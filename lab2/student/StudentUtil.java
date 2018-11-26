package lab2.student;

import java.util.Random;
import java.util.stream.Collectors;

import lab2.student.Student.Gender;

public class StudentUtil {
	
	public static final String[] alphabet = {"A", "B", "C", "E", "F", "a", "b", "c", "d", "e", "f"};
	public static final String[] dept = {"CS", "MATH", "ENG", "LET", "HIST"};
	public static final Random rand = new Random(System.currentTimeMillis());

	public static String generateName() {
		
		return rand.ints(0, alphabet.length)
				.limit(5)
				.mapToObj(x->alphabet[x])
				.collect(Collectors.joining());
	}

	public static String generateDepartment() {
		return dept[rand.nextInt(dept.length)];
	}
	
	public static double generateGrade() {
		return rand.nextInt(101);
	}
	
	public static Gender generateGender() {
		switch(rand.nextInt(3)) {
			case 0: {return Gender.FEMALE;}
			case 1: {return Gender.MALE;}
			default: {}
		}
		return Gender.YOUCHOSE;
	}
}
