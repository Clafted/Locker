package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class DataManager 
{
	private static DataManager instance;

	private static File lockerFolder;
	private static File passwords;

	private static HashMap<String, String> files;

	private enum lockType{ COMBINATION, PERMUTATION, STRING}

	private DataManager()
	{
		System.out.println("Creating DataManager");
		
		try {
			setupApp();
			getData();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	// Singleton pattern instance access method
	public static DataManager Instance()
	{
		if(instance == null) instance = new DataManager();
		return instance;
	}

	// Creates necessary directories and files for the application
	private void setupApp() throws Exception
	{
		lockerFolder = new File(System.getProperty("user.home") + "/AppData/Local/Locker");
		passwords = new File(lockerFolder.getPath() + "/passwords.txt");

		// Creates and confirms Locker directory creation
		if(lockerFolder.mkdir())
			System.out.println("Creating new directory");
		else 
			System.out.println("Using existing directory");

		// Creates and confirms passwords.txt file creation
		if(passwords.createNewFile())
			System.out.println("Successfully created new passwords file");
		else
			System.out.println("Unable to create a passwords file");
	}

	// Make any necessary updates to the files Object, including codes and files
	@SuppressWarnings("unchecked") 
	private void getData() throws ClassNotFoundException, IOException 
	{
		
		if(passwords == null || passwords.length() == 0)
		{
			System.out.println("Nothing to read");
			return;
		}
		
		// Instantiate the files Object with any existing HashMap Objects from the file
		ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(passwords));
		files = (HashMap<String, String>)oIS.readObject();
		oIS.close();
	
		// Prints out the file-paths and their codes
		if(files == null) return;
		System.out.println();
		for(String key : files.keySet())
			System.out.println("File Path: " + key + "\t" + "Code: " + files.get(key));
	}

	// Adds a file of a certain lockType, secured with a code
	public void addSecuredFile(lockType type, String lockedFilePath, String code) throws IOException
	{
		if(files == null) files = new HashMap<String, String>(); // Ensures that files is instantiated
		files.put(lockedFilePath, code); // Adds the code to the files HashMap
		
		// Write the updated files Object into the passwords file
		ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(passwords));
		fileWriter.writeObject(files);
		fileWriter.close();
	}
	
}
