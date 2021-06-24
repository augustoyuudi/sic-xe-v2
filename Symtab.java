import java.util.HashMap;

public class Symtab {
  private HashMap<String, Integer> SYMTAB = new HashMap<String, Integer>();

  public Symtab() {
    SYMTAB.put("A", 0);
    SYMTAB.put("X", 1);
    SYMTAB.put("L", 2);
    SYMTAB.put("B", 3);
    SYMTAB.put("S", 4);
    SYMTAB.put("T", 5);
    SYMTAB.put("PC", 8);
    SYMTAB.put("SW", 9);
  }

  public void put(String key, Integer value) {
    SYMTAB.put(key, value);
  }

  public Integer get(String key) {
    return SYMTAB.get(key);
  }

  public Boolean containsKey(String key) {
    return SYMTAB.containsKey(key);
  }

  public void printAll() {
    SYMTAB.forEach((key, value) -> System.out.println(key + "-" + value));
  }
}