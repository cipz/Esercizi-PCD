package lab1.frequencyCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrequencyCount {

	public static <K, V> void frequencyMap(List<? extends K> src, Map<? super K, Integer> map) {
	
		for (K key : src){
			map.putIfAbsent(key, 0);
			Integer val = map.get(key);
			map.put(key,val+1);
		}//for
		
		// throw new UnsupportedOperationException("");
		
	}//frequencyMap
	
	public static final void main(String args[]) {
		
		List<Integer> values1 = Stream.of(1,3,4,51,1,1,1).collect(Collectors.toList());
		Map<Integer, Integer> frequencyMap1 = new HashMap<>(); 
		FrequencyCount.<Integer, Integer>frequencyMap(values1, frequencyMap1);
		
		List<String> values2 = Stream.of("1","3","4","51","1","1","1").collect(Collectors.toList());
		Map<String, Integer> frequencyMap2 = new HashMap<>(); 
		FrequencyCount.<String, Integer>frequencyMap(values2, frequencyMap2);
		
		System.out.println(frequencyMap1);
		System.out.println(frequencyMap2);
		
	}//main
	
}//frequencyCount
