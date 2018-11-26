package lab2.student;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.DoubleSummaryStatistics;

public class StudentStats {
	
	public static final void main(String args[]) {

		List<Student> students = IntStream.range(1, 50)
										  .mapToObj(it->
										  			new Student(StudentUtil::generateName,
													StudentUtil::generateGrade,
													StudentUtil::generateDepartment,
													StudentUtil::generateGender))
										  .collect(Collectors.toList());
		
		//1. compute the number of students with passing grades.
//		System.out.println(getPassingStudentsGrades(students));

		//2. compute the  average student grade.
//		System.out.println(getAverageStudentsGrades(students));
		
		//3. partition the students to their belonging department.
//		Map<String, List<Student>> studentsByDepartment = getStudentsByDepartment(students);
//
//		for (String key : studentsByDepartment.keySet()) {
//
//			System.out.println("Department " + key + ": ");
//			studentsByDepartment.get(key).forEach(System.out::println);
//			System.out.println();
//
//		}//for

		//4. compute the average grade by department.
//		Map<String, List<Student>> studentsByDepartment = getStudentsByDepartment(students);
//
//		for (String key : studentsByDepartment.keySet()) {
//
//			System.out.print("Department " + key + ": ");
//			double avg = studentsByDepartment.get(key).stream().collect(Collectors.summarizingDouble(Student::getGrade)).getAverage();
//			System.out.println(avg + "\n");
//
//		}//for

// 		Soluzione Schiabel

//		//1. compute the number of students with passing grades.
//		long studentsWithPassingGrade = students.parallelStream()
//		.filter(student -> student.getGrade() >= Student.PASSING_GRADE)
//		.count();
//		
//		//2. compute the  average student grade.
//		double averageGrade = students.parallelStream()
//		.mapToDouble(student -> student.getGrade())
//		.average()
//		.getAsDouble();
//		
//		//3. partition the students to their belonging department.
//		Map<String, List<Student>> studentsGroupedByDepartment = students.parallelStream()
//		.collect(Collectors.groupingBy(Student::getDepartment));
//		
//		//4. compute the average grade by department.
//		Map<String, Double> averageGradeByDepartment = students.parallelStream()
//		.collect(Collectors.groupingBy(Student::getDepartment, Collectors.averagingDouble(Student::getGrade)));
//		
//		log("studentsWithPassingGrade", studentsWithPassingGrade);
//		log("averageGrade", averageGrade);
//		log("studentsGroupedByDepartment", studentsGroupedByDepartment);
//		log("averageGradeByDepartment", averageGradeByDepartment);
//

	}//main()

	public static int getPassingStudentsGrades(List<Student> students){

		return students.stream().filter(x -> x.getGrade() >= 70).collect(Collectors.toList()).size();

	}//getPassingStudentsGrades()

	public static Double getAverageStudentsGrades(List<Student> students){

		return students.stream().collect(Collectors.summarizingDouble(Student::getGrade)).getAverage();
		//return students.stream().mapToDouble(x -> x.getGrade()).average().getAsDouble();

	}//getAverageStudentsGrades()

	public static Map<String, List<Student>> getStudentsByDepartment(List<Student> students){
		
		return students.stream().collect(Collectors.groupingBy(Student::getDepartment));

	}//getStudentsByDepartment()

}//StudentStats
