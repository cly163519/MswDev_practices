import java.io.*;
import java.util.*;

public class Main {//try{}catch块必须写到main函数里面?还是写到构造里面?还是随便任意一个方法?但是不能鼓励出现在主类里?
//	List<Student> students = new ArrayList<>();
//	List<Course> courses = new ArrayList<>();
//	
//	public Main() {
//		try {
//			Scanner scanner = new Scanner(new File("students_data.txt"));
//			while(scanner.hasNext()) {
//				String courseId = scanner.nextLine().trim();
//				String score = scanner.nextLine().trim();
//				String studentName = scanner.nextLine().trim();
//				
//				Student student = new Student(studentName, score);
//				students.add(student);
//			}
//			
//		}catch(IOException e) {
//			
//		}
//	}
//	
//	public void listCourse() {
//		
//	}
//	
//	public void listStudents() {
//		
//	}
//	
//	public static void main(String[] args) {
//		new Main();
//	}
	
	    private static final Scanner in = new Scanner(System.in);

	    public static void main(String[] args) {
	        UniversityAdmin admin = new UniversityAdmin();

	        // 只根据你的 txt 文件导入（可用命令行参数覆盖路径）
	        String path = "students_data.txt";
	        if (args != null && args.length > 0 && args[0] != null && args[0].trim().length() > 0) {
	            path = args[0].trim();
	        }
	        loadData(path, admin);

	        // 交互式命令（在导入的数据基础上操作）
	        while (true) {
	            System.out.println("\nCommands: add-stu, add-crs, by-id, by-name, course, enroll, unenroll, grade, quit");
	            System.out.print("> ");
	            if (!in.hasNext()) break;
	            String cmd = in.next().trim().toLowerCase();

	            if ("add-stu".equals(cmd)) {
	                System.out.print("id name: ");
	                if (!in.hasNextInt()) { System.out.println("bad id"); in.nextLine(); continue; }
	                int id = in.nextInt();
	                String name = in.next();
	                System.out.println(admin.addStudent(id, name) ? "OK" : "FAIL");
	            } else if ("add-crs".equals(cmd)) {
	                System.out.print("code: ");
	                String code = in.next();
	                System.out.println(admin.addCourse(code) ? "OK" : "FAIL");
	            } else if ("by-id".equals(cmd)) {
	                System.out.print("id: ");
	                if (!in.hasNextInt()) { System.out.println("bad id"); in.nextLine(); continue; }
	                int id = in.nextInt();
	                System.out.println(admin.showStudentById(id));
	            } else if ("by-name".equals(cmd)) {
	                System.out.print("name: ");
	                String name = in.next();
	                System.out.println(admin.showStudentByName(name));
	            } else if ("course".equals(cmd)) {
	                System.out.print("code: ");
	                String code = in.next();
	                System.out.println(admin.showCourse(code));
	            } else if ("enroll".equals(cmd)) {
	                System.out.print("studentId courseCode: ");
	                if (!in.hasNextInt()) { System.out.println("bad id"); in.nextLine(); continue; }
	                int id = in.nextInt();
	                String code = in.next();
	                System.out.println(admin.enroll(id, code) ? "OK" : "FAIL");
	            } else if ("unenroll".equals(cmd)) {
	                System.out.print("studentId courseCode: ");
	                if (!in.hasNextInt()) { System.out.println("bad id"); in.nextLine(); continue; }
	                int id = in.nextInt();
	                String code = in.next();
	                System.out.println(admin.unenroll(id, code) ? "OK" : "FAIL");
	            } else if ("grade".equals(cmd)) {
	                System.out.print("studentId courseCode grade: ");
	                if (!in.hasNextInt()) { System.out.println("bad id"); in.nextLine(); continue; }
	                int id = in.nextInt();
	                String code = in.next();
	                String g = in.next();
	                System.out.println(admin.assignGrade(id, code, g) ? "OK" : "FAIL");
	            } else if ("quit".equals(cmd)) {
	                break;
	            } else {
	                System.out.println("Unknown command.");
	            }
	        }
	    }

	    // 支持两种行：
	    // 1) CODE - Name                 -> 在修
	    // 2) CODE GRADE Name(with space) -> 已修有成绩
	    static void loadData(String path, UniversityAdmin admin) {
	        File f = new File(path);
	        if (!f.exists()) {
	            System.out.println("No data file: " + path + " (skip)");
	            return;
	        }
	        System.out.println("Loading data from: " + path);

	        BufferedReader br = null;
	        int lineNo = 0, enrolledCnt = 0, gradedCnt = 0, createdStu = 0, createdCrs = 0;
	        HashSet<String> seenCourses = new HashSet<String>();
	        HashSet<String> seenStudents = new HashSet<String>(); // 用姓名统计新建人数

	        try {
	            br = new BufferedReader(new FileReader(f));
	            String line;
	            while ((line = br.readLine()) != null) {
	                lineNo++;
	                line = line.trim();
	                if (line.length() == 0) continue;

	                int dash = line.indexOf(" - ");
	                if (dash >= 0) {
	                    // 形如：COMP202 - Alan Butler
	                    String code = line.substring(0, dash).trim();
	                    String name = line.substring(dash + 3).trim();

	                    if (code.length() == 0 || name.length() == 0) {
	                        System.out.println("skip invalid line " + lineNo + ": " + line);
	                        continue;
	                    }

	                    Course c = admin.getOrCreateCourse(code);
	                    if (!seenCourses.contains(code)) { createdCrs++; seenCourses.add(code); }

	                    Student s = admin.getOrCreateByName(name);
	                    if (!seenStudents.contains(name)) { createdStu++; seenStudents.add(name); }

	                    if (admin.enroll(s.getId(), c.getCode())) enrolledCnt++;
	                } else {
	                    // 形如：COMP202 A+ David Davis
	                    String[] parts = line.split("\\s+");
	                    if (parts.length >= 3) {
	                        String code = parts[0];
	                        String grade = parts[1];

	                        StringBuilder sb = new StringBuilder();
	                        int i = 2;
	                        while (i < parts.length) {
	                            sb.append(parts[i]);
	                            if (i + 1 < parts.length) sb.append(" ");
	                            i++;
	                        }
	                        String name = sb.toString();

	                        if (code.length() == 0 || grade.length() == 0 || name.length() == 0) {
	                            System.out.println("skip invalid line " + lineNo + ": " + line);
	                            continue;
	                        }

	                        Course c = admin.getOrCreateCourse(code);
	                        if (!seenCourses.contains(code)) { createdCrs++; seenCourses.add(code); }

	                        Student s = admin.getOrCreateByName(name);
	                        if (!seenStudents.contains(name)) { createdStu++; seenStudents.add(name); }

	                        // 确保在修，再给分
	                        if (!s.isEnrolled(code) || !c.isEnrolled(s.getId())) {
	                            admin.enroll(s.getId(), c.getCode());
	                            enrolledCnt++;
	                        }
	                        if (admin.assignGrade(s.getId(), c.getCode(), grade)) gradedCnt++;
	                    } else {
	                        // 其它无效行（比如单独一个字母）
	                        System.out.println("skip invalid line " + lineNo + ": " + line);
	                    }
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("loadData error: " + e.getMessage());
	        } finally {
	            if (br != null) try { br.close(); } catch (IOException ignore) {}
	        }

	        System.out.println("Loaded summary: students+" + createdStu +
	                ", courses+" + createdCrs +
	                ", enrolled=" + enrolledCnt +
	                ", graded=" + gradedCnt);
	    }
	
}
