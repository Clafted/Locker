package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
			getCodes();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static DataManager Instance()
	{
		if(instance == null) instance = new DataManager();
		return instance;
	}

	private void setupApp() throws Exception
	{
		lockerFolder = new File(System.getProperty("user.home") + "/AppData/Local/Locker");
		passwords = new File(lockerFolder.getPath() + "/passwords.txt");

		if(lockerFolder.mkdir())
			System.out.println("Creating new directory");
		else 
			System.out.println("Using existing directory");

		if(passwords.createNewFile())
			System.out.println("Successfully created new passwords file");
		else
			System.out.println("Unable to create a passwords file");
	}

	@SuppressWarnings("unchecked") 
	private void getCodes() throws ClassNotFoundException, IOException 
	{
		if(passwords == null)
		{
			System.out.println("No passwords file exists :(");
			return;
		}else if(passwords.length() == 0)
		{
			System.out.println("No files to read");
			return;
		}
		
		// Instantiate the files Object with any existing HashMap Objects from the file
		ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(passwords));
		files = (HashMap<String, String>)oIS.readObject();
		oIS.close();
		
		if(files == null) return;
		System.out.println();
		for(String key : files.keySet())
			System.out.println("File Path: " + key + "\t" + "Code: " + files.get(key));
	}

	public void addCode(lockType type, String lockedFilePath, String code) throws IOException
	{
		if(files == null) files = new HashMap<String, String>();
		files.put(lockedFilePath, code);
		
		// Write the updated files Object into the passwords file
		ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(passwords));
		fileWriter.writeObject(files);
		fileWriter.close();
	}
}
