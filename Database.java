package Model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class Database {
		public static Vector<Request> requests = new Vector<Request>();
		public static Vector<News> news = new Vector<News>();
		public static Vector<Course> registrationCourses = new Vector<Course>();
		public static Vector<User> users = new Vector<User>();
		public static Vector<String> lastUserActions = new Vector<String>();
		public static HashMap<Book, Integer> booksInLibrary = new HashMap<Book, Integer>();
		public static HashMap<User, Map<LocalDate, Book>> borrowedBooks = new HashMap<User, Map<LocalDate, Book>>();
		public static Vector<String> allResearchPapers = new Vector<String>();
		public static Vector<String> allResearchProjects = new Vector<String>();
		public static Vector<Organization> organizations = new Vector<Organization>();
		public static Vector<Department> depatments = new Vector<Department>();

		private final static String BASE_PATH = "C:\\Users\\USER\\eclipse-workspace\\FinalProject\\Database\\";
		private static Database INSTANCE;
		private String path;
		
		static {
			try {
				INSTANCE = new Database(BASE_PATH);
			}
			catch (Exception e) {
				throw new RuntimeException("Exception occured in creating Singleton Database Instance");
			}
		}
		
		private Database(String path) {
			this.path = path;
		}
		
		public String getPath() {
			return this.path;
		}
	    
		public static Database getINSTANCE() {
//			if (INSTANCE == null) {
//				INSTANCE = new Database(BASE_PATH);
//			}
			return INSTANCE;
		}
		
		public static Vector<Teacher> getTeachersFromDB() { // streams
			return Database.users.stream().filter(user-> user instanceof Teacher)
			.map(user -> (Teacher) user)
			.collect(Collectors.toCollection(Vector::new));
			
		}
		
		public static Vector<Manager> getManagersFromDB() { // streams
			return Database.users.stream().filter(user-> user instanceof Manager)
			.map(user -> (Manager) user)
			.collect(Collectors.toCollection(Vector::new));
		}
	 
		public static Vector<Student> getStudentsFromDB() { // streams
			return Database.users.stream().filter(user-> user instanceof Student)
					.map(user -> (Student) user)
					.collect(Collectors.toCollection(Vector::new));
					
		}
	    
		public static Vector<Admin> getAdminsFromDB() { // streams
			return Database.users.stream().filter(user-> user instanceof Admin)
					.map(user -> (Admin) user)
					.collect(Collectors.toCollection(Vector::new));
		}
		
		public static Vector<Librarian> getLibrariansFromDB() { // streams
			return Database.users.stream().filter(user-> user instanceof Librarian)
					.map(user -> (Librarian) user)
					.collect(Collectors.toCollection(Vector::new));
		}
		
		public static Vector<ResearcherDecorator> getResearchersFromDB() { // streams
			return Database.users.stream().filter(user-> user instanceof ResearcherDecorator)
					.map(user -> (ResearcherDecorator) user)
					.collect(Collectors.toCollection(Vector::new));
		}
		
		public static Vector<Employee> getEmployeeFromDB() { // streams
			return Database.users.stream().filter(user-> user instanceof Employee)
					.map(user -> (Employee) user)
					.collect(Collectors.toCollection(Vector::new));
		}
		
		public static void serialize() {
			try (FileOutputStream fs = new FileOutputStream(Database.getINSTANCE().getPath() + "datas.txt")){                                
				ObjectOutputStream oos = new ObjectOutputStream(fs);
				oos.writeObject(Database.getINSTANCE());
				oos.flush();
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		public static void deserialize() {
			try (FileInputStream fis = new FileInputStream(Database.getINSTANCE().getPath() + "datas.txt")) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				INSTANCE = (Database) ois.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("Error with deserializing");
		}
	}

