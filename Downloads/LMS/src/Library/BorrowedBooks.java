package Library;

import java.util.ArrayList;

public class BorrowedBooks implements IOOperation {
	
	
	@Override
	public void oper(Database database, User user) {

		ArrayList<Borrowing> borrowings = database.getAllBorrowings();
		ArrayList<Book> books = database.getAllBooks();
		
	
		System.out.printf("%-25s %-25s %-25s %-25s%n",
		         "Date Borrowed", "Date Expiry", "Book", "User");

		
		
		for(Borrowing c: borrowings) {
			
			String BOOKNAME = c.getBook().getName();
			String USERNAME = c.getUser().getName();
			
		    System.out.printf("%-25s %-25s %-25s %-25s%n",
                     c.getStart(), c.getFinish(), BOOKNAME, USERNAME);
		}
			System.out.println();
			user.menu(database, user);
		
	}
	
	
	
	}


