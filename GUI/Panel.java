package GUI;
import ASSEMBLER.Assembler;
import SIC.SIC;
import SIC.Register;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	SIC sic;
	Assembler assembler;
	JTextPane instructionMemoryText, dataMemoryText;
	JTextArea textRegA, textRegX, textRegL, textRegB;
	JTextArea textRegS, textRegT, textRegPC, textRegSW;
	JTextArea currentInstructionText;

	public Panel() {
		sic = new SIC();
		assembler = new Assembler();

		setForeground(Color.WHITE);
		setBackground(new Color(169, 169, 169));
		setLayout(null);

		JLabel lblSicXeSimulator = new JLabel("SIC XE Simulator");
		lblSicXeSimulator.setBackground(new Color(0, 0, 0));
		lblSicXeSimulator.setBounds(145, 11, 152, 22);
		lblSicXeSimulator.setForeground(new Color(0, 0, 0));
		lblSicXeSimulator.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblSicXeSimulator);

		JButton btnLoad = new JButton("LOAD");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setMultiSelectionEnabled(true);
				int result = jFileChooser.showOpenDialog(new JFrame());
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFiles[] = jFileChooser.getSelectedFiles();
					assembler.handleInputFiles(selectedFiles);
				}
			}
		});
		btnLoad.setForeground(Color.BLACK);
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLoad.setBounds(31, 76, 89, 23);
		add(btnLoad);

		JButton btnStep = new JButton("STEP");
		btnStep.setForeground(new Color(0, 0, 0));
		btnStep.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnStep.setBounds(31, 110, 89, 23);
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sic.step();
				updateReg(sic.getRegister());
				updateInstructionMemory(sic.getMemory().instructionMemoryToString());
				updateDataMemory(sic.getMemory().dataMemoryToString());
				updateCurrentInstruction(sic.getInstruction().getCurrentInstruction());
			}
		});
		add(btnStep);

		// MEMORIA DE INSTRUCOES
		JLabel instructionMemoryLabel = new JLabel("Instruction Memory");
		instructionMemoryLabel.setForeground(new Color(0, 0, 0));
		instructionMemoryLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		instructionMemoryLabel.setBounds(21, 275, 175, 23);
		add(instructionMemoryLabel);

		instructionMemoryText = new JTextPane();
		instructionMemoryText.setEditable(false);
		instructionMemoryText.setForeground(Color.BLACK);
		instructionMemoryText.setBackground(Color.WHITE);
		JScrollPane instructionMemory = new JScrollPane(
			instructionMemoryText,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		instructionMemory.setBounds(21, 297, 408, 98);
		add(instructionMemory);

		// MEMORIA DE DADOS
		JLabel dataMemoryLabel = new JLabel("Data Memory");
		dataMemoryLabel.setForeground(new Color(0, 0, 0));
		dataMemoryLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		dataMemoryLabel.setBounds(21, 420, 100, 23);
		add(dataMemoryLabel);

		dataMemoryText = new JTextPane();
		dataMemoryText.setEditable(false);
		dataMemoryText.setForeground(Color.BLACK);
		dataMemoryText.setBackground(Color.WHITE);
		JScrollPane dataMemory = new JScrollPane(
			dataMemoryText,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		dataMemory.setBounds(21, 440, 408, 98);
		add(dataMemory);

		textRegA = new JTextArea();
		textRegA.setEditable(false);
		textRegA.setBounds(37, 188, 66, 22);
		add(textRegA);

		textRegX = new JTextArea();
		textRegX.setEditable(false);
		textRegX.setBounds(140, 188, 66, 22);
		add(textRegX);

		textRegL = new JTextArea();
		textRegL.setEditable(false);
		textRegL.setBounds(243, 188, 66, 22);
		add(textRegL);

		textRegB = new JTextArea();
		textRegB.setEditable(false);
		textRegB.setBounds(346, 188, 66, 22);
		add(textRegB);

		textRegPC = new JTextArea();
		textRegPC.setEditable(false);
		textRegPC.setBounds(243, 233, 66, 22);
		add(textRegPC);

		textRegSW = new JTextArea();
		textRegSW.setEditable(false);
		textRegSW.setBounds(346, 233, 66, 22);
		add(textRegSW);

		textRegT = new JTextArea();
		textRegT.setEditable(false);
		textRegT.setBounds(140, 233, 66, 22);
		add(textRegT);

		textRegS = new JTextArea();
		textRegS.setEditable(false);
		textRegS.setBounds(37, 233, 66, 22);
		add(textRegS);

		JLabel lblRegisters = new JLabel("Registers");
		lblRegisters.setForeground(new Color(0, 0, 0));
		lblRegisters.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegisters.setBounds(176, 142, 72, 19);
		add(lblRegisters);

		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblA.setBounds(66, 172, 8, 14);
		add(lblA);

		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblX.setBounds(169, 172, 8, 14);
		add(lblX);

		JLabel lblL = new JLabel("L");
		lblL.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblL.setBounds(272, 172, 8, 14);
		add(lblL);

		JLabel lblB = new JLabel("B");
		lblB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblB.setBounds(375, 172, 8, 14);
		add(lblB);

		JLabel lblS = new JLabel("S");
		lblS.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblS.setBounds(66, 218, 8, 14);
		add(lblS);

		JLabel lblT = new JLabel("T");
		lblT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblT.setBounds(169, 218, 8, 14);
		add(lblT);

		JLabel lblPc = new JLabel("PC");
		lblPc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPc.setBounds(269, 218, 14, 14);
		add(lblPc);

		JLabel lblSw = new JLabel("SW");
		lblSw.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSw.setBounds(370, 218, 18, 14);
		add(lblSw);

		// Current Instrcution
		JLabel currentInstructionLabel = new JLabel("Current Instruction");
		currentInstructionLabel.setForeground(Color.BLACK);
		currentInstructionLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		currentInstructionLabel.setBounds(276, 76, 175, 19);
		add(currentInstructionLabel);

		currentInstructionText = new JTextArea();
		currentInstructionText.setEditable(false);
		currentInstructionText.setBounds(272, 100, 111, 22);
		add(currentInstructionText);
	}

	protected void updateInstructionMemory(String mem) {
		instructionMemoryText.setText(mem);
	}
	protected void updateDataMemory(String mem) {
		dataMemoryText.setText(mem);
	}

	protected void updateReg(Register r) {
		textRegA.setText(Integer.toString(r.get("A")));
		textRegX.setText(Integer.toString(r.get("X")));
		textRegL.setText(Integer.toString(r.get("L")));
		textRegB.setText(Integer.toString(r.get("B")));
		textRegS.setText(Integer.toString(r.get("S")));
		textRegT.setText(Integer.toString(r.get("T")));
		textRegPC.setText(Integer.toString(r.get("PC")));
		textRegSW.setText(Integer.toString(r.get("SW")));
	}

	protected void updateCurrentInstruction(String instruction) {
		currentInstructionText.setText(instruction);
	}
}
