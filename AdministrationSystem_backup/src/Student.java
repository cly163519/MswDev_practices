import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Student {
	private final int id;
	private final String name;
//	String name;//data.txt里面没有studentI的,在设计系统时需要加入嘛?
//	String score;
	//List<Course> regCourse = new ArrayList<>();一个学生不是对应多门课程嘛?不需要这个集合?
	
	 // 当前在修：课程代码集合
    private final HashSet<String> currentCourses = new HashSet<String>();
    // 已修成绩：课程代码 -> 成绩
    private final HashMap<String, String> completedGrades = new HashMap<String, String>();

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public boolean isEnrolled(String courseCode) {
        return currentCourses.contains(courseCode);
    }

    public boolean hasGrade(String courseCode) {
        return completedGrades.containsKey(courseCode);
    }

    public boolean enroll(String courseCode) {
        if (completedGrades.containsKey(courseCode)) return false;
        return currentCourses.add(courseCode);
    }

    public boolean unenroll(String courseCode) {
        return currentCourses.remove(courseCode);
    }

    public boolean assignGrade(String courseCode, String grade) {
        if (!currentCourses.contains(courseCode)) return false;
        currentCourses.remove(courseCode);
        completedGrades.put(courseCode, grade);
        return true;
    }

    public String currentCoursesString() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = currentCourses.iterator();
        sb.append("[");
        while (it.hasNext()) {
            String c = it.next();
            sb.append(c);
            if (it.hasNext()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public String completedGradesString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Iterator<Map.Entry<String, String>> it = completedGrades.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            sb.append(e.getKey()).append("=").append(e.getValue());
            if (it.hasNext()) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}
