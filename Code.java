
public class Code {
	String t = "START TESTE\n" + 
			"*\n" + 
			"MACRO\n" + 
			"SCALE &RP\n" + 
			"MACRO\n" + 
			"MULTSC &A,&B,&C\n" + 
			"LOAD &A\n" + 
			"MULT &B\n" + 
			"SHIFTR &RP\n" + 
			"STORE &C\n" + 
			"MEND\n" + 
			"MACRO\n" + 
			"DIVSC &A,&B,&C\n" + 
			"LOAD &A\n" + 
			"DIV &B\n" + 
			"SHIFTL &RP\n" + 
			"STORE &C\n" + 
			"MEND\n" + 
			"MEND\n" + 
			"*\n" + 
			"MACRO\n" + 
			"&LAB DISCR &A,&B,&C,&D\n" + 
			"&LAB MULTSC &A,&C,TEMP1\n" + 
			"MULTSC TEMP1,@4,TEMP1\n" + 
			"MULTSC &A,&B,TEMP2\n" + 
			"SUB TEMP1\n" + 
			"STORE &D\n" + 
			"MEND\n" + 
			"*\n" + 
			"READ A\n" + 
			"READ B\n" + 
			"READ C\n" + 
			"SCALE 3\n" + 
			"DISCR A,B,C,D\n" + 
			"WRITE D\n" + 
			"STOP\n" + 
			"*A\n" + 
			"SPACE\n" + 
			"B SPACE\n" + 
			"C SPACE\n" + 
			"D SPACE\n" + 
			"TEMP1 SPACE\n" + 
			"TEMP2 SPACE\n" + 
			"*\n" + 
			"END";
	public Code() {
		// TODO Auto-generated constructor stub
	}

}
