

public class Teste {

	public static void main(String[] args) {

		Code c = new Code();
		MacroProcessor mc = new MacroProcessor(c.t);

		String ec = mc.processCode();

		Assembler assembler = new Assembler(ec);
		assembler.assemble();
	}

}
