
package lab1.statistics;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements a MultiMap data structure. A MultiMap is an associative structure where multiple elements can be mapped
 * under a one key. An an example consider the following sequence of statements:
 * 1. map.put(k1, v1);
 * 2. map.put(k1, v2);
 * 3. List<V> = map.get(k1);
 * 
 * The last operation should return a List<V> of values comprised of the entries {v1, v2}
 */
public class MultiMap<K, V> {
	
	Map<K, List<V>> m = new HashMap<K, List<V>>();
	
	/**
	 * Sole constructor of the class which builds an empty MultiMap.
	 */
	public MultiMap() {}
		
	/**
	 * Stores the value (value) given as an input under the key (key).
	 * 
	 * @param key: 		key parameter which serves as an index in the MultiMap data structure
	 * @param value: 	value parameter to be index under the key
	 */
	public void put(K key, V value) {
		
		List<V> l = m.get(key);

		if(l == null) {
			l = new ArrayList<V>();
			m.put(key, l);
		}//if
		
		l.add(value);
	
	}//put
	
	/**
	 * Returns a Set view of the keys contained in this map.
	 * 
	 * @return a set view of the keys contained in this map.
	 */
	public Set<K> keySet() {
		return m.keySet();		
	}//keySet

	/**
	 * Returns a List of objects stored under the key (key).
	 * 
	 * @return a List of objects stored under the key.
	 */
	public List<V> get(Object key) {
		return m.get(key);
	}//get
}


/*
 * Maps
 * https://examples.javacodegeeks.com/java-basics/java-map-example/
 * 
 * 
 * Sets
 * https://www.geeksforgeeks.org/set-in-java/
 * 
 * 
 * Difference set and list
 * https://stackoverflow.com/questions/1035008/what-is-the-difference-between-set-and-list
 * 
 */
