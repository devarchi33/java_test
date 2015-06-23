package skyfly33.array;

import java.util.Arrays;
import java.util.Comparator;

public class SortV2 {

	public static void main(String[] args){
		Student2[] students = new Student2[3];
		students[0] = new Student2("skyfly33", 3.39);
		students[1] = new Student2("imfly7", 4.21);
		students[2] = new Student2("egoing", 2.19);
		
		Arrays.sort(students, new Comparator<Student2>() {
			@Override
			public int compare(Student2 o1, Student2 o2) {
				// TODO Auto-generated method stub
				if(o1.getGpa() > o2.getGpa())
					return 1;
				else if(o1.getGpa() <  o2.getGpa())
					return -1;
				else
					return 0;
			}
		});
		
		for (Student2 s : students) {
			System.out.println("name : " + s.getName() +"\ngrade : " + s.getGpa());
		}
	}
}

class Student2 {
	
	private String name;
	private double gpa;
	
	public Student2 (String n, double g) {
		this.name = n;
		this.gpa = g;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getGpa() {
		return this.gpa;
	}
}
