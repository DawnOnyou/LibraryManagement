package Library;

import java.util.Scanner;

public class BorrowBook implements IOOperation{
	
	@Override
	public void oper(Database database, User user) {

		Scanner s = new Scanner(System.in);
		System.out.println("Enter book name: ");
		String bookname = s.nextLine();
		System.out.println("Enter quantity: ");
		int bookqt = s.nextInt();
		
		int i = database.getBook(bookname);

		if(i>-1) {
		    Book book = database.getBook(i);
		    boolean n = true;

		    for(Borrowing b: database.getBrws()) {
		        if(b.getBook().getName().matches(bookname) && 
		                b.getUser().getName().matches(user.getName())) {

		            n = false;
		            System.out.println("You have borrowed this book before! ");
		        }
		    }

		    if(n) {
		        if(book.getBrwcopies()>1) {
		            Borrowing borrowing = new Borrowing(book, user);
		            book.setBrwcopies(book.getBrwcopies()-bookqt);
		            database.borrowBook(borrowing, book, i);
		            System.out.println("You must return the book before 7 days from now\n"+"You'll be fined ₱50 after the expiry date\n"
		                    +"Expiry date: "+borrowing.getFinish()+"'\nEnjoy!\n");

		        }else {
		            System.out.println("This book isn't for borrowing! \n");
		        }
		    }
		    user.menu(database, user);
		} else {
		    System.out.println("Book doesn't exist!\n");
		    user.menu(database, user);
		}

	}
}
