package ASSEMBLER;
import java.util.ArrayList;

public class Linker {
	//Array list -  each element is an objCode
	ArrayList<ArrayList<String>> objCodes;

	public Linker(ArrayList<ArrayList<String>> objCodes) {
		this.objCodes = objCodes;
	}

	//Method to link objCodes
	public ArrayList<String> linkCodes(	){

		ArrayList<String> obj;
		ArrayList<String> mainCode = objCodes.get(0);

		//if there is more than one objCode, link then in the first objcode
		for (int i = 1; i < objCodes.size(); i++) {

			obj = objCodes.get(i);
			//Set new size in the main header
			mainCode.set(0, incrementSize(mainCode.get(0), obj.get(0)));

			//remove unnecessary header
			obj.remove(0);
			//remove unnecessary end of objcode
			obj.remove(obj.size()-1);

			//link
			mainCode.addAll(mainCode.size()-2, obj);

		}
		return mainCode;
	}
	// Method to set new header size
	public String incrementSize(String head, String head2) {

		int size =  Integer.parseInt(head.substring(14, 19), 16) + Integer.parseInt(head2.substring(14, 19), 16);
		String newSize = Integer.toHexString(size);

		//if size overflow
		if (newSize.length() > 6)
			newSize = newSize.substring(0, 5);
		else
			//complete 6 byte size with 0s
			while((newSize.length() < 6))
				newSize = "0" + newSize;

		return head.substring(0,13) + newSize;
 	}
}
