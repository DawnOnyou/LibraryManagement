package Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {
	
	
	
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<String> usernames = new ArrayList<String>();
	private static ArrayList<Book> books = new ArrayList<Book>();
	private static ArrayList<String> booknames = new ArrayList<String>();
	private static ArrayList<Order> orders = new ArrayList<Order>();
	private static ArrayList<Borrowing> borrowings = new ArrayList<Borrowing>();
	
	private static File usersfile = new File("C:\\Library Management System\\Data\\Users");
	private static File booksfile = new File("C:\\Library Management System\\Data\\Books");
	private static File ordersfile = new File("C:\\Library Management System\\Data\\Orders");
	private static File borrowingsfile = new File("C:\\Library Management System\\Data\\Borrowings");
	private File folder = new File("C:\\Library Management System\\Data");
	
	public Database() {
		if(!folder.exists()) {
			folder.mkdirs();
		}
		if(!usersfile.exists()) {
			try {
				usersfile.createNewFile();
			} catch(Exception e) {}	
		}
		if(!booksfile.exists()) {
			try {
				booksfile.createNewFile();
			} catch(Exception e) {}	
		}
		
		if(!ordersfile.exists()) {
			try {
				ordersfile.createNewFile();
			} catch(Exception e) {}	
		}
		
		if(!borrowingsfile.exists()) {
			try {
				borrowingsfile.createNewFile();
			} catch(Exception e) {}	
		}
		
		
		getUsers();
		getBooks();
		getOrders();
		getBorrowings();
	}
	
	
	public static void AddUser(User s) {
		users.add(s); //Static Error
		usernames.add(s.getName());
		saveUsers();

	}
	
	
	
	public int login(String phonenumber, String email) {
		int n = -1;
		for(User s : users) {
			if (s.getPhoneNumber().matches(phonenumber) && s.getEmail().matches(email)) {
				n = users.indexOf(s);
				break;
			}
		}
		return n;
	}
	
	public User getUser(int n) {
		return users.get(n); 
	}
	
	public void AddBook(Book book) {
		books.add(book);
		booknames.add(book.getName());
		saveBooks();
	}

	private void getUsers() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(usersfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewUser/>");
			for (String s : a1) {
				String[] a2 = s.split("<N/>");
				if (a2[3].matches("Admin")) {

					User user = new Admin(a2[0], a2[1], a2[2]);
					users.add(user);
					usernames.add(user.getName());
				} else {
					User user = new NormalUser(a2[0], a2[1], a2[2]);
					users.add(user);
					usernames.add(user.getName());
				}
			}
		}
	}
	
	private static void saveUsers() {     //static
		String text1 = "";
		for(User user : users) {
			text1 = text1 + user.toString()+"<NewUser/>";
		}
		try {
			PrintWriter pw = new PrintWriter(usersfile);
			pw.print(text1);
			pw.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
	}
	
	private static void saveBooks() {     //static
		String text1 = "";
		for(Book book : books) {
			text1 = text1 + book.toString2()+"<NewBook/>";
		}
		try {
			PrintWriter pw = new PrintWriter(booksfile);
			pw.print(text1);
			pw.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void getBooks() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(booksfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		
		if(!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewBook/>");
			for (String s : a1) {
				Book book = parseBook(s);
				books.add(book);
				booknames.add(book.getName());				
			}
		}
		
	}
	
	public Book parseBook(String s) {
		String[] a = s.split("<N/>");
		Book book = new Book();
		book.setClas(a[0]);
		book.setName(a[1]);
		book.setAuthor(a[2]);
		book.setPublisher(a[3]);
		book.setIDnum(Integer.parseInt(a[4]));
		book.setBrwcopies(Integer.parseInt(a[5]));
		return book;
	}

	public ArrayList<Book> getAllBooks() {
		return books;
	}
	
	
	// HERE HERE HERE HERE HERE HERE
	
	
	public ArrayList<Borrowing> getAllBorrowings(){
		return borrowings;
	}
	
	 public Book searchBook(String bookname, String category) {
	        for (Book book : books) {
	            if (book.getName().equalsIgnoreCase(bookname) && book.getClas().equalsIgnoreCase(category)) {
	                return book;
	            }
	        }
	        return null;
	    }	
	
	
	public int getBook(String bookname) {
		int i = -1;
		for(Book book : books) {
			if(book.getName().matches(bookname)) {
				i = books.indexOf(book);
			}
		}
		return i;
	}
	
	public Book getBook(int i) {
		return books.get(i);
	}
	
	
	public void deleteBook(int i) {
		books.remove(i);
		booknames.remove(i);
		saveBooks();
	}
	
	public void deleteAllData() {
		
		if(usersfile.exists()) {
			try {
				usersfile.delete();
			} catch(Exception e) {}	
		}
		if(booksfile.exists()) {
			try {
				booksfile.delete();	
			} catch(Exception e) {}	
		}
		if(ordersfile.exists()) {
			try {
				ordersfile.delete();	
			} catch(Exception e) {}	
		}
		if(borrowingsfile.exists()) {
			try {
				borrowingsfile.delete();	
			} catch(Exception e) {}	
		}
		
	}
	
	public void addOrder(Order order, Book book, int bookindex) {
		orders.add(order);
		books.set(bookindex, book);
		saveOrders();
	}
	
	public static void saveOrders() {
		
		String text1 = "";
		for(Order order : orders) {
			text1 = text1 + order.toString2()+"<NewOrder/>";
		}
		try {
			PrintWriter pw = new PrintWriter(ordersfile);
			pw.print(text1);
			pw.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void getOrders() {
		
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(ordersfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		
		if(!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewOrder/>");
			for (String s : a1) {
				Order order = parseOrder(s);
				orders.add(order);
			}
		}
		
	}
	
	public boolean userExist(String name) {
		boolean f = false;
		for(User user : users) {
			if(user.getName().toLowerCase().matches(name.toLowerCase())) {
				f = true;
				break;
			}
		}
		return f;
	}
	
	private User getUserByName(String name) {
		User u = new NormalUser("");
		for(User user : users) {
			if(user.getName().matches(name)) {
				u = user;
				break;
			}
		}
		return u;
	}
	
	
	
	private Order parseOrder(String s) {
		String[] a = s.split("<N/>");
		
		Order order = new Order(books.get(getBook(a[0])), getUserByName(a[1]),
				Double.parseDouble(a[2]), Integer.parseInt(a[3]));
		
		return order;
	}
	
	public ArrayList<Order> getAllOrders(){
		return orders;
	}
	
	private void saveBorrowings() {
		
		String text1 = "";
		for(Borrowing borrowing : borrowings) {
			text1 = text1 + borrowing.toString2()+"<NewBorrowing/>";
		}
		try {
			PrintWriter pw = new PrintWriter(borrowingsfile);
			pw.print(text1);
			pw.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
	}
	
	private void getBorrowings() {
		
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(borrowingsfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch(Exception e) {
			System.err.println(e.toString());
		}
		
		if(!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewBorrowing/>");
			for (String s : a1) {
				Borrowing borrowing = parseBorrowing(s);
				borrowings.add(borrowing);

			}
		}
	}
	
	private Borrowing parseBorrowing(String s) {
		String[] a = s.split("<N/>");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(a[0], formatter);
		LocalDate finish = LocalDate.parse(a[1], formatter);
		Book book = getBook(getBook(a[2]));
		User user = getUserByName(a[3]);
		Borrowing brw = new Borrowing(start, finish, book, user);
		return brw;
	}
	
	public void borrowBook(Borrowing brw, Book book, int bookindex) {
		borrowings.add(brw);
		books.set(bookindex, book);
		saveBorrowings();
		saveBooks();
		
	}
	
	public ArrayList<Borrowing> getBrws(){
		return borrowings;
	}
	
	public void returnBook(Borrowing b, Book book, int bookindex) {
		borrowings.remove(b);
		books.set(bookindex, book);
		saveBorrowings();
		saveBooks();
	}
	
}
