package Data.FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class SecuredData implements Serializable{
	
	public static final long serialVersionUID = 329048;
	
	public enum lockType{ COMBINATION, PERMUTATION, STRING}
	private String key;
	private File file;
	private lockType type;
	
	public SecuredData(lockType type, String key, String path) throws FileNotFoundException{
		
		this.type = type;
		this.key = key;
		file = new File(path);
	}
	
	public String toString() {
		
		String typeName = "";
		switch(this.type) {
		case COMBINATION:
			typeName = "Combination";
			break;
		case PERMUTATION:
			typeName = "Permutation";
			break;
		case STRING:
			typeName = "String";
			break;
		}
		
		String content = "";
		try {
			
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNext())
				content += scanner.nextLine();
			
			scanner.close();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return "KEY: " + key + "\tType: " + typeName + "\nPATH: " + file.getAbsolutePath() + "\nCONTENT: " + content;
	}
}
