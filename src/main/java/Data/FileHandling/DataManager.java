package data.filehandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * A Singleton class to handle files specific to the Locker application.
 * 
 * Upon instantiation of this class, a directory and initial files related to 
 * the Locker application will be created. As a part of handling the files, the
 * class is what connects the user's file-system with the application, providing
 * access to encrypted and decrypted files.
 * <br><br>
 * This class does not have any encryption of decryption functionality, leaving
 * that task to subclasses of the Security class.
 * 
 * @author noahm
 *
 */
public class DataManager 
{
	private static DataManager instance;
	private static File lockerFolder;
	private static File dataFile;
	private static ArrayList<SecuredData> securedData;


	private DataManager(){
		System.out.println("Creating DataManager");

		try {
			setupApp();
			loadData();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * The singleton instance method to access the single instance of the DataManager class.
	 * 
	 * @return a new or existing DataManager instance
	 */
	public static DataManager Instance(){

		if(instance == null) instance = new DataManager();
		return instance;
	}

	/**
	 * Creates necessary directories and files for the application
	 * 
	 * @throws Exception
	 */
	private void setupApp() throws Exception {

		lockerFolder = new File(System.getProperty("user.home") + "/AppData/Local/Locker");
		dataFile = new File(lockerFolder.getPath() + "/dataFile.txt");

		// Creates and confirms Locker directory creation
		if(lockerFolder.mkdir()) {
			System.out.println("Creating new directory");
		} else { 
			System.out.println("Using existing directory");
		}
		// Creates and confirms passwords.txt file creation
		if(dataFile.createNewFile()) {
			System.out.println("Successfully created new passwords file");
		} else {
			System.out.println("Unable to create a passwords file");
		}
		
		// Prints out the file-paths and their codes
		if(securedData == null) securedData = new ArrayList<SecuredData>();
	}
	
	/**
	 *  Adds a file of a certain lockType, secured with a code
	 *  
	 * @param type
	 * @param lockedFilePath
	 * @param code
	 * @throws IOException
	 */
	public void addSecuredData(SecuredData target){

		securedData.add(target); 

		// Write the updated files Object into the passwords file
		try {
			ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(dataFile));
			fileWriter.writeObject(securedData);
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public SecuredData getSecuredData(String fileName) {
		
		for (SecuredData file : securedData) {
			if(file.fileName.equals(fileName)) {
				return file;
			}
		}
		
		System.out.println("That file does not exist!");
		return null;
	}
	
	public String getSecuredDataInfo(String fileName) {
		
		for (SecuredData file : securedData) {
			if(file.fileName.equals(fileName)) {
				return file.toString();
			}
		}
		
		return "That file does not exist!";
	}
	
	/**
	 * Load saved securedData.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked") 
	private void loadData() throws ClassNotFoundException, IOException {

		if(dataFile == null 
				|| dataFile.length() == 0) {
			System.out.println("Nothing to read");
			return;
		}

		// Instantiate the files Object with any existing HashMap Objects from the file
		ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(dataFile));
		securedData = (ArrayList<SecuredData>)oIS.readObject();
		
		oIS.close();
	}

	public String listData() {
		
		String list = "";
		
		for (int i = 0; i < securedData.size(); i++) {
			list += "\n" + (i + 1) + ".\n" + securedData.get(i) + "\n" ;
		}
		
		return list;
	}
}
