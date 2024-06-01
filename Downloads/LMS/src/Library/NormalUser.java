package Library;

import java.util.Scanner;
import java.util.InputMismatchException;


public class NormalUser extends User {
	
	
	public NormalUser(String name) {
		super(name);
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new Search(),
				new BorrowBook(),
				new CalculateFine(),
				new ReturnBook(),
				new Exit(),
				 
			};
		
		
	}
	
	public NormalUser(String name, String email, String phonenumber) {
		super(name, email, phonenumber);	
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new Search(),
				new BorrowBook(),
				new CalculateFine(),
				new ReturnBook(),
				new Exit() 
			};
	}
	
	
	@Override
	public void menu(Database database, User user) {
		System.out.println("\n===============================");
		System.out.println("=  Library Management System  =");
		System.out.println("===============================");
		System.out.println("           USER MODE    ");
		System.out.println("\n[1]. View Books");
		System.out.println("[2]. Search");
		System.out.println("[3]. Borrow Book");
		System.out.println("[4]. Calculate Fine");
		System.out.println("[5]. Return Book");
		System.out.println("[6]. Exit\n");
		
		Scanner s = new Scanner(System.in);
		try {
		int n = s.nextInt();
		if(n>=7) {
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
	
	public String toString() {
		return name+"<N/>" +email+ "<N/>"+phoneNumber+"<N/>"+"Normal";        
	}
	
}
