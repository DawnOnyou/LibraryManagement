package Library;

public class Book {
	private String classification;
	private String name;    	
	private String author;
	private String publisher;
	private int IDnum;
	private int brwcopies;		
	
	public Book() {};
	
	public Book(String classification, String name, String author, String publisher, int IDnum, int brwcopies) {
		this.classification = classification;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.IDnum = IDnum;
		this.brwcopies = brwcopies;
	}
	
	
	
	public String toString() {
		String text = "Category: "+ classification + "\n" + "Book Name: " + name + "\n"+
				"Book Author: " + author + "\n"+
				"Book Publisher: " + publisher + "\n"+
				"Book ID Number: " + String.valueOf(IDnum) + "\n"+
				"Borrowing Copies: " + String.valueOf(brwcopies);
		
		
		return text;
	}
	
	public String getClas() {
		return classification;
	}

	public void setClas(String classification) {
		this.classification = classification;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getIDnum() {
		return IDnum;
	}

	public void setIDnum(int IDnum) {
		this.IDnum = IDnum;
	}
	
	public int getBrwcopies() {
		return brwcopies;
	}

	public void setBrwcopies(int brwcopies) {
		this.brwcopies = brwcopies;
	}
	
	public String toString2() {
		
		String text = classification+"<N/>"+ name+"<N/>"+author+ "<N/>" + publisher + "<N/>" + String.valueOf(IDnum) +
				"<N/>"+ String.valueOf(brwcopies);
		return text;

	}
	
	
	
	
}
