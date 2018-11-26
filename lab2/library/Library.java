package lab2.library;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;

/**
 * This class models the library which is comprised of several shelves holding books each of which identified by some parameters.
 */
public final class Library {

	private final ArrayList<Shelf> library;

	/**
	 * Builds an empty library
	 * */
	public Library() {
		this(new ArrayList<Shelf>());
	}

	/**
	 * Builds a library given some shelves as a input parameter.
	 * 
	 * @param library: shelves to be added to this library
	 * 
	 * */
	public Library(ArrayList<Shelf> library) {
		this.library = cloneInternal(library);
	}
	
	/**
	 * Adds a shelf to the library
	 * 
	 * @param shelf: the shelf
	 */
	public void addShelf(Shelf shelf) {
		library.add(shelf.clone());
	}
	
	/**
	 * @return the total number of books present in this library
	 */
	public int getNumberOfBooksInLibrary() {
		return library.size();
	}

	/**
	 * @return the book year range (min, max) present in this library.
	 * E.g., if the oldest book year is 1200 and a newly added in 2017, the method returns an array of int {1200, 2017}
	 * */
	public int[] getBooksYearRangeInLibrary() {
	
		library.forEach(Shelf::getBooksInShelf);

		int[] range = {0, 0};

		return range;

//		throw new UnsupportedOperationException();
	}
	
	/**
	 * @return the average number of books stored in the library shelves.
	 * E.g., Shelf_1 contains 1 and Shelf_1 contains 2 contains 2 => Avg = (1+2)/2
	 */
	public double getAvgNumberOfBookPerShelf() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the number of distinct authors present in the library
	 * */
	public int getTotalNumberOfDistinctAuthorsInLibrary() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the total number of book pages present in this library
	 * */
	public int getTotalNumberOfBookPagesInLibrary() {		
		throw new UnsupportedOperationException();
	}
	
	
	private ArrayList<Shelf> cloneInternal(ArrayList<Shelf> toCopy) {
		if(toCopy == null) return new ArrayList<>();
		ArrayList<Shelf> copy = new ArrayList<>();
		for(Shelf a: toCopy) 
			copy.add(a.clone());
		return copy;
	}	
	
	
	
}
