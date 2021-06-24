package ASSEMBLER;
import java.util.Map;
import java.util.HashMap;

public class DefTable {

	private Map<String, Macro> defMacros;

	public DefTable() {

		defMacros = new HashMap<String, Macro>();
	}
	//Returns True if Macro is definition table
	public boolean containMacro(String macroName) {
		return defMacros.containsKey(macroName);
	}
	//Put Macro in definition table
	public void putMacro(Macro m, String macroName) {
		defMacros.put(macroName, m);
	}
	//get macro from definition table by name
	public Macro getMacro(String macroName) {
		return defMacros.get(macroName);
	}

}
