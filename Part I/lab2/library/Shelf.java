package lab2.library;

import java.util.ArrayList;

/**
 * This class models a shelf in our library context
 * 
 * */
public class Shelf implements Cloneable {

	private final int capacity;
	private ArrayList<Book> books;
	
	/**
	 * Builds a shelf of a certain capacity as specified by the input parameter
	 * 
	 * @param capacity: positive value denoting the maximum number of books the shelf can contain
	 * */
	public Shelf(int capacity) {
		this(capacity, new ArrayList<Book>());
	}
	
	/**
	 * Builds a shelf of a certain capacity and a certain number of books
	 * 
	 * @param capacity: capacity of this shelf
	 * @param books:	books to store in this shelf
	 * 
	 * @throws IllegalArgumentException: in case parameters are not valid i.e., capacity not positive or capacity exceeded
	 * */
	public Shelf(int capacity, ArrayList<Book> books) {
		boolean parametersValid = Util.positiveIntegralValue(capacity) &&
							 	  Util.listParameterValid(books) &&
							 	  books.size() <= capacity;
		if(!parametersValid) throw new IllegalArgumentException("Shelf parameters are not valid");
		this.capacity = capacity;
		this.books = cloneInternal(books);
	}


	/**
	 * Adds the specified book in the shelf
	 * 
	 * @param b: book to add
	 * 
	 * @return true if the book was added in the shelf, false otherwise
	 */
	public boolean addBookInShelf(Book b) {
		if(shelfFull()) return false;
		books.add(b.clone());
		return true;
	}

	/**
	 * Adds the books specified in the parameter to this shelf
	 * 
	 * @param book:	an array of books to add to this shelf
	 * 
	 * @return the effective number of books added to this shelf
	 */
	public int addBooksInShelf(Book[] book) {
		if(shelfFull()) return 0;
		int ins = 0;
		while(!shelfFull() && ins < book.length)
			books.add(book[ins++].clone());	
		return ins;		
	}


	/**
	 * @return the shelf capacity
	 * */
	public int getShelfCapacity() {
		return capacity;
	}
	
	/**
	 * @return a list containing the books stored in this shelf
	 * */
	public ArrayList<Book> getBooksInShelf() {

		return cloneInternal(books);
	}

	/**
	 * @return the number of books stored in this shelf
	 */
	public int getNumerOfBooksInShelf() {
		return books.size();
	}
	
	/**
	 * @return true if the shelf is full (cannot contain any more books), false otherwise
	 * */
	public boolean shelfFull() {
		return books.size() == capacity;
	}

	@Override	
	public String toString() {
		return "Shelf[" + capacity + " " + books + "]";
	}
	
	@Override	
	public Shelf clone() {

		try {
			Shelf shelf = (Shelf)super.clone();
			shelf.books = cloneInternal(books);
			return shelf;
		} catch (CloneNotSupportedException e) {}
		return null;
		
	}
	
	private ArrayList<Book> cloneInternal(ArrayList<Book> books) {
		/**
		 * Both deep and shallow copy avoid side-effects from the callee and no class invariants are violated.
		 * We have no mutators in classes contained objs. in THIS implementation.
		 * However, in future extensions might introduce mutators: hence, a deep-copy approach.
		 */
		ArrayList<Book> copy = new ArrayList<Book>();
		for(Book b: books) {
			copy.add(b.clone());
		}
		return copy;
	}
}