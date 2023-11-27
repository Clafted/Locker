package Data.FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

		// Appends the content of the file to a String variable
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
	
	/**
	 * Encrypt the contents of the file using the given encryption algorithm.
	 * <br><br>
	 * This method assumes that the file exists, although error messages will
	 * be printed to the console if any errors occur.
	 * 
	 * @param key the key used to encrypt the data
	 * @param enryptionAlgorithm the algorithm used to cipher the data
	 * @param encrypt "true" for encryption, "false" for decryption
	 */
	public boolean convertData(String key, EncryptorDecryptor enryptionAlgorithm, boolean encrypt) {
		
		try {
			
			Scanner fileReader = new Scanner(file);
			String content = "";

			// Save all contents of the file to a String variable
			while (fileReader.hasNext())
				content += fileReader.nextLine();
			fileReader.close();
			
			content = ((encrypt) ? enryptionAlgorithm.encrypt(content, key) : enryptionAlgorithm.decrypt(content, key));

			// Write the converted data into the file
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			
			writer.close();
			return true;
			
		} catch (FileNotFoundException e) {
			
			System.out.println("The file \"" + fileName + "\" could not be found!");
			e.printStackTrace();
			return false;
			
		} catch (IOException e) {
			
			System.out.println("There was an error trying to access the file \"" + fileName + "\"");
			e.printStackTrace();
			return false;
		}
		
		
	}
}
