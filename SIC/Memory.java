package SIC;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Memoria é um array de bytes de 2 kb, com 1 kb para instruções e 1kb para dados
 *
 * Dados começam na posicao 1024
 * Instrucoes começam na posicao 0
 */
public class Memory {

	private byte[] memory; // array da memoria
	private ByteBuffer buffer; // buffer para facilitar o controle da memoria
	private int instPointer; // Ponteiro para instruções

	public Memory() {
		memory = new byte[2048]; //Memoria com 2 kb
		buffer = ByteBuffer.wrap(memory);
		buffer.order(ByteOrder.LITTLE_ENDIAN); // Ordena em little Endian
		instPointer = 0;
	}

	protected void newInstF1(byte inst) { //Coloca instrução de formato 1 na mamoria
		if(instPointer == 1023)
			throw new ArithmeticException("Instruction Memory Full");
		buffer.put(instPointer, inst);
		instPointer ++;
	}

	protected void newInstF2F3F4(byte[] inst) { //Coloca instrução de formato 2,3,4 na mamoria
		for (byte b : inst) {
			buffer.put(instPointer, b);
			instPointer ++;
			if(instPointer == 1023)
				throw new ArithmeticException("Instruction Memory Full");
		}
	}

	protected void setNewData(int value, int address) { // Coloca dado na memoria no proxima posição livre
		if ((address > 2048) | (address < 1024))
			throw new ArithmeticException("Invalid address");
		buffer.putInt(address, value);
	}

	protected int getData(int address) { // retorna dado da memoria
		return buffer.getInt(address);
	}

	protected byte getDataByte(int address) { // retorna dado(byte)da memoria
		return buffer.get(address);
	}

	//Limpa memoria
	protected void clear() {
		buffer.clear();
	}

	public String instructionMemoryToString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 1024; i++) {
			byte inst = getDataByte(i);
			sb.append(String.format("%02X ", inst));
		}

		return sb.toString();
	}

	public String dataMemoryToString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 1024; i < 2048; i++) {
			byte inst = getDataByte(i);
			sb.append(String.format("%02X ", inst));
		}

		return sb.toString();
	}
}
