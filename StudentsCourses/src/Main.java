import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] arg) {
		new Main();//main can "new" in its own class?
		//Create students
		Student tom = new Student("Tom", 100, 480.00);//Student,Course,Lecture shouldn't new in its own, but in main?
		Student lucy = new Student("Lucy", 120, 680.00);
		Student jack = new Student("Jack", 130, 380.00);
		//Create courses
		Course swen = new Course("SWEN", "501", 30, 300.00);
		Course cs = new Course("CS", "131", 20, 200);
		//Create lecturers
		Lecturer john = new Lecturer("John",1500.00);
		Lecturer alice = new Lecturer("Alice", 1000.00);
		
//		List<Student> students = new ArrayList<>();//ArrayList<String> students = new ArrayList<>();
//		students.add(student);//Can't be put in "Student" class
//		
//		List<Integer> points = new ArrayList<>();
		
		//Assign lecturers
		swen.assignLecturer(john);
		cs.assignLecturer(alice);
		
		//Assign students
		swen.enrolStudent(tom);
		swen.enrolStudent(lucy);
		cs.enrolStudent(jack);
		cs.enrolStudent(tom);
		
		//Assign grades
		tom.assignGrade(swen, "A");
		tom.assignGrade(cs, "B");
		lucy.assignGrade(swen, "B");
		jack.assignGrade(cs, "C");
		
		System.out.println("SWEN profit: " + swen.calculateProfit());
		//System.out.println("SWEN average GPA: " + swen.averageGPA());
		
		System.out.println("Tom GPA: " + tom.getGPA());
		System.out.println("Jack GPA: " + jack.getGPA());
		
	}
}
