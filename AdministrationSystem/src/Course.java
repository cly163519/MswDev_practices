import java.util.*;
import java.util.List;

public class Course {
//	String courseId;
//	List<Grade> grades = new ArrayList<>();
//	List<Student> studentLists = new ArrayList<>();//如何初始化List?一个课程有很多学生选择
//	
//	public Course(String courseId) {//不能在这里初始化List?
//		this.courseId = courseId;
//		
//	}
//	
//	
//	public String courseName() {
//		return courseId;
//	}
	
	    private final String code;
	    // 在修学生 id 集合
	    private final HashSet<Integer> enrolled = new HashSet<Integer>();
	    // 已给分：学生 id -> 成绩
	    private final HashMap<Integer, String> finalGrades = new HashMap<Integer, String>();

	    public Course(String code) {
	        this.code = code;
	    }

	    public String getCode() { return code; }

	    public boolean isEnrolled(int studentId) {
	        return enrolled.contains(studentId);
	    }

	    public boolean hasGraded(int studentId) {
	        return finalGrades.containsKey(studentId);
	    }

	    public boolean enroll(int studentId) {
	        if (finalGrades.containsKey(studentId)) return false;
	        return enrolled.add(studentId);
	    }

	    public boolean unenroll(int studentId) {
	        return enrolled.remove(studentId);
	    }

	    public boolean assignGrade(int studentId, String grade) {
	        if (!enrolled.contains(studentId)) return false;
	        enrolled.remove(studentId);
	        finalGrades.put(studentId, grade);
	        return true;
	    }

	    public String enrolledString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("[");
	        Iterator<Integer> it = enrolled.iterator();
	        while (it.hasNext()) {
	            int id = it.next();
	            sb.append(id);
	            if (it.hasNext()) sb.append(", ");
	        }
	        sb.append("]");
	        return sb.toString();
	    }

	    public String finalGradesString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("{");
	        Iterator<Map.Entry<Integer, String>> it = finalGrades.entrySet().iterator();
	        while (it.hasNext()) {
	            Map.Entry<Integer, String> e = it.next();
	            sb.append(e.getKey()).append("=").append(e.getValue());
	            if (it.hasNext()) sb.append(", ");
	        }
	        sb.append("}");
	        return sb.toString();
	    }

	    public int enrolledCount() {
	        return enrolled.size();
	    }

	    @Override
	    public String toString() {
	        return "Course{code='" + code + "'}";
	    }
	
}
