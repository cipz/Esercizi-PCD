package lab2.library;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public final class Util {

	public static final int RAND_STRING_LENGTH = 12;
	/**
	 * 
	 * */
	public static final char[] dictSymbols;

	static {
	    final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	    final String lower = upper.toLowerCase(Locale.ROOT);

	    final String digits = "0123456789";

	    final String alphanum = lower + digits;
	    
	    dictSymbols = alphanum.toCharArray();
	}
	
	public static String generateRandIdString() {
		
		StringBuilder builder = new StringBuilder();
		Random rand = new Random(System.currentTimeMillis());
		
		for(int i=0; i< RAND_STRING_LENGTH; i++) {
			builder.append(dictSymbols[rand.nextInt(dictSymbols.length)]);
		}
		return builder.toString();
	}
	
	public static boolean parameterNotNull(Object obj) {
		return obj!=null;
	}
	
	public static boolean strinParameterNotEmpty(String str) {
		return (str != null && !str.isEmpty());
	}

	
	public static <T> boolean listParameterValid(List<T> lst) {
		return (lst != null && !lst.isEmpty());
	}

	public static boolean positiveIntegralValue(double value) {
		if(value < 0) return false;
		return true;
	}
}