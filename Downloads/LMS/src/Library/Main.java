package Library;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	
	static Scanner s;
	static Database database;
	static private final Pattern EMAILPATTERN = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$");

	public static void main(String[] args) {
		
	  database = new Database();
		
		int num;
			
			System.out.println("\n===============================");
			System.out.println("=  Library Management System  =");
			System.out.println("===============================");
			System.out.println("\n[0]. Exit\n"	+ "[1]. Login\n[2]. New User\n");
				s = new Scanner(System.in);
				try {
				
				num = s.nextInt();
				s.nextLine(); 
					
				switch(num) {
					case 0:
		            System.out.println("\nThank You For Your Patronage!");
		            break;
					case 1: login(); break;
					case 2: newUser(); break;
					default:
						System.out.println("\nInvalid Input! ");
						main(args);
					
				}
				
				} catch (InputMismatchException e) {
			        System.out.println("\nInvalid input. Please enter a number.");
			       main(args);
				}
				
		
	}

	private static void login() {
		
		 
		System.out.print("Enter email: ");
		String email = s.nextLine();
		System.out.print("\nEnter Password: ");
		String phonenumber = s.nextLine();
		int n = database.login(phonenumber, email);
		if(n != -1) {
			User user = database.getUser(n);
			user.menu(database, user);
		} else {
			System.out.println("User doesn't exist!");
			main(null);
		}
	}
	
	
	private static boolean  isValidEmail(String email) {
        return EMAILPATTERN.matcher(email).matches();
    }

	private static void newUser() { 
		System.out.print("Enter name: ");
		String name = s.nextLine();
		if(database.userExist(name)) {
			System.out.println("User exists! ");
			newUser();
		}
		System.out.print("Enter Password: ");
		String phonenumberString = s.nextLine();
		
		String email = "";
		
		
		do {
			System.out.print("Enter email: ");
			 email = s.nextLine();
			 
			 if(!isValidEmail(email)) {
				 System.out.println("Invalid email address!"); 
			 }
		}while(!isValidEmail(email));
		
		
		
		System.out.println("\n[1]. Librarian\n[2]. Student");
		int n2 = s.nextInt();
		s.nextLine();
		User user = null; 
		if (n2 == 1) {
			System.out.println("You Need to enter the secret key(Enter 1 to go back): ");
			int n = s.nextInt();
			s.nextLine(); 
			if(n == 1) {
				main(null);
			} 
			else if(n == 0000) {
				System.out.println("\nYou have successfully created an account! ");
				user = new Admin(name, email, phonenumberString);
			} else {
			
				System.out.println("Incorrect secret key!");
				main(null);
			}
		} else {
			System.out.println("You have successfully created an account! \n");
			user = new NormalUser(name, email, phonenumberString);
		}
		if(user != null) { 
			Database.AddUser(user);
			user.menu(database, user);
		}
		
		
	}
}
