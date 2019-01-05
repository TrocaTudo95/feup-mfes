package gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import mfes.Facebook;
import mfes.User;


public class Gui {
	 private static Facebook facebook = new Facebook();
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
		 String name,email,password,date;
		 User.Date date2 ;
		 clearScreen();
			System.out.println("#################################################################");
			System.out.println("############               Register                  ############");
			System.out.println("#################################################################\n\n");
			System.out.println("Insert your Name:");
			name= readStringfromKeyBoard() ;
			System.out.println("Insert your Email:");
			email= readStringfromKeyBoard() ;
			System.out.println("Insert your Password:");
			password= readStringfromKeyBoard() ;
			System.out.println("Insert your birthday date (YYYY/MM/DD):");
			date= readStringfromKeyBoard() ;
			date2 =new User.Date(date);
			User user = facebook.register(name,date2,email,password);

			userMenu(user);	
	 }
	 
	 
	 private static void userMenu(User user) {
		 int option=0;
		 while(option<1 ||option >3) {
			clearScreen();
			System.out.println("#################################################################");
			System.out.println("############                Welcome                  ############");
			System.out.println("#################################################################\n\n");
			System.out.println("1 - Show Feed");
			System.out.println("2 - Show Recomendations");
			System.out.println("3 - Show Friends");
			System.out.println("4 - Show Profile");
			System.out.println("5 - Show Friend Requests");
			System.out.println("6 - Logout");
			System.out.print("\nOption: ");
			option= getNextChoice();
		 }
		 
		 clearScreen();
		
		 switch(option) {
		 case 1:
			showFeed();
			break;
		 }
	}

	private static void showFeed() {
		System.out.println(facebook.getFeed());
		
	}
	
	public static void Logout() {
		facebook.logout();
	}

	public static void initalMenu() {
		 int option=0;
		 while(option<1 ||option >2) {
		 clearScreen();
			System.out.println("#################################################################");
			System.out.println("############               Register                  ############");
			System.out.println("#################################################################\n\n");
			System.out.println("1 - Register");
			System.out.println("2 - Login");
			System.out.print("\nOption: ");
			option= getNextChoice();
	 }
		 
		 switch(option) {
		 case 1:
			registerMenu();
			break;
		 case 2:
			 loginMenu();
			 break;
		
		 }
		 
	 }
	 
		private static void loginMenu() {
			String email,password;
			clearScreen();
			System.out.println("#################################################################");
			System.out.println("############                 Login                   ############");
			System.out.println("#################################################################\n\n");
			System.out.println("Insert your Email:");
			email= readStringfromKeyBoard() ;
			System.out.println("Insert your Password:");
			password = readStringfromKeyBoard();
			
			User user = facebook.login(email, password);
			clearScreen();
			userMenu(user);
		
	}

		public static void main(String [] args) {
			initalMenu();
	 }
}
