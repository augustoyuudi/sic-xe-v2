package SIC;
public class Instruction {

	byte opcode;
	byte nixbpe;
	String r1;
	String r2;
	int address;

	public Instruction() {
		opcode = 0;
		nixbpe = 0;
		r1 = "";
		r2 = "";
	}

	//Inicializa uma nova Instrução
	protected void initInstruction(byte opcode,	byte nixbpe, String r1, String r2, int address) {
		this.opcode = opcode;
		this.nixbpe = nixbpe;
		this.r1 = r1;
		this.r2 = r2;
		this.address = (address * 4) + 1024;
	}

	//Executa Instrução
	protected void execInstruction(Register r, Memory m) {

		addressCalc(r, m);

		if(opcode == (byte) 0x18) add(r, m);

		else if(opcode == (byte) 0x90) addR(r);

		else if(opcode == (byte) 0x40) and(r, m);

		else if(opcode == (byte) 0xB4) clear(r);

		else if(opcode == (byte) 0x28) comp(r, m);

		else if(opcode == (byte) 0xA0) compR(r);

		else if(opcode == (byte) 0x24) div(r, m);

		else if(opcode == (byte) 0x9C) divR(r);

		else if(opcode == (byte) 0x3C) j(r);

		else if(opcode == (byte) 0x30) jeq(r);

		else if(opcode == (byte) 0x34) jgt(r);

		else if(opcode == (byte) 0x38) jlt(r);

		else if(opcode == (byte) 0x48) jsub(r);

		else if(opcode == (byte) 0x00) lda(r, m);

		else if(opcode == (byte) 0x68) ldb(r, m);

		else if(opcode == (byte) 0x50) ldch(r, m);

		else if(opcode == (byte) 0x08) ldl(r, m);

		else if(opcode == (byte) 0x6C) lds(r, m);

		else if(opcode == (byte) 0x74) ldt(r, m);

		else if(opcode == (byte) 0x04) ldx(r, m);

		else if(opcode == (byte) 0x20) mul(r, m);

		else if(opcode == (byte) 0x98) mulr(r);

		else if(opcode == (byte) 0x44) or(r, m);

		else if(opcode == (byte) 0xAC) rmo(r);

		else if(opcode == (byte) 0x4C) rsub(r);

		else if(opcode == (byte) 0xA4) shiftl(r);

		else if(opcode == (byte) 0xA8) shiftr(r);

		else if(opcode == (byte) 0x0C) sta(r, m);

		else if(opcode == (byte) 0x78) stb(r, m);

		else if(opcode == (byte) 0x54) stch(r, m);

		else if(opcode == (byte) 0x14) stl(r, m);

		else if(opcode == (byte) 0x7C) sts(r, m);

		else if(opcode == (byte) 0x84) stt(r, m);

		else if(opcode == (byte) 0x10) stx(r, m);

		else if(opcode == (byte) 0x1C) sub(r, m);

		else if(opcode == (byte) 0x94) subr(r);

		else if(opcode == (byte) 0x2C) tix(r, m);

		else if(opcode == (byte) 0xB8) tixr(r);

	}

	//------------------------------------------------------------------------------------------------------
	//-----------------------------------Utilitarios--------------------------------------------------------
	//------------------------------------------------------------------------------------------------------

	private void addressCalc(Register r, Memory m) {
		//Endereçamento Direto
		if(nixbpe == 50) // 110010
			address = r.get("PC") + (~address + 1);

		if(nixbpe == 52) // 110100
			address = r.get("B") + address;

		if((nixbpe == 56) || (nixbpe == 57)) // 111000 e //111001
			address = r.get("X") + address;

		if(nixbpe == 58) // 111010
			address = r.get("X") + r.get("PC");

		if(nixbpe == 60) // 111100
			address = r.get("X") + r.get("B");

		if(nixbpe == 8) // 001000
			address = r.get("X") + address;

		//Enderecamento indireto
		if((nixbpe == 32) || (nixbpe == 33) || (nixbpe == 34) || (nixbpe == 36)) {
			address = m.getData(address);
		}
	}

	//------------------------------------------------------------------------------------------------------
	//---------------------------------METODOS DAS INSTRUÇÕES-----------------------------------------------
	//------------------------------------------------------------------------------------------------------

	//ADD - Soma um valor de memoria com o valor do reg A e armazena em A
	private void add(Register r, Memory m){
		int data = m.getData(address);
		r.replace("A", r.get("A") + data);
	}

	//------------------------------------------------------------------------------------------------------

	//ADDR - Soma dois registradores e armazena em r2
	private void addR(Register r){
		r.replace(r2, r.get(r1) +  r.get(r2));
	}

	//------------------------------------------------------------------------------------------------------

