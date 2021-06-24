import java.util.HashMap;
import java.util.ArrayList;

public class Assembler {

  private Integer LOCCTR;
  private Symtab SYMTAB = new Symtab();
  private ArrayList<String> intermediateFile = new ArrayList<String>();
  private Integer startingAddress;
  private Integer programLength;
  private String code;
	private String whiteSpaceRegex = "[\\s,]+";
  private Optab OPTAB = new Optab();
  private ArrayList<String> objectProgram = new ArrayList<String>();

  public Assembler(String code) {
    this.code = code;
  }

  public void assemble() {
    passOne();
    passTwo();
  }

  private void passOne() {
    String[] lines = code.split("\n");
    Integer currentIndex = 0;
    String[] words = lines[currentIndex].split(whiteSpaceRegex);
    String opcode;

    if (words.length > 2) {
      opcode = words[1];
      String operand = words[2];

      if (opcode.equals("START")) {
        startingAddress = Integer.parseInt(operand);
        LOCCTR = Integer.parseInt(operand);
        intermediateFile.add(lines[currentIndex].trim());
        currentIndex += 1;
      }

      if (!opcode.equals("START")) {
        LOCCTR = 0;
      }
    }

    words = lines[currentIndex].split(whiteSpaceRegex);
    opcode = words[0];

    while (!opcode.equals("END")) {
      if (opcode.charAt(0) == '.') {
        intermediateFile.add(lines[currentIndex].trim());
        currentIndex += 1;
        words = lines[currentIndex].trim().split(whiteSpaceRegex);
        opcode = words[0];
        continue;
      }

      if (words.length > 2) {
        String label = words[0];
        opcode = words[1];

        if (SYMTAB.containsKey(label)) {
          System.out.println("duplicate symbol");
          return;
        }

        SYMTAB.put(label, LOCCTR);
      }

      if (OPTAB.containsKey(opcode)) {
        LOCCTR += 3;
        intermediateFile.add(lines[currentIndex].trim());
        currentIndex += 1;
        words = lines[currentIndex].trim().split(whiteSpaceRegex);
        opcode = words[0];
        continue;
      }

      if (opcode.equals("WORD")) {
        LOCCTR += 3;
        currentIndex += 1;
        words = lines[currentIndex].trim().split(whiteSpaceRegex);
        opcode = words[0];
        continue;
      }

      if (opcode.equals("RESW")) {
        Integer operand = Integer.parseInt(words[1]);
        LOCCTR += (3 * operand);
        intermediateFile.add(lines[currentIndex].trim());
        currentIndex += 1;
        words = lines[currentIndex].trim().split(whiteSpaceRegex);
        opcode = words[0];
        continue;
      }

      if (opcode.equals("RESB")) {
        Integer operand = Integer.parseInt(words[1]);
        LOCCTR += operand;
        intermediateFile.add(lines[currentIndex].trim());
        currentIndex += 1;
        words = lines[currentIndex].trim().split(whiteSpaceRegex);
        opcode = words[0];
        continue;
      }

      if (opcode.equals("BYTE")) {
        Integer operand = Integer.parseInt(words[1]);
        LOCCTR += operand;
        intermediateFile.add(lines[currentIndex].trim());
        currentIndex += 1;
        words = lines[currentIndex].trim().split(whiteSpaceRegex);
        opcode = words[0];
        continue;
      }

      // System.out.println("invalid operation code");
    }

    intermediateFile.add(lines[currentIndex].trim());
    programLength = LOCCTR - startingAddress;
  }

  private void passTwo() {
    Integer currentIndex = 0;
    String[] words = intermediateFile.get(currentIndex).split(whiteSpaceRegex);
    String opcode = words[1];
    String operand = null;
    Integer operandAddress = 0;
    String objectCode;

    if (words.length > 2) {
      operand = words[2];

      if (opcode.equals("START")) {
        String programName = String.format("%1$-" + 7 + "s", "H" + words[0]);

        if (programName.length() > 7) {
          System.out.println("invalid program name");
          return;
        }

        String firstLine = programName + String.format("%06X", startingAddress) + String.format("%06X", programLength);
        objectProgram.add(firstLine);

        currentIndex += 1;
        words = intermediateFile.get(currentIndex).split(whiteSpaceRegex);
        opcode = words[0];
      }
    }

    while (!opcode.equals("END")) {
      if (opcode.charAt(0) == '.') {
        currentIndex += 1;
        words = intermediateFile.get(currentIndex).split(whiteSpaceRegex);
        opcode = words[0];
        continue;
      }

      if (OPTAB.containsKey(opcode)) {
        if (OPTAB.getOperationType(opcode) == 2) {
          handleInstruction2(opcode, operandAddress, words);
        }

        if (OPTAB.getOperationType(opcode) == 3) {
          handleInstruction3(opcode, words);
        }

        currentIndex += 1;
        words = intermediateFile.get(currentIndex).split(whiteSpaceRegex);
        opcode = words[0];
        continue;
      }

      if (opcode.equals("BYTE") || opcode.equals("WORD")) {
        // opcode = words[0];
        // convert constant to object code
      }

      operandAddress = SYMTAB.get(operand);
      objectCode = OPTAB.get(opcode) + String.format("%04d", operandAddress);
      objectProgram.add(objectCode);
      currentIndex += 1;
      words = intermediateFile.get(currentIndex).split(whiteSpaceRegex);
      opcode = words[0];
    }

    String endLine = "E" + String.format("%06X", startingAddress);
    objectProgram.add(endLine);
  }

  private void handleInstruction2(String opcode, Integer operandAddress, String[] words) {
    Integer temp = OPTAB.get(opcode);
    String objectCode = Integer.toHexString(temp);
    String register1 = String.valueOf(words[1].charAt(0));
    String register2 = null;
    Integer register1Address = SYMTAB.get(register1);
    Integer register2Address = null;

    if (words[1].length() == 2) {
      register2 = String.valueOf(words[1].charAt(1));
      register2Address = SYMTAB.get(register2);
    }

    if (register2 == null) {
      objectCode += String.format("%-2s", register1Address + "").replace(' ', '0');
    }

    if (register2 != null) {
      objectCode += register1Address + "" + register2Address;
    }

    objectProgram.add(objectCode);
  }

  private void handleInstruction3(String opcode, String[] words) {
    String strOpcode = Integer.toHexString(OPTAB.get(opcode));
    if (strOpcode.length() == 1) {
      strOpcode += "0";
    }
    String objectCode = strOpcode;
    String operand = words[1];
    Integer operandAddress = SYMTAB.get(operand);

    objectCode += String.format("%04d", operandAddress);
    objectProgram.add(objectCode);
  }
}