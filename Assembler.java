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
  private HashMap<String, Byte> OPTAB = new Optab().OPTAB;

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

      if (words.length == 3 && !words[2].equals("COMMENT")) {
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
  }

  private void passTwo() {
    Integer currentIndex = 0;
    String line = intermediateFile[0];
    String[] words = line.split(whiteSpaceRegex);

    if (words.length > 2) {
      opcode = words[1];
      String operand = words[2];

      if (opcode.equals("START")) {
        // write listing line
        // next input line
      }

      if (!opcode.equals("START")) {
        // write header record to object program
        // initialize first text record
      }
    }

    words = lines[currentIndex].split(whiteSpaceRegex);
    opcode = words[0];

    while (!opcode.equals("END")) {
      if (opcode.charAt(0) == '.') {
        // write listing line
        // read next input file
        continue;
      }

      if (optab.containsKey(opcode)) {
        if (words.length == 3 && !words[2].equals("COMMENT")) {
          // search symbol operand field
          // search symtab for operand
          // if found store symbol value as operand address

          // else store 0 as operand address
          // set error flag (undeifned symbol)
        }
        // store 0 as operand address
        // assemble the object code instruction
      }

      if (opcode.equals("BYTE") || opcode.equals("WORD")) {
        // convert constant to object code
      }

      // if object code dont fit into the current text record
        // write text record to object program
        // initialize new text record

      // add object code to text record
      // write listing line
      // read next input line
    }

    // write last text record to object program
    // write end record to object program
    // write last listing line


    return;
  }
}