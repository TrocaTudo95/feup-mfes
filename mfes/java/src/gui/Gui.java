package gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import mfes.Facebook;


public class Gui {
	 private Facebook facebook = new Facebook();
	 static BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
	 
	 
	 
	 
	 
	 public static void clearScreen() {  
		    for(int i = 0; i < 50; i++)
		    	System.out.println("");
	}
	 
	 public static int getNextChoice() {	
			int usrInput = -1;
			try {
				usrInput = Integer.parseInt(buffer.readLine());
			} catch (Exception e) {}
			return usrInput;
	}
	 
	 public static String readStringfromKeyBoard() {	
			String usrInput = "";
			try {
				usrInput =buffer.readLine();
			} catch (Exception e) {}
			return usrInput;
	}
	 public static void registerMenu() {
		 String name,email,password;
		 clearScreen();
			System.out.println("#################################################################");
			System.out.println("############               Facebook                  ############");
			System.out.println("#################################################################\n\n");
			System.out.println("Insert your Name:");
			name= readStringfromKeyBoard() ;
			System.out.println("Insert your Email:");
			email= readStringfromKeyBoard() ;
			System.out.println("Insert your Password:");
			email= readStringfromKeyBoard() ;
			
			
			
			
	 }
	 
	 
	 public static void initalMenu() {
		 int option=0;
		 while(option<1 ||option >3) {
		 clearScreen();
			System.out.println("#################################################################");
			System.out.println("############               Facebook                  ############");
			System.out.println("#################################################################\n\n");
			System.out.println("1 - Register");
			System.out.println("2 - Login");
			System.out.println("3 - Logout");
			option= getNextChoice();
		
	 
	 }
		 
		 switch(option) {
		 case 1:
			registerMenu();
			break;
	 }
	 }
	 
		public static void main(String [] args) {
			initalMenu();
	 }
}
