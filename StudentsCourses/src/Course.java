import java.util.ArrayList;
import java.util.List;

public class Course {
	private String name;
	private String id;
	private int points;
	private double roomCost;
	private List<Student> students;
	private List<Lecturer> lecturers;
	
	private static final int MAX_STUDENTS = 30;
	
	
	public Course (String name, String id, int points, double roomCost) {
		this.name = name;
		this.id = id;
		this.points = points;
		this.roomCost = roomCost;
		this.students = new ArrayList<>();
		this.lecturers = new ArrayList<>();//为什么在这里初始化arrayList,而不在成员变量里直接初始化?
	}
	
//	public void setName(String name) {//Don't need this setting for every variable?
//		this.name = name;
//	}
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public int getPoints() {
		return points;
	}
	
	public double getRoomCost() {
		return roomCost;
	}
	
	public List<Student> getStudents(){
		return students;
	}
	
	public List<Lecturer> getLecturers(){
		return lecturers;
	}
	
	public boolean enrolStudent(Student student) {//这段逻辑什么意思?
		if(students.size() >= MAX_STUDENTS) return false;
		for(int i = 0; i < students.size(); i++){
			if(students.get(i) == student){//这句啥意思?
				return false;
			}
		}
		students.add(student);
		student.enrollCourse(this);//这句啥意思?
 		return true;
	}
	
	public void assignLecturer(Lecturer lecturer) {
		boolean alreadyAssigned = false;
		for(int i = 0; i < lecturers.size(); i++) {
			if(lecturers.get(i) == lecturer) {
				alreadyAssigned = true;
			}
		}
		if(!alreadyAssigned && lecturer.canTeachMore()) {
			lecturers.add(lecturer);
			lecturer.assignCourse(this);//这句什么意思?
		}
		
	}
	
	public double calculateIncome() {
		double total = 0;
		for(int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			total += s.getPricePerPoint() * points;
		}
		return total;
	}
	
	public double calculateCost() {
		double total = roomCost;
		for(int i = 0; i < lecturers.size(); i++) {
			Lecturer l = lecturers.get(i);
			total += l.getCostToTeach();
		}
		return total;
	}
	
	public double calculateProfit() {
		return calculateIncome() - calculateCost();
	}
	
//	public double averageGPA() {
//		return 1.00;
//	}
}
