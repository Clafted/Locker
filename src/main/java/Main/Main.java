package Main;

import Data.FileHandling.DataManager;
import Data.FileHandling.SecuredData;
import Data.FileHandling.SecuredData.lockType;

public class Main 
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to the MLH Data Hackathon!");
		DataManager.Instance();
		
		try {
			
			DataManager.Instance().addSecuredData(new SecuredData(lockType.COMBINATION, "2251", "C:\\Users\\noahm\\OneDrive\\Desktop\\Data.txt"));
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
}
