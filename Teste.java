

public class Teste {

	public static void main(String[] args) {

		Code c = new Code();
		MacroProcessor mc = new MacroProcessor(c.t);
		
		String ec = mc.processCode();
		
		System.out.print(ec);
	}
	
}