	//AND - And um valor de memoria com o valor do reg A e armazena em A
	private void and(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("A", r.get("A") & data);
	}

	//------------------------------------------------------------------------------------------------------

	// CLEAR - Limpa um registrador
	private void clear(Register r) {
		r.replace(r1, 0);
	}

	//------------------------------------------------------------------------------------------------------

	// COMP - Compara um valor de memoria com o valor do reg A
	private int comp(Register r, Memory m) {
		int data = m.getData(address);
		return Integer.compare(r.get("A"), data);
	}

	//------------------------------------------------------------------------------------------------------

	// COMPR - Compara um valor de reg1 com o valor do reg 2
	private int compR(Register r) {
		return Integer.compare(r.get(r1), r.get(r2));
	}

	//------------------------------------------------------------------------------------------------------

	// DIV - Divide o valor do reg A por um valor de memoria e armazena no reg A
	private void div(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("A", r.get("A") / data);
	}

	//------------------------------------------------------------------------------------------------------

	// DIVR - Divide o valor do reg 2 pelo do reg 2 e armazena no reg 2
	private void divR(Register r) {
		r.replace(r2, r.get(r2) / r.get(r1));
	}

	//------------------------------------------------------------------------------------------------------

	// J - Jump carrega um endereço de memoria no reg PC
	private void j(Register r) {
		r.replace("PC", address);
	}

	//------------------------------------------------------------------------------------------------------

	// JEQ - Se dois valores forem Iguais carrega um endereço no PC
	private void jeq(Register r) {
		if(r.get(r1) == r.get(r2))
			r.replace("PC", address);
	}

	//------------------------------------------------------------------------------------------------------

	// JGT - Se v1 for maior que v2 carrega um enderaço no PC
	private void jgt(Register r) {
		if(r.get(r1) > r.get(r2))
			r.replace("PC", address);
	}

	//------------------------------------------------------------------------------------------------------

	// JLT - Se v1 for menor que v2 carrega um enderaço no PC
	private void jlt(Register r) {
		if(r.get(r1) < r.get(r2))
			r.replace("PC", address);
	}

	//------------------------------------------------------------------------------------------------------

	// JSUB - armazena o valor de PC em L e passo um endereço de subrotina para PC
	private void jsub(Register r) {
		r.replace("L", r.get("PC"));
		r.replace("PC", address);
	}

	//------------------------------------------------------------------------------------------------------

	//LDA - Carrega em A
	private void lda(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("A", data);
	}

	//------------------------------------------------------------------------------------------------------

	//LDB - Carrega em B
	private void ldb(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("B", data);
	}
	//------------------------------------------------------------------------------------------------------

	//LDCH - Carrega no byte mais signidicativo de A
	private void ldch(Register r, Memory m) {
		byte data = m.getDataByte(address);
		r.replace("A", r.get("A")&((int)data));
	}

	//------------------------------------------------------------------------------------------------------

	//LDL - Carrega em L
	private void ldl(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("L", data);
	}

	//------------------------------------------------------------------------------------------------------

	//LDS - Carrega em S
	private void lds(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("S", data);
	}

	//------------------------------------------------------------------------------------------------------

	//LDT - Carrega em T
	private void ldt(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("T", data);
	}

	//------------------------------------------------------------------------------------------------------

	//LDX - Carrega em X
	private void ldx(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("X", data);
	}

	//------------------------------------------------------------------------------------------------------

	//MUL - Multiplica um valor de memoria pelo reg A e armazena em A
	private void mul(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("A", r.get("A")*data);
	}

	//------------------------------------------------------------------------------------------------------

	//MULR - Multiplica dois reg e armazena em r2
	private void mulr(Register r) {
		r.replace(r2, r.get(r1)*r.get(r2));
	}

	//------------------------------------------------------------------------------------------------------

	// OR - bitwise ou entre um valor de memoria e reg A, armazena em A
	private void or(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("A", r.get("A") | data);
	}

	//------------------------------------------------------------------------------------------------------

	// RMO - copia conteudo do reg1 para o reg2
	private void rmo(Register r) {
		r.replace(r2, r.get(r1));
	}

	//------------------------------------------------------------------------------------------------------

	// RSUB - Carrega o conteudo de L para PC - (fim de subrotina)
	private void rsub(Register r) {
		r.replace("PC", r.get("L"));
	}

	//------------------------------------------------------------------------------------------------------

	//SHIFTL - desloca n bit a esquerda o conteudo do reg
	private void shiftl(Register r) {
		r.replace(r1, r.get(r1)<<r.get(r2));
	}

	//------------------------------------------------------------------------------------------------------

	//SHIFTR - desloca n bit a direita o conteudo do reg
	private void shiftr(Register r) {
		r.replace(r1, r.get(r1)>>r.get(r2));
	}

