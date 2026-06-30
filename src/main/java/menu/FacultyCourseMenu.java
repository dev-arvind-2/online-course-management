package menu;

import java.util.List;
import java.util.Scanner;

import com.entity.FacultyCourse;
import com.exception.DatabaseOperationException;
import com.exception.ResourceNotFoundException;
import com.service.impl.FacultyCourseServiceImpl;

public class FacultyCourseMenu {

    private static FacultyCourseServiceImpl facultyCourseService =
            new FacultyCourseServiceImpl();

    public static void showMenu(Scanner sc) {
    	
    	try {

        int choice;

        do {

            System.out.println("\n===== FACULTY COURSE MENU =====");
            System.out.println("1. Assign Faculty To Course");
            System.out.println("2. View Faculty Assignments");
            System.out.println("3. Remove Assignment");
            System.out.println("4. Back");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    assignFacultyToCourse(sc);
                    break;

                case 2:
                    viewAssignments();
                    break;

                case 3:
                    removeAssignment(sc);
                    break;

                case 4:
                    System.out.println("Returning...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 4);
        
        
        
    	}catch(ResourceNotFoundException e) {
            System.out.println("NOT FOUND ERROR: " + e.getMessage());

		}catch(DatabaseOperationException e) {
			System.out.println("DATABASE ERROR: " + e.getMessage());
            e.printStackTrace();
            
		}catch (Exception e) {
            System.out.println("Unexpected error occurred!");
        }
    }

    
    
    
    
    
    
    private static void assignFacultyToCourse(Scanner sc) {

        System.out.print("Enter Faculty Id: ");
        int facultyId = sc.nextInt();

        System.out.print("Enter Course Id: ");
        int courseId = sc.nextInt();

        facultyCourseService.assignFacultyToCourse(
                facultyId, courseId);
    }

    
    
    
    
    
    private static void viewAssignments() {

        List<FacultyCourse> assignments =
                facultyCourseService.getAllAssignments();

        if (assignments.isEmpty()) {
            System.out.println("No Assignments Found.");
            return;
        }

        System.out.println("\n===== ASSIGNMENTS =====");

        for (FacultyCourse fc : assignments) {

            System.out.println(
                    "Assignment Id : " + fc.getId());

            System.out.println(
                    "Faculty Id    : "
                            + fc.getFaculty().getFacultyId());

            System.out.println(
                    "Faculty Name  : "
                            + fc.getFaculty().getName());

            System.out.println(
                    "Course Id     : "
                            + fc.getCourse().getCourseId());

            System.out.println(
                    "Course Name   : "
                            + fc.getCourse().getCourseName());

            System.out.println("--------------------------------");
        }
    }

    
    
    
    
    private static void removeAssignment(Scanner sc) {

        System.out.print("Enter Assignment Id: ");
        int id = sc.nextInt();

        facultyCourseService.removeAssignment(id);

    }
}