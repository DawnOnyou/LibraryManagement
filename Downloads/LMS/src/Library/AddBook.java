package Library;

import java.util.Scanner;

public class AddBook implements IOOperation {                           //added abstract 
	
	@Override
	public void oper(Database database, User user) {
		
		Scanner s = new Scanner(System.in);
		Book book = new Book();
		
		System.out.println("Enter Book category: ");
		String clas = s.nextLine();
		book.setClas(clas);
		
		System.out.println("Enter Book name: ");
		String name = s.nextLine();
		
		if(database.getBook(name)>-1) {
			System.out.println("There is a book with this name!\n");
			user.menu(database,user);
			return;
		}else {
			
			book.setName(name);
			System.out.println("Enter Book author: ");
			book.setAuthor(s.nextLine());
			System.out.println("Enter Book publisher: ");
			book.setPublisher(s.nextLine());
			System.out.println("Enter book ID: ");
			book.setIDnum(s.nextInt());
			s.nextLine();
			System.out.println("Enter book quantity: ");
			book.setBrwcopies(s.nextInt());
			s.nextLine(); // consume newline left-over
			database.AddBook(book);
			System.out.println("Book Added Successfully!");
			user.menu(database, user);
			s.close();
			
		}
	}
}
