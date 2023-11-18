package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Data.DataManager;

public class Main 
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to the MLH Data Hackathon!");
		DataManager.Instance();
		
		JFrame frame = new JFrame();
		frame.setSize(800, 500);
		frame.setTitle("Title");
		frame.setVisible(true);
	}
	
	
}
