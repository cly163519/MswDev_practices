import java.util.ArrayList;
import java.util.List;

public class Lecturer {
	private String name;
	private double costToTeach;
	private List<Course> assignedCourses;
	
	private static final int MAX_COURSES = 3;

	public Lecturer(String name, double costToTeach) {
		this.name = name;
		this.costToTeach = costToTeach;
		this.assignedCourses = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public double getCostToTeach() {
		return costToTeach;
	}

	public boolean canTeachMore() {
		return assignedCourses.size() < MAX_COURSES;
 	}
	
	public void assignCourse(Course course) {
		if(!assignedCourses.contains(course)) {
			assignedCourses.add(course);
		}
	}
	
	
}
