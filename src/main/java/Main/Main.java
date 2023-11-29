package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import data.filehandling.DataManager;
import data.filehandling.SecuredData;
import data.filehandling.SecuredData.lockType;
import data.security.CaesarCipher;
import data.security.EncryptorDecryptor;
import data.security.EncryptorDecryptorFactory;
import data.security.MonoAlphabeticCipher;

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
					+ "\n- encrypt [encryption method] [key] [fileName] : encrypt the file with the given fileName"
					+ "\n- decrypt [decryption method] [key] [fileName] : decrypt the file with the given fileName"
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
				if (input[1].equals("COMBINATION")) {
					type = lockType.COMBINATION;
				} else if (input[1].equals("PERMUTATION")) {
					type = lockType.PERMUTATION;
				} else if (input[1].equals("STRING")) {
					type = lockType.STRING;
				} else { 
					return;
				}
				
				// Saves public information of the secured data
				try {
					DataManager.Instance().addSecuredData(new SecuredData(type, key, new File(path)));
				} catch(IOException e) {
					System.out.println("Could not find specified file!\n");
				}
			} else {
				System.out.println("To add a file, use the command \"add [lockType (COMBINATION/PERMUTATION/STRING)] [key] [path]\"\n");
			}
		} else if (input[0].equals("encrypt") || input[0].equals("decrypt")) {
			
			if(input.length == 4) {
				
				try {
					EncryptorDecryptor algorithm = EncryptorDecryptorFactory.makeEncryptorDecryptor(input[1]);
					
					if (algorithm != null) {
						if (DataManager.Instance().getSecuredData(input[3]).convertData(input[2], algorithm, input[0].equals("encrypt"))) {
							System.out.println("Conversion complete!");
						} else {
							System.out.println("Unable to complete conversion :(");
						}
					} else {
						System.out.println("\nUse one of the available methods:"
								+ "\n- caesar"
								+ "\n- monoalphabetic"
								+ "\n");
					}
				} catch (NumberFormatException e) {
					System.out.println("That is not a number\n");
				}
				
			} else
				System.out.println("To encrypt or decrypt a file, use the command \"encrypt/decrypt [method] [key] [fileName]\"");
			
		} else if (input[0].equals("exit")) {
			System.out.println("Goodbye!");
		} else {
			System.out.println("That is not one of the actions!\n");
		}
	}
}
