package SIC;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Loader {
  Memory memory;

  protected void start(Memory memory, ArrayList<String> objectCode) {
    this.memory = memory;
    for (String s : objectCode) {
      addToMemory(s);
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
      splitInstruction[0] = instruction.toUpperCase();
    }
    if (l == 4) {
      splitInstruction = new String[2];
      splitInstruction[0] = instruction.substring(0, 2).toUpperCase();
      splitInstruction[1] = instruction.substring(2, 4).toUpperCase();
    }
    if (l > 4) {
      splitInstruction = new String[3];
      splitInstruction[0] = instruction.substring(0, 2).toUpperCase();
      splitInstruction[1] = instruction.substring(2, 4).toUpperCase();
      splitInstruction[2] = instruction.substring(4).toUpperCase();
    }

    return splitInstruction;
  }
}