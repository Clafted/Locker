package Main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Data.FileHandling.DataManager;
import Data.FileHandling.SecuredData;
import Data.FileHandling.SecuredData.lockType;
import Data.Security.CaesarCipher;

public class Main 
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to the MLH Data Hackathon! Type -h for help");
		Scanner scanner = new Scanner(System.in);
		String[] inputs = {};
				
		while (!(inputs.length > 0 && inputs[0].equals("exit"))) {
			
			System.out.print("Command: ");			
			inputs = scanner.nextLine().split(" ");
			handleInput(inputs);
		}
		
		scanner.close();
		System.exit(0);
	}
	
	/**
	 * Uses back-end functionality using a given set of inputs.
	 * <br><br>
	 * The inputs must follow the commands formats available
	 * 
	 * @param input the actions the user wants to make.
	 */
	public static void handleInput(String[] input) {
		
		if (input[0].equals("-h")) {
			
			System.out.println(
					"\nAvailable actions:"
					+ "\n- list : list all saved files"
					+ "\n- add [lockType (COMBINATION/PERMUTATION/STRING)] [key] [path] : add a file to secure"
					+ "\n- encrypt [key] [fileName] : encrypt the file with the given fileName"
					+ "\n- decrypt [key] [fileName] : decrypt the file with the given fileName"
					+ "\n- exit : close the program"
					+ "\n");
		} else if (input[0].equals("list")) {
			
			System.out.println(DataManager.Instance().listData());
			
		} else if(input[0].equals("add")) {
			
			if (input.length == 4) {
				
				String key = input[2];
				String path = input[3];
				lockType type;
				
				// Determine how a key was generated
				if (input[1].equals("COMBINATION"))
					type = lockType.COMBINATION;
				else if (input[1].equals("PERMUTATION"))
					type = lockType.PERMUTATION;
				else if (input[1].equals("STRING"))
					type = lockType.STRING;
				else 
					return;
				// Saves public information of the secured data
				try {
					DataManager.Instance().addSecuredData(new SecuredData(type, key, new File(path)));
				} catch(IOException e) {
					System.out.println("Could not find specified file!\n");
				}

			} else
				System.out.println("To add a file, use the command \"add [lockType (COMBINATION/PERMUTATION/STRING)] [key] [path]\"\n");
			
		} else if (input[0].equals("encrypt") || input[0].equals("decrypt")) {
			
			if(input.length == 3) {
			
				try {
					if (DataManager.Instance().getSecuredData(input[2]).convertData(input[1], new CaesarCipher(), input[0].equals("encrypt")))
						System.out.println("Conversion complete!");
					else
						System.out.println("Unable to complete conversion :(");
					
				} catch (NumberFormatException e) {
					System.out.println("That is not a number\n");
				}
				
			} else
				System.out.println("To encrypt a file, use the command \"encrypt [key] [fileName]\"");
			
		} else if (input[0].equals("exit"))
			System.out.println("Goodbye!");
		else
			System.out.println("That is not one of the actions!\n");
	}
}
