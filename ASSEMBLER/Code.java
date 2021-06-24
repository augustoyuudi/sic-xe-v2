package ASSEMBLER;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Code {
	private String code = "";

	public String readInputFile(String filePath) {
		
		try {
			File inputFile = new File(filePath);
			Scanner reader = new Scanner(inputFile);

			while (reader.hasNextLine()) 
				code += reader.nextLine() + "\n";
			
			reader.close();
			
		} catch(FileNotFoundException e) { 
			e.printStackTrace();
		}

		return code;
	}
}
