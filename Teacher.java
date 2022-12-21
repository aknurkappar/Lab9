package Model;

import java.util.*;

public class Teacher extends Employee {
	
	public Vector<Course> courses;
	public HashMap<Course, Vector<File>> courseFiles;
	public Vector<Double> teacherRates = new Vector<Double>();
        
    public Department department;
    public TeacherLevel teacherLevel;
    
//    {
//    	courses = new Vector<Course>();
//    	courseFiles = new HashMap<Course, Vector<File>>();
//    	teacherRates = new Vector<Double>();
//    }
//    
    public Teacher(User user) {
    	super(user);
    }
    
    public Teacher(User user,  String firstLastName, 
    		String login, String password,
    		double salary, Department department, 
    		TeacherLevel teacherLevel) {
    	super(user, firstLastName, login, password);
    	this.setSalary(salary);;
    	this.department = department;
    	this.teacherLevel = teacherLevel;
    	
    	if (teacherLevel == TeacherLevel.ASSISTANT_PROFESSOR  || 
    		teacherLevel == TeacherLevel.ASSOCIATE_PROFESSOR || 
    		teacherLevel == TeacherLevel.PROFESSOR)
    		super.isResearcher = true;
    	else super.isResearcher = false;
    	
    	courses = new Vector<Course>();
    	courseFiles = new HashMap<Course, Vector<File>>();
    	teacherRates = new Vector<Double>();
    	teacherRates.add(0.0);
    } 
    
    
    
    public Teacher(User user, String firstLastName, double salary, Department department, TeacherLevel teacherLevel) {
    	super(user, firstLastName);
    	this.setSalary(salary);
    	this.department = department;
    	this.teacherLevel = teacherLevel;
    } 
    
    public Teacher(User user, String firstLastName, double salary, Department department, TeacherLevel teacherLevel, Vector<Course> courses) {
    	this(user, firstLastName, salary, department, teacherLevel);
    	this.courses = courses;
    } 
    
    
    //                          Operations                                  
    
    public Vector<Course> viewCourses() {
    	return this.courses;
    }
    
    
    public HashSet<Student> viewStudents() {
    	HashSet<Student> students = new HashSet<Student>();
    	this.courses.stream()
    			.forEach(c->c.getStudents().stream().
    					forEach(s->students.add(s)));
    	return students;
    }

    public boolean putMark(Student student, Course course, Mark mark) {	
    	if (student.getCoursesMarks().containsKey(course)) {
    		student.getCoursesMarks().put(course, mark);
    		return true;
    	}
    	return false;
    }
    
    public boolean addCourseFile(Course course, File file) {
    	Vector<File> files = courseFiles.get(course);
    	files.add(file);
    	courseFiles.put(course, files);
    	return true;
    }
    
    public boolean deleteCourseFile(Course course, File file) {
    	Vector<File> files = courseFiles.get(course);
    	if (files.isEmpty()) {
    		return false;
    	}
    	files.remove(file);
    	courseFiles.put(course, files);
    	return true;
    }
    
    public Double computeTeacherRate() {
    	return 0.0;
    }
    
    public String toString() {
    	return "Teacher: " + super.toString() + ", courses: " + courses + ", rating: " + computeTeacherRate() + ", department: " + department + ", level: " + teacherLevel;
    }
    
    public boolean equals(Object o) {
    	if (!super.equals(o)) {
    		return false;
    	}
    	Teacher teacher = (Teacher)o;
    	return 
    			teacher.courses == this.courses &&
    			teacher.courseFiles == this.courseFiles &&
    			teacher.computeTeacherRate() == this.computeTeacherRate() &&
    			teacher.department == this.department &&
    			teacher.teacherLevel == this.teacherLevel; 
    }
    
    public int hashCode() {
    	return super.hashCode() + 
    			Objects.hash(courses, courseFiles, 
    					computeTeacherRate(), department, 
    					teacherLevel);
    }
    
    public int compareTo(UserDecorator user) {
    	if (super.compareTo(user) != 0) {
    		return super.compareTo(user);
    	}
    	Teacher t = (Teacher)user;
    	if (this.computeTeacherRate() < t.computeTeacherRate()) { 
    		return -1;
    	}
    	else if (this.computeTeacherRate() > t.computeTeacherRate()) {
    		return 1;
    	}
    	return 0;
    }
    
    public Object clone() throws CloneNotSupportedException {
    	Teacher newTeacher = new Teacher(user);
    	newTeacher.department = (Department) this.department.clone();
    	newTeacher.teacherLevel = this.teacherLevel;
    	
    	Vector<Course> newCourses = new Vector<Course>();
    	for (Course c : this.courses) {
    		newCourses.add((Course) c.clone());
    	}
    	newTeacher.courses = newCourses;
    	
    	HashMap<Course, Vector<File>> newCourseFiles = new HashMap<Course, Vector<File>>();
    	for (Map.Entry<Course, Vector<File>> entry: this.courseFiles.entrySet()) {
    		Vector<File> newFiles = new Vector<File>();
    		for (File f: entry.getValue()) {
    			newFiles.add((File) f.clone());
    		}
    		newCourseFiles.put((Course) entry.getKey().clone(), newFiles);
    	}
    	newTeacher.courseFiles = newCourseFiles;
    	
    	newTeacher.teacherRates = (Vector<Double>) this.teacherRates.clone();
    	
    	return newTeacher;
    }
}





    public boolean registerCourse(Course course) {
    	if (Manager.approveCourseRegistration(this, course) 
    			&& Database.registrationCourses.contains(course)
    			&& computeCreditsForThisSemester() <= 21) {
    		this.coursesMarks.put(course, new Mark());
    		return true;
    	}
    	return false;
    }
    



