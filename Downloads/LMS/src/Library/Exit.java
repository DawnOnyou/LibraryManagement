package Library;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Exit implements IOOperation{
	
	static private final Pattern EMAILPATTERN = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$");
	Main x = new Main();
	Scanner s;
	Database database;
	User user;
	
	@Override
	public void oper(Database database, User user) {
		this.database = database;
		this.user = user;
		
		System.out.println("Are you sure that you want to quit?\n"
				+ "[1]. Yes\n[2]. Main Menu");
		
		Scanner s = new Scanner(System.in);
		int i = s.nextInt();
		s.nextLine();

		
		if(i==1) {
			
			//
			System.out.println("Library Management System\n"
					+"[0]. Exit\n"	+ "[1]. Login\n[2]. New User");
				s = new Scanner(System.in);
				int num = s.nextInt();
				s.nextLine();
			
				
				switch(num) {
					case 0:
		            System.out.println("\nThank You For Your Patronage!");
		            break;
					case 1: login(); break;
					case 2: newUser(); break;
					default:
						System.out.println("Invalid Input !!");
						x.main(null);
						
		  }
		 }	else {
			 
			user.menu(database, user);
		 }
	}	
 
		
		private  void login() {
			Scanner s = new Scanner(System.in);
			System.out.println("\nEnter email: ");
			String email = s.nextLine();
			System.out.println("\nEnter Password: ");
			String phonenumber = s.nextLine(); 
			int n = database.login(phonenumber, email);
			if(n != -1) {
				User user = database.getUser(n);
				user.menu(database, user);
			}else {
				System.out.println("\nUser doesn't exist!\n");
				
			}
		}
		
		private static boolean  isValidEmail(String email) {
	        return EMAILPATTERN.matcher(email).matches();
	    }
		
		private  void newUser() { 
			
			//static
			Scanner s = new Scanner(System.in);
			System.out.println("Enter name: ");
			String name = s.nextLine();
			System.out.println("Enter Password: ");
			String phonenumberString = s.nextLine();
			
			String email = "";
			
			
			do {
				System.out.print("\nEnter email: ");
				 email = s.nextLine();
				 
				 if(!isValidEmail(email)) {
					 System.out.println("\nInvalid email address!"); 
				 }
			}while(!isValidEmail(email));
			
			
			
			
			
			
			
			System.out.println("\n[1]. Librarian\n[2]. Student");
			int n2 = s.nextInt();
			s.nextLine();
			User user = null; 
			if (n2 == 1) {
				System.out.println("\nYou Need to enter the secret key: ");
				int n = s.nextInt();
				s.nextLine(); 
				if(n == 0000) {
					System.out.println("\nYou have successfully created an account! ");
					user = new Admin(name, email, phonenumberString);
				} else {
				
					System.out.println("Incorrect secret key!\n");
					x.main(null);
				}
			} else {
				System.out.println("\nYou have successfully created an account! \n");
				user = new NormalUser(name, email, phonenumberString);
			}
			if(user != null) { 
				Database.AddUser(user);
				user.menu(database, user);
			}}
	
			
		}
		


