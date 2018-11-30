package lab2.library;

import java.util.ArrayList;

/**
 * This class models a book in our library context
 * 
 * */
public class Book implements Cloneable {

	private String title;
	private String ISBN;
	private int year;
	private int pages;
	private ArrayList<Author> authors;

	/**
	 * Sole class constructor.
	 * 
	 * @param title: 	the book title
	 * @param ISBN:  	the book ISBN identifier
	 * @param year:  	the year the book was published
	 * @param pages: 	the number of pages in the book
	 * @param authors:	a list of authors who wrote the book
	 * 
	 * @throws IllegalArgumentException: in case parameters are not valid
	 */
	public Book(String title, String ISBN, int year, int pages, ArrayList<Author> authors) {
		boolean parametersValid = Util.strinParameterNotEmpty(title) &&
								  Util.strinParameterNotEmpty(ISBN) &&
								  Util.positiveIntegralValue(year) &&
								  Util.positiveIntegralValue(pages) &&
								  Util.listParameterValid(authors);
		
		if(!parametersValid) throw new IllegalArgumentException("Book has all or some parameters which are not valid");
		this.title = title;
		this.ISBN = ISBN;
		this.year = year;
		this.pages = pages;
		this.authors = cloneInternal(authors);
	}
	
	/**
	 * @return the book title
	 * */
	public String getBookTitle() {
		return title;
	}
	
	/**
	 * @return the book ISBN identifier
	 * */
	public String getBookISBN() {
	 	return ISBN;
	}
	
	/**
	 * @return the book year
	 * */
	public int getBookYear() {
		return year;
	}
	
	/**
	 * @return the book pages
	 * */
	public int getBookPages() {
		return pages;
	}
	
	/**
	 * @return the book authors
	 * */
	public ArrayList<Author> getBookAuthors() {

		return cloneInternal(authors);
	}
	
	/**
	 * @return the book author number
	 * */
	public int getBookAuthorNumber() {
		return authors.size();
	}
	
	/**
	 * Adds an Author to this book
	 * 
	 * @param author: author to add
	 * */
	public void addBookAuthor(Author author) {
		authors.add(author.clone());
	}
	
	@Override	
	public String toString() {
		return "Book[" + title + " " + ISBN + " " + authors + "]";
	}
	
	@Override	
	public boolean equals(Object obj) {
		if(!(obj instanceof Book))
			return false;
		
		if(this == obj) 
			return true;
		
		Book otherBook = (Book)obj;
		boolean primitives_test = otherBook.getBookTitle().equals(title) &&	
								  otherBook.getBookISBN().equals(ISBN) &&
								  otherBook.getBookYear() == year &&
								  otherBook.getBookPages() == pages && 
								  otherBook.getBookAuthorNumber() == authors.size();
		if(!primitives_test) 
			return false;
			
		ArrayList<Author> otherAuthors = otherBook.getBookAuthors();
		if(otherAuthors.size() != authors.size())
			return false;
		for(Author a: authors) 
			if(otherAuthors.indexOf(a) == -1)
				return false;
			return true;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + title.hashCode();
		result = 31 * result + ISBN.hashCode();
		result = 31 * result + year;
		result = 31 * result + pages;
		result = 31 * result + authors.hashCode();
		return result;
	}
	

	@Override
	public Book clone() {
		
		try {
			Book copy = (Book) super.clone();
			copy.authors = cloneInternal(authors);
			return copy;
		} catch (CloneNotSupportedException e) {}
		return null;		
	}
	
	private ArrayList<Author> cloneInternal(ArrayList<Author> toCopy) {

		/**
		 * Both deep and shallow copy avoid side-effects from the callee and no class invariants are violated.
		 * We have no mutators in classes contained objs. in THIS implementation.
		 * However, in future extensions might introduce mutators: hence, a deep-copy approach.
		 */
		ArrayList<Author> copy = new ArrayList<>();
		for(Author a: toCopy) 
			copy.add(a.clone());
		return copy;
	}	
}