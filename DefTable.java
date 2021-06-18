import java.util.Map;
import java.util.HashMap;

public class DefTable {
	
	Map<String, Macro> defMacros;
	
	public DefTable() {

		defMacros = new HashMap<String, Macro>();
	}
	
	public void putMacro(Macro m, String macroName) {
		defMacros.put(macroName, m);
	}
}
