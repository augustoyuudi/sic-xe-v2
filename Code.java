
public class Code {
	String t ="TESTE START	0	COMMENT\n"
			+ "TEST1 MACRO	&A,&B,&C\n"
			+ "		 CLEAR	X	COMMENT\n"
			+ "		 CLEAR	A\n"
			+ "		 CLEAR	S\n"
			+ "		 LDT	&A\n"
			+ "		 LDA    &B\n"
			+ "		 LDC	&C\n"
			+ "TEST2 MACRO  &A\n"
			+ "		 ADD    &A\n"
			+ "		 MEND\n"
			+ "MTEST2 TEST2 LOL\n"
			+ "TEST3 MACRO	&B\n"
			+ "		 SUB	&B\n"
			+ "		 MEND\n"
			+ "		 MEND\n"
			+ "MTEST1 TEST1 X Y Z\n"
			+ "MTEST2 TEST2 K\n"
			+ "MTEST3 TEST3 X\n"
			+ "		 END";

}
