package menu;

import java.util.Scanner;

import com.exception.DatabaseOperationException;
import com.exception.DuplicateResourceException;
import com.exception.ResourceNotFoundException;
import com.service.EnrollmentService;
import com.service.impl.EnrollmentServiceImpl;

public class EnrollmentMenu {

    private static EnrollmentService enrollmentService =
            new EnrollmentServiceImpl();

    public static void showMenu(Scanner sc) {
    	
    	
    	try
    	{

        int choice;

        do {

            System.out.println("\n===== ENROLLMENT MANAGEMENT =====");
            System.out.println("1. Enroll Student To Course");
            System.out.println("2. View All Enrollments");
            System.out.println("3. Delete Enrollment");
            System.out.println("4. Back");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    enrollStudentToCourse(sc);
                    break;

                case 2:
                    viewAllEnrollments();
                    break;

                case 3:
                    deleteEnrollment(sc);
                    break;

                case 0:
                    System.out.println("Returning To Main Menu...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);
        
        
    	} catch (ResourceNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        catch (DuplicateResourceException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        catch (DatabaseOperationException e) {
            System.out.println("SYSTEM ERROR: Something went wrong!");
        }
        catch (Exception e) {
            System.out.println("Unexpected error occurred!");
        }
        
    }

    
    
    
//    ===================================================================
    private static void enrollStudentToCourse(Scanner sc) {

        System.out.print("Enter Student Id: ");
        int studentId = sc.nextInt();

        System.out.print("Enter Course Id: ");
        int courseId = sc.nextInt();

        enrollmentService.enrollStudent(studentId, courseId);
    }
    
    
    
    
//  ===================================================================


    private static void viewAllEnrollments() {

        System.out.println("\n================ ENROLLMENT LIST ================");

        enrollmentService.getAllEnrollments().forEach(e -> {

            System.out.println("----------------------------------");
            System.out.println("Enrollment Id : " + e.getEnrollmentId());
            System.out.println("Student Id     : " + e.getStudent().getStudentId());
            System.out.println("Student Name   : " + e.getStudent().getName());
            System.out.println("Course Id     : " + e.getCourse().getCourseId());
            System.out.println("Course Name   : " + e.getCourse().getCourseName());
            System.out.println("Enroll Date   : " + e.getEnrollmentDate());
        });

        System.out.println("===============================================\n");
    }    
    
    
//  ===================================================================

    private static void deleteEnrollment(Scanner sc) {

        System.out.print("Enter Enrollment Id: ");
        int enrollmentId = sc.nextInt();

        System.out.print("Are you sure? (yes/no): ");
        String confirm = sc.next();

        if (confirm.equalsIgnoreCase("yes")) {

            enrollmentService.deleteEnrollment(enrollmentId);

        } else {

            System.out.println("Deletion Cancelled.");
        }
    }
}