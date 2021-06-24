import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Code c = new Code();
		// System.out.println(c.getCode());
		MacroProcessor mc = new MacroProcessor(c.getCode());

		String ec = mc.processCode();

		Assembler assembler = new Assembler(ec);
		ArrayList<String> objectProgram = assembler.assemble();
		System.out.println(objectProgram);
	}
}