	//------------------------------------------------------------------------------------------------------

	// STA - armazena o valor de A em um endereço de memoria
	private void sta(Register r, Memory m) {
		m.setNewData(r.get("A"), address);
	}

	//------------------------------------------------------------------------------------------------------

	// STB - armazena o valor de B em um endereço de memoria
	private void stb(Register r, Memory m) {
		m.setNewData(r.get("B"), address);
	}

	//------------------------------------------------------------------------------------------------------

	// STCH - armazena o valor de byte de A em um endereço de memoria
	private void stch(Register r, Memory m) {
		m.setNewData(r.get("A")&0xFF, address);
	}

	//------------------------------------------------------------------------------------------------------

	// STL - armazena o valor de L em um endereço de memoria
	private void stl(Register r, Memory m) {
		m.setNewData(r.get("L"), address);
	}

	//------------------------------------------------------------------------------------------------------

	// STS - armazena o valor de S em um endereço de memoria
	private void sts(Register r, Memory m) {
		m.setNewData(r.get("S"), address);
	}

	//------------------------------------------------------------------------------------------------------

	// STT - armazena o valor de T em um endereço de memoria
	private void stt(Register r, Memory m) {
		m.setNewData(r.get("T"), address);
	}

	//------------------------------------------------------------------------------------------------------

	// STX - armazena o valor de X em um endereço de memoria
	private void stx(Register r, Memory m) {
		m.setNewData(r.get("X"), address);
	}

	//------------------------------------------------------------------------------------------------------

	// SUB -  armazena em A a subtração de um valor de memoria com reg A
	private void sub(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("A", data);
	}

	//------------------------------------------------------------------------------------------------------

	// SUBR - armazena em r2 a subtação r2-r1
	private void subr(Register r) {
		r.replace(r2, r.get(r2) - r.get(r1));
	}

	//------------------------------------------------------------------------------------------------------

	// TIX - Incrementa X e compara com valor de mem
	private int tix(Register r, Memory m) {
		int data = m.getData(address);
		r.replace("X", r.get("X") + 1);
		return Integer.compare(r.get("X"), data);
	}

	//------------------------------------------------------------------------------------------------------

	// TIXR - Incrementa X e compara com valor de r1
	private int tixr(Register r) {
		r.replace("X", r.get("X") + 1);
		return Integer.compare(r.get("X"), r.get(r1));
	}

	//------------------------------------------------------------------------------------------------------

  protected int getType(byte opcode) {
    if(opcode == (byte) 0x18) return 3;
		else if(opcode == (byte) 0x90) return 2;
		else if(opcode == (byte) 0x40) return 3;
		else if(opcode == (byte) 0xB4) return 2;
		else if(opcode == (byte) 0x4) return 3;
		else if(opcode == (byte) 0x28) return 3;
		else if(opcode == (byte) 0xA0) return 2;
		else if(opcode == (byte) 0x24) return 3;
		else if(opcode == (byte) 0x9C) return 2;
		else if(opcode == (byte) 0x3C) return 3;
		else if(opcode == (byte) 0x30) return 3;
		else if(opcode == (byte) 0x34) return 3;
		else if(opcode == (byte) 0x38) return 3;
		else if(opcode == (byte) 0x48) return 3;
		else if(opcode == (byte) 0x00) return 3;
		else if(opcode == (byte) 0x68) return 3;
		else if(opcode == (byte) 0x50) return 3;
		else if(opcode == (byte) 0x08) return 3;
		else if(opcode == (byte) 0x6C) return 3;
		else if(opcode == (byte) 0x74) return 3;
		else if(opcode == (byte) 0x04) return 3;
		else if(opcode == (byte) 0x20) return 3;
		else if(opcode == (byte) 0x98) return 2;
		else if(opcode == (byte) 0x44) return 3;
		else if(opcode == (byte) 0xAC) return 2;
		else if(opcode == (byte) 0x4C) return 3;
		else if(opcode == (byte) 0xA4) return 2;
		else if(opcode == (byte) 0xA8) return 2;
		else if(opcode == (byte) 0x0C) return 3;
		else if(opcode == (byte) 0x78) return 3;
		else if(opcode == (byte) 0x54) return 3;
		else if(opcode == (byte) 0x14) return 3;
		else if(opcode == (byte) 0x7C) return 3;
		else if(opcode == (byte) 0x84) return 3;
		else if(opcode == (byte) 0x10) return 3;
		else if(opcode == (byte) 0x1C) return 3;
		else if(opcode == (byte) 0x94) return 2;
		else if(opcode == (byte) 0x2C) return 3;
		else if(opcode == (byte) 0xB8) return 2;



    return 1;
  }

	public String getCurrentInstruction() {
		return String.format("%02X ", opcode);
	}
}
