import java.util.ArrayList;
import java.util.Arrays;


public class MacroProcessor {

	String[] splitCode;
	DefTable definitionTable = new DefTable();
	int getLine;

	//Constructor receive the code input
	public MacroProcessor(String code) {
		splitCode = code.split("\n");
		getLine = 0;
	}

	//Main function to process code and expand macros
	public String processCode() {
		ArrayList<String> codeExpanded = new ArrayList<String>();
		String line;

		while (getLine < splitCode.length) {
				line = splitCode[getLine];
				codeExpanded.addAll(processLine(line, false));
				getLine++;
			}
		return String.join("\n", codeExpanded);
	}

	//process line returns a expanded macros(including nested ones), define macros(including nested ones) or return the line
	public ArrayList<String>  processLine(String line, boolean defining) {

		String[] lineSplit = line.split("[\\s,]+");

		//Expand Macro
		if(definitionTable.containMacro(lineSplit[1]) && !defining) {
			ArrayList<String> expansion;
			if(lineSplit.length > 2)
				expansion = expand(definitionTable.getMacro(lineSplit[1]), Arrays.copyOfRange(lineSplit, 2, lineSplit.length));
			else
				expansion = expand(definitionTable.getMacro(lineSplit[1]), new String[0]);
			expansion.add(0, "."+line);
			return expansion;
		}

		//Define Macro
		else if(lineSplit[1].equals("MACRO")) {
			getLine++;
			//if has parameters
			if(lineSplit.length > 2)
				define(lineSplit[0], Arrays.copyOfRange(lineSplit, 2, lineSplit.length));
			else
				define(lineSplit[0], new String[0]);

			return  new ArrayList<String>();
		}

		//Copy line
		else {
			ArrayList<String> al = new ArrayList<String>();
			al.add(line);
			return al;
			}
	}

	//Define Macro
	public void define(String macroName, String[] parameters) {
		//put new macro on defTable
		definitionTable.putMacro(new Macro(parameters), macroName);
		String line = splitCode[getLine];

		while (!line.contains("MEND")) {
			definitionTable.getMacro(macroName).newBodyLine(processLine(line, true));
			getLine++;
			line = splitCode[getLine];
		}
	}

	public ArrayList<String> expand(Macro macro, String[] parameters) {

		ArrayList<String> macroExpanded = new ArrayList<String>();
		for (int i = 0; i < macro.body.size(); i++) {
				macroExpanded.addAll(processLine(macro.getLine(i, parameters), false));
		}

		return macroExpanded;
	}
}
