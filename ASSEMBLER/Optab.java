package ASSEMBLER;
import java.util.HashMap;

public class Optab {

  private HashMap<String, Integer> OPTAB = new HashMap<String, Integer>();
  private HashMap<String, Integer> OperationTypes = new HashMap<String, Integer>();

  public Optab() {
    setOperationCodes();
    setOperationTypes();
  }

  private void setOperationCodes() {
    OPTAB.put("ADD", 0x18);
    OPTAB.put("ADDR", 0x90);
    OPTAB.put("AND", 0x40);
    OPTAB.put("CLEAR", 0xB4);
    OPTAB.put("COMP", 0x28);
    OPTAB.put("COMPR", 0xA0);
    OPTAB.put("DIV", 0x24);
    OPTAB.put("DIVR", 0x9C);
    OPTAB.put("J", 0x3C);
    OPTAB.put("JEQ", 0x30);
    OPTAB.put("JGT", 0x34);
    OPTAB.put("JLT", 0x38);
    OPTAB.put("JSUB", 0x48);
    OPTAB.put("LDA", 0x00);
    OPTAB.put("LDB", 0x68);
    OPTAB.put("LDC", 0x50);
    OPTAB.put("LDL", 0x08);
    OPTAB.put("LDS", 0x6C);
    OPTAB.put("LDT", 0x74);
    OPTAB.put("LDX", 0x04);
    OPTAB.put("MUL", 0x20);
    OPTAB.put("MULR", 0x98);
    OPTAB.put("OR", 0x44);
    OPTAB.put("RMO", 0xAC);
    OPTAB.put("RSUB", 0x4C);
    OPTAB.put("SHIFTL", 0xA4);
    OPTAB.put("SHIFTR", 0xA8);
    OPTAB.put("STA", 0x0C);
    OPTAB.put("STB", 0x78);
    OPTAB.put("STCH", 0x54);
    OPTAB.put("STL", 0x14);
    OPTAB.put("STS", 0x7C);
    OPTAB.put("STT", 0x84);
    OPTAB.put("STX", 0x10);
    OPTAB.put("SUB", 0x1C);
    OPTAB.put("SUBR", 0x94);
    OPTAB.put("TIX", 0x2C);
    OPTAB.put("TIXR", 0xB8);
  }

  private void setOperationTypes() {
    OperationTypes.put("ADD", 3);
    OperationTypes.put("ADDR", 2);
    OperationTypes.put("AND", 3);
    OperationTypes.put("CLEAR", 2);
    OperationTypes.put("COMP", 3);
    OperationTypes.put("COMPR", 2);
    OperationTypes.put("DIV", 3);
    OperationTypes.put("DIVR", 2);
    OperationTypes.put("J", 3);
    OperationTypes.put("JEQ", 3);
    OperationTypes.put("JGT", 3);
    OperationTypes.put("JLT", 3);
    OperationTypes.put("JSUB", 3);
    OperationTypes.put("LDA", 3);
    OperationTypes.put("LDB", 3);
    OperationTypes.put("LDC", 3);
    OperationTypes.put("LDL", 3);
    OperationTypes.put("LDS", 3);
    OperationTypes.put("LDT", 3);
    OperationTypes.put("LDX", 3);
    OperationTypes.put("MUL", 3);
    OperationTypes.put("MULR", 2);
    OperationTypes.put("OR", 3);
    OperationTypes.put("RMO", 2);
    OperationTypes.put("RSUB", 3);
    OperationTypes.put("SHIFTL", 2);
    OperationTypes.put("SHIFTR", 2);
    OperationTypes.put("STA", 3);
    OperationTypes.put("STB", 3);
    OperationTypes.put("STCH", 3);
    OperationTypes.put("STL", 3);
    OperationTypes.put("STS", 3);
    OperationTypes.put("STT", 3);
    OperationTypes.put("STX", 3);
    OperationTypes.put("SUB", 3);
    OperationTypes.put("SUBR", 2);
    OperationTypes.put("TIX", 3);
    OperationTypes.put("TIXR", 2);
  }

  public Integer get(String opcode) {
    return OPTAB.get(opcode);
  }

  public Boolean containsKey(String opcode) {
    return OPTAB.containsKey(opcode);
  }

  public Integer getOperationType(String opcode) {
    return OperationTypes.get(opcode);
  }
}