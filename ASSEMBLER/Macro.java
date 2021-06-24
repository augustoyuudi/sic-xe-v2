package ASSEMBLER;
import java.util.ArrayList;

public class Macro {

	private String[] parameters;
	protected ArrayList<String> body;

	//Constructor receive the formal parameters of the macro
	public Macro(String[] parameters) {
		body = new ArrayList<String>();
		this.parameters = parameters;
	}

	//Add new line in the macro body
	protected void newBodyLine(ArrayList<String> line) {
		body.addAll(line);
	}

	//Return line from macro, with actual parameters
	public String getLine(int i, String[] actualParameters) {
		String line = body.get(i);

		for (int j = 0; j < actualParameters.length; j++)
			line =line.replace(parameters[j], actualParameters[j]);

		return line;

	}

}
