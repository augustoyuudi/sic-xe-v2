package SIC;
import java.util.HashMap;
import java.util.Map;

public class Register {
  private Map<String, Integer> register;

  public void start() {
		register = new HashMap<String, Integer>();
		register.put("A", -1);
		register.put("X", -1);
		register.put("L", -1);
		register.put("B", -1);
		register.put("S", -1);
		register.put("T", -1);
		register.put("PC", -1);
		register.put("SW", -1);
  }

	public int get(String key) {
		return register.get(key);
	}

	public void replace(String key, int value) {
		register.replace(key, value);
	}


  protected String getName(int register) {
    if (register == 0) return "A";
    else if (register == 1) return "X";
    else if (register == 2) return "L";
    else if (register == 3) return "B";
    else if (register == 4) return "S";
    else if (register == 5) return "T";
    else if (register == 6) return "F";
    else if (register == 7) return "PC";
    else if (register == 8) return "SW";

    return "";
  }
}
