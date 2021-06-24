package SIC;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.ByteBuffer;

		// JFileChooser jFileChooser = new JFileChooser();
    // int result = jFileChooser.showOpenDialog(new JFrame());
    // if (result == JFileChooser.APPROVE_OPTION) {
    // 	File selectedFile = jFileChooser.getSelectedFile();
    // 	sic.start(selectedFile.getAbsolutePath());
    // 	updateReg(sic.getRegister());
    // 	updateInstructionMemory(sic.getMemory().instructionMemoryToString());
    // 	updateDataMemory(sic.getMemory().dataMemoryToString());
    // 	updateCurrentInstruction(sic.getInstruction().getCurrentInstruction());
    // }

public class Loader {
  Memory memory;

  protected void start(Memory memory, String filePath) {
    this.memory = memory;
    readInputFile(filePath);
  }

  protected void readInputFile(String filePath) {
    try {
      File inputFile = new File(filePath);
      Scanner reader = new Scanner(inputFile);

      while (reader.hasNextLine()) {
        addToMemory(reader.nextLine());
      }

      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  protected void addToMemory(String instruction) {
    String[] splitInstruction = splitInstruction(instruction);

    byte[] splitInstructionBytes;
    ByteBuffer buffer;

    splitInstructionBytes = new byte[splitInstruction.length];
    buffer = ByteBuffer.wrap(splitInstructionBytes);

    for (String s : splitInstruction) {
      buffer.put((byte) Integer.parseInt(s, 16));
    }

    if (splitInstruction.length == 1) {
      memory.newInstF1(splitInstructionBytes[0]);

      return;
    }

    memory.newInstF2F3F4(splitInstructionBytes);
  };

  protected String[] splitInstruction(String instruction) {
    String[] splitInstruction = new String[0];
    int l = instruction.length();
    if (l == 2) {
      splitInstruction = new String[1];
      splitInstruction[0] = instruction;
    }
    if (l == 4) {
      splitInstruction = new String[2];
      splitInstruction[0] = instruction.substring(0, 2);
      splitInstruction[1] = instruction.substring(2, 4);
    }
    if (l > 4) {
      splitInstruction = new String[3];
      splitInstruction[0] = instruction.substring(0, 2);
      splitInstruction[1] = instruction.substring(2, 4);
      splitInstruction[2] = instruction.substring(4);
    }

    return splitInstruction;
  }
}