package multiset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>A MultiSet models a data structure containing elements along with their frequency count i.e., </p>
 * <p>the number of times an element is present in the set.</p>
 * <p>HashMultiSet is a Map-based concrete implementation of the MultiSet concept.</p>
 *
 * <p>MultiSet a = <{1:2}, {2:2}, {3:4}, {10:1}></p>
 * */
public final class HashMultiSet<T, V extends Integer> {

	public Map<T, V> multiSet;

	/**
	 *XXX: data structure backing this MultiSet implementation.
	 */

	/**
	 * Sole constructor of the class.
	 **/
	public HashMultiSet() {
		multiSet = new HashMap<>();
	}//HashMultiSet


	/**
	 * If not present, adds the element to the data structure, otherwise
	 * simply increments its frequency.
	 *
	 * @param t T: element to include in the multiset
	 *
	 * @return V: frequency count of the element in the multiset
	 * */
	public V addElement(T t) {

		Integer val = 1 + (multiSet.containsKey(t) ? multiSet.get(t) : 0);

		multiSet.put(t, (V) val);
		return multiSet.get(t);

	}//addElement()

	/**
	 * Check whether the elements is present in the multiset.
	 *
	 * @param t T: element
	 *
	 * @return V: true if the element is present, false otherwise.
	 * */
	public boolean isPresent(T t) {
		return multiSet.containsKey(t);
	}//isPresent

	/**
	 * @param t T: element
	 * @return V: frequency count of parameter t ('0' if not present)
	 * */
	public V getElementFrequency(T t) {
	    Integer zero = 0;
		return multiSet.containsKey(t) ? multiSet.get(t) : (V) zero ;
	}//getElementFrequency


	/**
	 * Builds a multiset from a source data file. The source data file contains
	 * a number comma separated elements.
	 * Example_1: ab,ab,ba,ba,ac,ac -->  <{ab:2},{ba:2},{ac:2}>
	 * Example 2: 1,2,4,3,1,3,4,7 --> <{1:2},{2:1},{3:2},{4:2},{7:1}>
	 *
	 * @param source Path: source of the multiset
	 * */
	public void buildFromFile(Path source) throws IOException {

	    if(source == null)
            throw new IllegalArgumentException("Method should be invoked with a non null file path");

		File file = source.toFile();

	    BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;

        while((line = reader.readLine()) != null)
            for(String value : line.split(","))
                addElement((T) value);



	}//buildFromFile

	/**
	 * Same as before with the difference being the source type.
	 * @param source List<T>: source of the multiset
	 * */
	public void buildFromCollection(List<? extends T> source) {

		if (source != null){
			for(T element : source)
				addElement(element);
		}else{
			throw new IllegalArgumentException("Method should be invoked with a non null list collection");
		}//if_else

	}//buildFromCollection

	/**
	 * Produces a linearized, unordered version of the MultiSet data structure.
	 * Example: <{1:2},{2:1}, {3:3}> -> 1 1 2 3 3 3 3
	 *
	 * @return List<T>: linearized version of the multiset represented by this object.
	 */
	public List<T> linearize() {

		List<T> linearizedMap = null;

		for(T key : multiSet.keySet())
			for(int i = 0; i < (int) multiSet.get(key) ; i ++)
				linearizedMap.add((T) key);

		return linearizedMap;

	}//linearize


}
