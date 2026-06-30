package menu;

import java.util.List;
import java.util.Scanner;

import com.entity.Student;
import com.exception.DatabaseOperationException;
import com.exception.ResourceNotFoundException;
import com.exception.ValidationException;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;

public class StudentMenu {

	private static StudentService studentService = new StudentServiceImpl();

	public static void showMenu(Scanner sc) {
		
		
		try
		{
			int choice;
		

		do {

			System.out.println("\n----- STUDENT MANAGEMENT -----");
			System.out.println("1. Add Student");
			System.out.println("2. Update Student");
			System.out.println("3. Delete Student");
			System.out.println("4. View Student By Id");
			System.out.println("5. View All Students");
			System.out.println("6. Back");

			System.out.println("Enter Choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				addStudent(sc);
				break;

			case 2:
				updateStudent(sc);
				break;

			case 3:
				deleteStudent(sc);
				break;

			case 4:
				viewStudentById(sc);
				break;

			case 5:
				viewAllStudents();
				break;

			case 6:
				System.out.println("Returning to Main Menu...");
				break;

			default:
				System.out.println("Invalid Choice");

			}

		} while (choice != 6);
		
		
		}catch(ResourceNotFoundException e) {
            System.out.println("NOT FOUND ERROR: " + e.getMessage());

		}catch(DatabaseOperationException e) {
			System.out.println("DATABASE ERROR: " + e.getMessage());
            e.printStackTrace();
            
		} catch(ValidationException e) {
			
			 System.out.println("\n=================================");
			 System.out.println(" VALIDATION FAILED ");
			 System.out.println("=================================");
			 System.err.println(e.getMessage());
			    
			
		} catch (Exception e) {

		    System.out.println("\n System Error occurred. Please contact admin.");
		}
	}

	 
	
	
	
	private static void addStudent(Scanner sc) {

		System.out.println("Enter Student Name : ");
		String name = sc.next();

		System.out.println("Enter Student Email: ");
		String email = sc.next();

		System.out.println("Enter Student Mobile No : ");
		String mobile = sc.next();

		System.out.println("Enter Student City: ");
		String city = sc.next();

		
		   Student student = Student.builder()
					            .name(name)
					            .email(email)
					            .mobile(mobile)
					            .city(city)
					            .build();

		    studentService.addStudent(student);

		System.err.println("Student Added Succesfully");

	}
	
	
	
	

	private static void updateStudent(Scanner sc) {

		 System.out.print("Enter Student Id: ");
	        int id = sc.nextInt();

	        System.out.println("\n===== UPDATE MENU =====");
	        System.out.println("1. Update Name");
	        System.out.println("2. Update Email");
	        System.out.println("3. Update Mobile");
	        System.out.println("4. Update City");
	        System.out.println("5. Update All Details");

	        System.out.print("Enter Choice: ");
	        int choice = sc.nextInt();

	        Student student = Student.builder()
	                .studentId(id)
	                .build();

	        switch(choice) {

	        case 1:

	            System.out.print("Enter New Name: ");
	            student.setName(sc.next());

	            break;

	        case 2:

	            System.out.print("Enter New Email: ");
	            student.setEmail(sc.next());

	            break;

	        case 3:

	            System.out.print("Enter New Mobile: ");
	            student.setMobile(sc.next());

	            break;

	        case 4:

	            System.out.print("Enter New City: ");
	            student.setCity(sc.next());

	            break;

	        case 5:

	            System.out.print("Enter New Name: ");
	            student.setName(sc.next());

	            System.out.print("Enter New Email: ");
	            student.setEmail(sc.next());

	            System.out.print("Enter New Mobile: ");
	            student.setMobile(sc.next());

	            System.out.print("Enter New City: ");
	            student.setCity(sc.next());

	            break;

	        default:
	            System.out.println("Invalid Choice");
	            return;
	        }

	        studentService.updateStudent(student);

	        System.out.println("Student Updated Successfully");
		
		
		

	}
	
	
	

	private static void deleteStudent(Scanner sc) {
		System.out.println("Enter Student Id: ");
		int id = sc.nextInt();

		System.out.print("Are you sure you want to delete this student? (yes/no): ");
		String confirm = sc.next();

		if (confirm.equalsIgnoreCase("yes")) {
			studentService.deleteStudent(id);
			System.err.println("Student Deleted Successfully.");
		} else {
			System.err.println("Delete Operation Cancelled.");
		}
	}
	
	
	
	

	private static void viewStudentById(Scanner sc) {

		System.out.print("Enter Student Id: ");
		int id = sc.nextInt();

		Student student = studentService.getStudentById(id);

		if (student != null) {

			System.out.println("\nStudent Details");
			System.out.println("ID    : " + student.getStudentId());
			System.out.println("Name  : " + student.getName());
			System.out.println("Email : " + student.getEmail());
			System.out.println("Mobile : " + student.getMobile());
			System.out.println("City : " + student.getCity());

		} else {
			System.err.println("Student Not Found.");
		}
	}

	
	
	
	private static void viewAllStudents() {

		List<Student> students = studentService.getAllStudents();

		if (students.isEmpty()) {
			System.err.println("No Students Found.");
			return;
		}

		System.out.println("\n===== STUDENT LIST =====");

		for (Student student : students) {

			System.out.println("------------------------");
			System.out.println("ID    : " + student.getStudentId());
			System.out.println("Name  : " + student.getName());
			System.out.println("Email : " + student.getEmail());
			System.out.println("Mobile : " + student.getMobile());
			System.out.println("City : " + student.getCity());
		}
	}
	
	

}
