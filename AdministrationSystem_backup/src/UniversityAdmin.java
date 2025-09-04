import java.util.*;

	public class UniversityAdmin {
	    // 主索引
	    private final HashMap<Integer, Student> studentsById = new HashMap<Integer, Student>();
	    private final HashMap<String, Student> studentsByName = new HashMap<String, Student>();
	    private final HashMap<String, Course>  coursesByCode = new HashMap<String, Course>();

	    // 为没有学号的数据自动分配
	    private int nextId = 1_000_000;

	    public boolean addStudent(int id, String name) {
	        if (id < 1_000_000 || id > 4_000_000) return false;
	        if (studentsById.containsKey(id)) return false;
	        if (studentsByName.containsKey(name)) return false;
	        Student s = new Student(id, name);
	        studentsById.put(id, s);
	        studentsByName.put(name, s);
	        return true;
	    }

	    // 按姓名获取或新建（用于导入数据）
	    public Student getOrCreateByName(String name) {
	        Student s = studentsByName.get(name);
	        if (s != null) return s;
	        // 分配新 ID
	        int assigned = nextId++;
	        s = new Student(assigned, name);
	        studentsById.put(assigned, s);
	        studentsByName.put(name, s);
	        return s;
	    }

	    public boolean addCourse(String code) {
	        if (coursesByCode.containsKey(code)) return false;
	        coursesByCode.put(code, new Course(code));
	        return true;
	    }

	    public Course getOrCreateCourse(String code) {
	        Course c = coursesByCode.get(code);
	        if (c != null) return c;
	        c = new Course(code);
	        coursesByCode.put(code, c);
	        return c;
	    }

	    public Student getStudentById(int id) { 
	    	return studentsById.get(id);
	    }

	    public Student getStudentByName(String name) { 
	    	return studentsByName.get(name);
	    }

	    public Course getCourse(String code) { 
	    	return coursesByCode.get(code);
	    }

	    // 选课（双写：学生侧与课程侧同时更新；先检查，再提交）
	    public boolean enroll(int studentId, String courseCode) {
	        Student s = studentsById.get(studentId);
	        Course  c = coursesByCode.get(courseCode);
	        if (s == null || c == null) return false;
	        if (s.hasGrade(courseCode)) return false;
	        if (s.isEnrolled(courseCode)) return false;
	        if (c.isEnrolled(studentId)) return false;

	        boolean okS = s.enroll(courseCode);
	        boolean okC = c.enroll(studentId);
	        return okS && okC;
	    }

	    public boolean unenroll(int studentId, String courseCode) {
	        Student s = studentsById.get(studentId);
	        Course  c = coursesByCode.get(courseCode);
	        if (s == null || c == null) return false;
	        if (!s.isEnrolled(courseCode)) return false;
	        if (!c.isEnrolled(studentId)) return false;

	        boolean okS = s.unenroll(courseCode);
	        boolean okC = c.unenroll(studentId);
	        return okS && okC;
	    }

	    // 给分（必须先在修）
	    public boolean assignGrade(int studentId, String courseCode, String grade) {
	        Student s = studentsById.get(studentId);
	        Course  c = coursesByCode.get(courseCode);
	        if (s == null || c == null) return false;
	        if (!s.isEnrolled(courseCode)) return false;
	        if (!c.isEnrolled(studentId)) return false;

	        boolean okS = s.assignGrade(courseCode, grade);
	        boolean okC = c.assignGrade(studentId, grade);
	        return okS && okC;
	    }

	    // 展示学生
	    public String showStudentById(int id) {
	        Student s = studentsById.get(id);
	        if (s == null) return "Student not found";
	        StringBuilder sb = new StringBuilder();
	        sb.append(s.toString()).append("\n");
	        sb.append("  Current: ").append(s.currentCoursesString()).append("\n");
	        sb.append("  Grades : ").append(s.completedGradesString()).append("\n");
	        return sb.toString();
	    }

	    public String showStudentByName(String name) {
	        Student s = studentsByName.get(name);
	        if (s == null) return "Student not found";
	        return showStudentById(s.getId());
	    }

	    // 展示课程
	    public String showCourse(String code) {
	        Course c = coursesByCode.get(code);
	        if (c == null) return "Course not found";
	        StringBuilder sb = new StringBuilder();
	        sb.append(c.toString()).append("\n");
	        sb.append("  Enrolled count: ").append(c.enrolledCount()).append("\n");
	        sb.append("  Enrolled ids  : ").append(c.enrolledString()).append("\n");
	        sb.append("  Final grades  : ").append(c.finalGradesString()).append("\n");
	        return sb.toString();
	    }
}
