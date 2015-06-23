package skyfly33.array;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {

		Student[] students = new Student[3];
		students[0] = new Student("skyfly33", 3.39);
		students[1] = new Student("imfly7", 4.21);
		students[2] = new Student("egoing", 2.19);
		
		Arrays.sort(students);
		for (Student s : students) {
			System.out.println("name : " + s.getName() +"\ngrade : " + s.getGpa());
		}
	}

}

class Student implements Comparable<Student> {

	private String name;
	private double gpa;

	public Student(String n, double g) {
		this.name = n;
		this.gpa = g;
	}

	public String getName() {
		return name;
	}

	public double getGpa() {
		return gpa;
	}

	@Override
	public int compareTo(Student obj) {
		// TODO Auto-generated method stu
		Student other = (Student) obj;

		if (gpa < other.gpa)
			return -1;
		else if (gpa > other.gpa)
			return 1;
		else
			return 0;
	}

}
