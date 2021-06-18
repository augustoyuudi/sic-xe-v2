import java.util.Arrays;

public class Teste {

	public static void main(String[] args) {

        Code c = new Code();
        String[] s = c.t.split("\n");
        DefTable dT = new DefTable();
        Bool d = false;
        Bool p = false;
        String[] lp;

        for (String i: s) {

            if(i.equals("MACRO")) {
                d = true;
                p = true;
                i = null;
            }
            if (p) {
                lp = i.split("[\\s,]+");
                dT.putMacro(new Macro(ArrayUtils.removeElement(lp, lp[0])), lp[0]);
            }

        }

        for (String i: s)
            System.out.print(i+"\n");

	}

}
