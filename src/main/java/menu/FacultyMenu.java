package menu;

import java.util.List;
import java.util.Scanner;

import com.entity.Faculty;
import com.exception.DatabaseOperationException;
import com.exception.ResourceNotFoundException;
import com.exception.ValidationException;
import com.repository.FacultyCourseRepository;
import com.service.FacultyService;
import com.service.impl.FacultyCourseServiceImpl;
import com.service.impl.FacultyServiceImpl;

public class FacultyMenu {
	
	private static FacultyService facultyService=new FacultyServiceImpl();
	
	public static void showMenu(Scanner sc)
	{
		
		try
		{
		
		int choice;
		
		do {
			
			  System.out.println("\n----- FACULTY MANAGEMENT -----");
	            System.out.println("1. Add Faculty");
	            System.out.println("2. Update Faculty");
	            System.out.println("3. Delete Faculty");
	            System.out.println("4. View Faculty By Id");
	            System.out.println("5. View All Faculty");
	            System.out.println("6. Back");
	            
	            
	            System.out.println("Enter Choice: ");
	            choice=sc.nextInt();
	            
	            switch (choice) {
				case 1:
					addFaculty(sc);
					break;

				case 2:
					updateFaculty(sc);
					break;

				case 3:
					deleteFaculty(sc);
					break;

				case 4:
					viewFacultyById(sc);
					break;

				case 5:
					viewAllFaculty();
					break;

				case 6:
					System.out.println("Returning to Main Menu...");
					break;

				default:
					System.out.println("Invalid Choice");

				}
			
			
		}while(choice!=6);
		
		
		}catch(ResourceNotFoundException e) {
            System.out.println("NOT FOUND ERROR: " + e.getMessage());

		}catch(DatabaseOperationException e) {
			System.out.println("DATABASE ERROR: " + e.getMessage());
            e.printStackTrace();
		}catch(ValidationException e) {
			
			 System.out.println("\n=================================");
			 System.out.println(" VALIDATION FAILED ");
			 System.out.println("=================================");
			 System.err.println(e.getMessage());
			    
			
		} catch (Exception e) {

		    System.out.println("\n System Error occurred. Please contact admin.");
		}
	}
	
	
	
	
	
//	===================================================
	
	private static void addFaculty(Scanner sc)
	{
		System.out.println("Enter Faculty Name : ");
		String name = sc.next();

		System.out.println("Enter Faculty Email: ");
		String email = sc.next();

		System.out.println("Enter Specialization : ");
		String specialization = sc.next();

		System.out.println("Enter experience: ");
		int experience = sc.nextInt();

		
		Faculty faculty = Faculty.builder()
					            .name(name)
					            .email(email)
					            .specialization(specialization)
					            .experience(experience)
					            .build();

		     facultyService.addFaculty(faculty);

		System.err.println("Faculty Added Succesfully");
	}
	
	
//	===================================================
	
	private static void updateFaculty(Scanner sc) {

	    System.out.print("Enter Faculty Id: ");
	    int id = sc.nextInt();

	    System.out.println("\n===== UPDATE FACULTY MENU =====");
	    System.out.println("1. Update Name");
	    System.out.println("2. Update Email");
	    System.out.println("3. Update Specialization");
	    System.out.println("4. Update Experience");
	    System.out.println("5. Update All Details");

	    System.out.print("Enter Choice: ");
	    int choice = sc.nextInt();

	    Faculty faculty = Faculty.builder()
	            .facultyId(id)
	            .build();

	    switch (choice) {

	    case 1:
	        System.out.print("Enter New Name: ");
	        faculty.setName(sc.next());
	        break;

	    case 2:
	        System.out.print("Enter New Email: ");
	        faculty.setEmail(sc.next());
	        break;

	    case 3:
	        System.out.print("Enter New Specialization: ");
	        faculty.setSpecialization(sc.next());
	        break;

	    case 4:
	        System.out.print("Enter New Experience: ");
	        faculty.setExperience(sc.nextInt());
	        break;

	    case 5:

	        System.out.print("Enter New Name: ");
	        faculty.setName(sc.next());

	        System.out.print("Enter New Email: ");
	        faculty.setEmail(sc.next());

	        System.out.print("Enter New Specialization: ");
	        faculty.setSpecialization(sc.next());

	        System.out.print("Enter New Experience: ");
	        faculty.setExperience(sc.nextInt());

	        break;

	    default:
	        System.out.println("Invalid Choice");
	        return;
	    }

	    facultyService.updateFaculty(faculty);

	    System.out.println("Faculty Updated Successfully");
	}
	
	
//	===================================================

	private static void deleteFaculty(Scanner sc) {
		System.out.println("Enter Faculty Id: ");
		int id = sc.nextInt();

		System.out.print("Are you sure you want to delete this Faculty? (yes/no): ");
		String confirm = sc.next();

		if (confirm.equalsIgnoreCase("yes")) {
			facultyService.deleteFaculty(id);
			System.err.println("Faculty Deleted Successfully.");
		} else {
			System.err.println("Delete Operation Cancelled.");
		}
	}
	
	
	
	
	
//	===================================================


	private static void viewFacultyById(Scanner sc) {

		System.out.print("Enter Student Id: ");
		int id = sc.nextInt();

		Faculty faculty = facultyService.getFacultyById(id);

		if (faculty != null) {

			System.out.println("\nFaculty Details");
			System.out.println("ID    : " + faculty.getFacultyId());
			System.out.println("Name  : " + faculty.getName());
			System.out.println("Email : " + faculty.getEmail());
			System.out.println("Experience : " + faculty.getExperience());
			System.out.println("Specializaition : " + faculty.getSpecialization());

		} else {
			System.err.println("Facultys Not Found.");
		}
	}

	
	
	
//	===================================================

	private static void viewAllFaculty() {

		List<Faculty> facultyies = facultyService.getAllFaculties();

		if (facultyies.isEmpty()) {
			System.err.println("No Faculty Found.");
			return;
		}

		System.out.println("\n===== FACULTY LIST =====");

		for (Faculty faculty : facultyies) {

			System.out.println("------------------------");
			System.out.println("ID    : " + faculty.getFacultyId());
			System.out.println("Name  : " + faculty.getName());
			System.out.println("Email : " + faculty.getEmail());
			System.out.println("Mobile : " + faculty.getExperience());
			System.out.println("City : " + faculty.getSpecialization());
		}
	}
	
	
	
	
	
	


}



