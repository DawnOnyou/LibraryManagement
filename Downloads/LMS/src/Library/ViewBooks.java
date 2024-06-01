package Library;

import java.util.ArrayList;

public class ViewBooks implements IOOperation {
	
	@Override
	public void oper(Database database, User user) {
	
		ArrayList<Book> books = database.getAllBooks();
		
		System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s%n",
		         "Category", "Book Title", "Book Author", "Book Publisher",  "Book ID", "Quantity");
		
	
		for(Book b: books) {
			System.out.printf("%-25s %-25s %-25s %-25s %-25d %-25d%n",
			         b.getClas(), b.getName(), b.getAuthor(), b.getPublisher(), b.getIDnum(), b.getBrwcopies());
		}
		
		System.out.println();
		user.menu(database, user);
	}
}
