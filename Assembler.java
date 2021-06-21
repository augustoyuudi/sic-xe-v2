import java.util.HashMap;
import java.util.ArrayList;

public class Assembler {

  private Integer LOCCTR;
  private HashMap<String, Integer> SYMTAB = new HashMap<String, Integer>();
  private ArrayList<String> intermediateFile = new ArrayList<String>();
  private Integer startingAddress;
  private Integer programLength;
  private String code;
	private String whiteSpaceRegex = "[\\s,]+";
  private HashMap<String, Integer> OPTAB = new Optab().OPTAB;
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
        intermediateFile.add(lines[currentIndex]);
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

      if (words.length == 3) {
        String label = words[0];
        if (SYMTAB.containsKey(label)) {
          System.out.println("duplicate symbol");
          return;
        }
        SYMTAB.put(label, LOCCTR);
        intermediateFile.add(lines[currentIndex].trim());
        currentIndex += 1;
        words = lines[currentIndex].trim().split(whiteSpaceRegex);
        opcode = words[0];
        continue;
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
        intermediateFile.add(lines[currentIndex].trim());
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

      System.out.println("invalid operation code");
    }

    intermediateFile.add(lines[currentIndex].trim());
    programLength = LOCCTR - startingAddress;
    // System.out.println(intermediateFile);
  }

  private void passTwo() {
    Integer currentIndex = 0;
    String[] words = intermediateFile.get(currentIndex).split(whiteSpaceRegex);
    String opcode = words[1];
    String operand;
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
        if (words.length > 2) {
          operandAddress = SYMTAB.get(opcode);

          if (operandAddress == null) {
            operandAddress = 0;
            // set error flag (undeifned symbol)
          }
        }
        Integer temp = OPTAB.get(opcode);
        objectCode = Integer.toHexString(temp) + String.format("%04d", operandAddress);
      }

      if (opcode.equals("BYTE") || opcode.equals("WORD")) {
        // convert constant to object code
      }

      // if object code dont fit into the current text record
        // write text record to object program
        // initialize new text record


      // add object code to text record
      operandAddress = SYMTAB.get(opcode);
      objectCode = OPTAB.get(opcode) + String.format("%04d", operandAddress);
      objectProgram.add(objectCode);
      currentIndex += 1;
      words = intermediateFile.get(currentIndex).split(whiteSpaceRegex);
      opcode = words[0];
    }

    String endLine = "E" + String.format("%06X", startingAddress);
    objectProgram.add(endLine);
    // write last text record to object program

    System.out.println(objectProgram);
  }
}