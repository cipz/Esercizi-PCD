package lab2.student;

import java.util.function.Supplier;

public class Student {
    private String name;
    private double grade;
    private String department;
    private Gender gender;

    public static final double PASSING_GRADE = 70.0;

    public enum Gender {
        MALE, FEMALE, YOUCHOSE
    }

    public Student(Supplier<String> name, 
    		Supplier<Double> grade, 
    		Supplier<String> department, 
    		Supplier<Gender> gender) {

    	this.name = name.get();
        this.grade = grade.get();
        this.department = department.get();
        this.gender = gender.get();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}