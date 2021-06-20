import java.util.HashMap;

public class Optab {

  public HashMap<String, Byte> OPTAB = new HashMap<String, Byte>();

  public Optab() {
    OPTAB.put("ADD", (byte) 0x18);
    OPTAB.put("ADDR", (byte) 0x90);
    OPTAB.put("AND", (byte) 0x40);
    OPTAB.put("CLEAR", (byte) 0xB4);
    OPTAB.put("COMP", (byte) 0x28);
    OPTAB.put("COMPR", (byte) 0xA0);
    OPTAB.put("DIV", (byte) 0x24);
    OPTAB.put("DIVR", (byte) 0x9C);
    OPTAB.put("J", (byte) 0x3C);
    OPTAB.put("JEQ", (byte) 0x30);
    OPTAB.put("JGT", (byte) 0x34);
    OPTAB.put("JLT", (byte) 0x38);
    OPTAB.put("JSUB", (byte) 0x48);
    OPTAB.put("LDA", (byte) 0x00);
    OPTAB.put("LDB", (byte) 0x68);
    OPTAB.put("LDC", (byte) 0x50);
    OPTAB.put("LDL", (byte) 0x08);
    OPTAB.put("LDS", (byte) 0x6C);
    OPTAB.put("LDT", (byte) 0x74);
    OPTAB.put("LDX", (byte) 0x04);
    OPTAB.put("MUL", (byte) 0x20);
    OPTAB.put("MULR", (byte) 0x98);
    OPTAB.put("OR", (byte) 0x44);
    OPTAB.put("RMO", (byte) 0xAC);
    OPTAB.put("RSUB", (byte) 0x4C);
    OPTAB.put("SHIFTL", (byte) 0xA4);
    OPTAB.put("SHIFTR", (byte) 0xA8);
    OPTAB.put("STA", (byte) 0x0C);
    OPTAB.put("STB", (byte) 0x78);
    OPTAB.put("STCH", (byte) 0x54);
    OPTAB.put("STL", (byte) 0x14);
    OPTAB.put("STS", (byte) 0x7C);
    OPTAB.put("STT", (byte) 0x84);
    OPTAB.put("STX", (byte) 0x10);
    OPTAB.put("SUB", (byte) 0x1C);
    OPTAB.put("SUBR", (byte) 0x94);
    OPTAB.put("TIX", (byte) 0x2C);
    OPTAB.put("TIXR", (byte) 0xB8);
  }
}