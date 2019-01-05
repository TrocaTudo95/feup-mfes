package gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.overture.codegen.runtime.VDMSet;


import mfes.Facebook;
import mfes.Post;
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
			facebook.register(name,date2,email,password);

			userMenu();	
	 }
	 
	 
	 private static void userMenu() {
		 int option=0;
		 while(option<1 ||option >8) {

			clearScreen();
			System.out.println("#################################################################");
			System.out.println("############                Welcome                  ############");
			System.out.println("#################################################################\n\n");
			System.out.println("1 - Show Feed");
			System.out.println("2 - Show Recomendations");
			System.out.println("3 - Show Friends");
			System.out.println("4 - Show Profile");
			System.out.println("5 - Show Friend Requests");
			System.out.println("6 - Add Post");
			System.out.println("7 - Show Users");
			System.out.println("8 - Logout");
			System.out.print("\nOption: ");
			option= getNextChoice();
		 }
		 
		 clearScreen();
		
		 switch(option) {
		 case 1:
			showFeed();
			break;
		 case 2:
			 showUsers(facebook.recommendUsers());
			 break;
		 case 3:
			 break;
		 case 4:
			 showProfile(facebook.getCurrentUser());
			 break;
		 case 5:
			 break;
		 case 6:
			 addPost();
			 break;
			 
		 case 7:
			 showUsers(facebook.getUsers());
			 break;
		 case 8:
			 logout();
			 initialMenu();
			 break;
	 	}
	}
	 
	 private static User getSelectedUser(int i, VDMSet users ) {
		  Iterator<User> it=users.iterator();
		  int option=1;
		  while(it.hasNext()) {
			  //User auxUser = it.next();
			  User u =it.next();
			  if(option== i)
				  return  u;
			  option++;
		  }
		  return null;
	 }
	 
	
	 private static void showFriends( VDMSet users) {
			
		  Iterator<User> it=users.iterator();
		 int option=1;
		  while(it.hasNext()) {
			  User u = (User) it.next();
			  System.out.print(option +"-");
			  System.out.println(u);
			  option ++;
			  
		  }
		  option = getNextChoice();
		  User u = getSelectedUser(option, users);
		  option=0;
		  
		  while(option<1 ||option >3) {
		  System.out.println("1 - Show Profile");
		  System.out.println("2 - Remove Friend");
		  System.out.println("3 - Back");
		  option = getNextChoice();
		  }
		  
		  switch(option) {
			 case 1:
				 showProfile(u);
				 break;
			 case 2:
				 facebook.currentUser.unfriend(u);
				 userMenu();
				 break;
			 case 3:
				 userMenu();
				 break;
			
			 }
			 
		  
		
	}
	private static void showUsers( VDMSet users) {
		
		  Iterator<User> it=users.iterator();
		 int option=1;
		  while(it.hasNext()) {
			  User u = (User) it.next();
			  System.out.print(option +"-");
			  System.out.println(u);
			  option ++;
			  
		  }
		  option = getNextChoice();
		  User u = getSelectedUser(option, users);
		  option=0;
		  
		  while(option<1 ||option >3) {
		  System.out.println("1 - Add Friend");
		  System.out.println("2 - Show Profile");
		  System.out.println("3 - Back");
		  option = getNextChoice();
		  }
		  
		  switch(option) {
			 case 1:
				facebook.currentUser.addFriend(u);
				userMenu();
				break;
			 case 2:
				 showProfile(u);
				 break;
			 case 3:
				 userMenu();
				 break;
			
			 }
			 
		  
		
	}

	private static void showProfile(User user) {
		System.out.println("#################################################################");
		System.out.println("############                Profile                  ############");
		System.out.println("#################################################################\n");
		
		System.out.println("Name: " + user.getName());
		System.out.println("Email: " + user.getEmail());
		System.out.println("Birthdate: " + user.getBirthday());
		
		System.out.println("\n##########################    Post    ##########################\n");
		
		Iterator<Post> it = user.getPosts(facebook.getCurrentUser()).iterator();
		
	      while(it.hasNext()) {
	    	  Post aux = it.next();
	    	  System.out.println(aux);
	      }
		
		System.out.println("PRESS ANY KEY TO GO BACK");
		readStringfromKeyBoard();
		userMenu();
	}

	private static void addPost() {
		System.out.println("#################################################################");
		System.out.println("############                Add Post                 ############");
		System.out.println("#################################################################\n\n");
		
		String content;
		String permission;
		
		System.out.println("Contents:");
		content= readStringfromKeyBoard() ;
		System.out.println("Permissions (public/family):");
		permission = readStringfromKeyBoard();
		
		facebook.getCurrentUser().addPost(content, permission);
		
		userMenu();
		
	}

	private static void showFeed() {
		System.out.println(facebook.getFeed());
		
	}
	
	public static void logout() {
		facebook.logout();
	}

	public static void initialMenu() {
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
			userMenu();
		
	}

		public static void main(String [] args) {
			initialMenu();
	 }
}
