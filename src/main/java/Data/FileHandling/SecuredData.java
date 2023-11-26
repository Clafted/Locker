package Data.FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Scanner;

import Data.Security.EncryptorDecryptor;

/**
 * A class to represent data which would be secured with encryption or decryption.
 * @author noahm
 *
 */
public class SecuredData implements Serializable{

	public static final long serialVersionUID = 329048;

	public enum lockType{ COMBINATION, PERMUTATION, STRING}
	private final String key;
	public final String fileName;
	private final File file;
	private final lockType type;

	public SecuredData(lockType type, String key, File file) throws FileNotFoundException{

		this.type = type;
		this.key = key;
		this.file = file;
		fileName = file.getName();
	}

	public String getContent() {

		// Adds content of the file into a string
		String content = "";
		try {

			Scanner scanner = new Scanner(file);

			while(scanner.hasNext())
				content += scanner.nextLine();

			scanner.close();
		}catch(Exception e) {

			e.printStackTrace();
		}
		
		return content;
	}

	public String toString() {

		// Gets the type of the key
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

		// Adds content of the file into a string
		String content = "";
		try {

			Scanner scanner = new Scanner(file);

			while(scanner.hasNext())
				content += scanner.nextLine();

			scanner.close();
		}catch(Exception e) {

			e.printStackTrace();
		}

		return "NAME: " + fileName + "\nKEY: " + key + "\tType: " + typeName + "\nPATH: " + file.getAbsolutePath() + "\nCONTENT: " + content;
	}
	
	public void encryptData(String key, EncryptorDecryptor enryptionAlgorithm) {
		
		try {
			
			Scanner fileReader = new Scanner(file);
			
			String content = "";
			
			while (fileReader.hasNext())
				content += fileReader.next();
			
			fileReader.close();
			
			content = enryptionAlgorithm.encrypt(content, key);
			
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
}
