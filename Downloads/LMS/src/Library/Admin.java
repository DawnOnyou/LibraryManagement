package Library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User {
	
	
	
	public Admin(String name) {
		super(name);
		
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new AddBook(),
				new Search(),
				new BorrowedBooks(),				
				new Exit()
		
				 
			};
		
	}
	
	public Admin(String name, String email, String phonenumber) {
		super(name, email, phonenumber);
		
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new AddBook(),
				new Search(),
				new BorrowedBooks(),
				new Exit()
				
				 
			};
		
	}
	
	@Override
	public void menu(Database database, User user) {
		System.out.println("\n===============================");
		System.out.println("   Library Management System");
		System.out.println("===============================");
		System.out.println("        ADMIN MODE    ");
		System.out.println("\n[1]. View Books");
		System.out.println("[2]. Add Books");
		System.out.println("[3]. Search");
		System.out.println("[4]. Borrow History");          
		System.out.println("[5]. Exit");
		
	
		Scanner s = new Scanner(System.in);
		try {
		int n = s.nextInt();
		if(n>=8) {
			System.out.println("Invalid Input!");
			menu(database, user);
		}
		this.operations[n-1].oper(database, user);
		s.close();
		}catch (InputMismatchException e) {
	        System.out.println("\nInvalid input. Please enter a number.");
	        menu(database, user);
		}
	}

	@Override
	public String toString() {
		return name+"<N/>"+email+"<N/>"+phoneNumber+"<N/>"+"Admin";
	}
	
}
