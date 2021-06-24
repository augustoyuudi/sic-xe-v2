import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Code {
	private String code = "";

	public Code() {
		readInputFile();
	}

	private void readInputFile() {
		try {
			File inputFile = new File("./input.asm");
      Scanner reader = new Scanner(inputFile);

      while (reader.hasNextLine()) {
				code += reader.nextLine() + "\n";
      }

			// code = code.substring(0, code.length() - 1);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getCode() {
		return code;
	}
}
