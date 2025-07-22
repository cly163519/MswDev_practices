import java.util.ArrayList;
import java.util.List;

public class Student {
	private static int nextId = 1;//这行什么意思?
	
	private String name;
	private final int id ;//为什么要加final?
	private double pricePerPoint;
	private ArrayList<Course> enrolledCourses;
	private ArrayList<String> grades;
	
	private static final int MAX_COURSES = 5;//这行什么意思?
	
	
	public Student(String name, int id, double pricePerPoint) {//name equals passing value from main?//每个全局变量都要传值到这个参数里?
		this.name = name;
		this.pricePerPoint = pricePerPoint;
		this.id = nextId++;//这行什么意思?
		this.enrolledCourses = new ArrayList<>();//这是什么用法?
		this.grades = new ArrayList<>();
		
	}	

	
	public String getName() {//每个全局变量都要在这里get?但是可以不用set?
		return name;
	}
	
	public int getId() {
		return id;
	}
	
//	public void setName(String name) {//name equals passing value from main?
//		this.name = name;
//	}
	
	public double getPricePerPoint() {
		return pricePerPoint;
	}
	
	public boolean enrollCourse(Course course) {//这段逻辑没看懂
		if(enrolledCourses.size() >= MAX_COURSES) {
			return false;
		}
		for(int i = 0; i < enrolledCourses.size(); i++) {
			if(enrolledCourses.get(i) == course) {
				return false;
			}
		}
		enrolledCourses.add(course);
		grades.add("N/A");
		return true;
	}
	
	public void assignGrade(Course course, String grade) {
		for(int i = 0; i < enrolledCourses.size(); i++) {
			if(enrolledCourses.get(i) == course) {
				grades.set(i, grade);
			}
		}
	}
	
	public String getGrade(Course course) {
		for(int i = 0; i < enrolledCourses.size(); i++) {
			if(enrolledCourses.get(i) == course) {
				return grades.get(i);
			}
		}
		return "N/A";
	}
	
	public double getGPA() {
		double total = 0;
		int count = 0;
		for(int i = 0; i < grades.size(); i++) {
			double points = gradeToGPA(grades.get(i));
			total += points;
			count++;
		}
		if (count == 0) {
			return 0.0;
		}
		return total / count;
	}
	

	public double gradeToGPA(String grade) {
		if(grade.equals("A")) return 4.0;
		if(grade.equals("B")) return 3.0;
		if(grade.equals("C")) return 2.0;
		if(grade.equals("D")) return 1.0;
		return 0.0;
		
	}
}
