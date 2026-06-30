package menu;

import java.util.List;
import java.util.Scanner;

import com.service.StudentService;
import com.service.FacultyService;
import com.entity.Course;
import com.entity.Enrollment;
import com.entity.Faculty;
import com.entity.Student;
import com.exception.DatabaseOperationException;
import com.exception.ResourceNotFoundException;
import com.exception.ValidationException;
import com.service.CourseService;
import com.service.EnrollmentService;

import com.service.impl.StudentServiceImpl;
import com.service.impl.FacultyServiceImpl;
import com.service.impl.CourseServiceImpl;
import com.service.impl.EnrollmentServiceImpl;

public class ReportMenu {

    private static StudentService studentService = new StudentServiceImpl();
    private static FacultyService facultyService = new FacultyServiceImpl();
    private static CourseService courseService = new CourseServiceImpl();
    private static EnrollmentService enrollmentService = new EnrollmentServiceImpl();

    public static void showMenu(Scanner sc) {
    	
    	try {

        int choice;

        do {

            System.out.println("\n----- REPORTS -----");
            System.out.println("1. Total Students");
            System.out.println("2. Total Faculty");
            System.out.println("3. Total Courses");
            System.out.println("4. Total Enrollment");
            System.out.println("5. Back");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

             case 1:

			
//			                System.out.println("Total Students : "+ studentService.getTotalStudents());
            	 
	                		List<Student> students =studentService.getAllStudents();
			                System.out.println("\n==============================================");
			                System.out.println("Total Students : " + students.size());
			                System.out.println("==============================================");
			                
			                System.out.println("\n╔════════════════════════════════════════════════════════════╗");
			                System.out.println("║                    STUDENT REPORT                         ║");
			                System.out.println("╠════╦══════════════════════╦═══════════════════════════════╣");

			                for (Student s : students) {

			                    System.out.printf(
			                    		 "║ %-2d ║ %-20s ║ %-29s ║%n",
			                            s.getStudentId(),
			                            s.getName(),
			                            s.getEmail()
			                    );
			                }

			               
			                System.out.println("╚════╩══════════════════════╩═══════════════════════════════╝");
			             
			                break;
			                
			                
                case 2:
//                    System.err.println("Total Faculty: " + facultyService.getTotalFaculty());
                	
                			List<Faculty> faculty = facultyService.getAllFaculties();
			                System.out.println("==============================================");
                			System.out.println("Total Faculty : "+faculty.size());
                			System.out.println();
			           
			                System.out.println("\n╔════════════════════════════════════════════════════════════╗");
			                System.out.println("║                   FACULTY DETAILS                         ║");
			                System.out.println("╠════╦══════════════════════╦═══════════════════════════════╣");
			                
			                for(Faculty f:faculty)
			                {
			                	
			                
			                	  System.out.printf(
			                			  "║ %-2d ║ %-20s ║ %-29s ║%n",
				                            f.getFacultyId(),
				                            f.getName(),
				                			 f.getEmail(),
				                			 f.getSpecialization(),
				                			 f.getExperience()
				                    );
			                }
			                
			                System.out.println("╚════╩══════════════════════╩═══════════════════════════════╝");
                    break;

                case 3:
//                    System.err.println("Total Courses: " + courseService.getTotalCourses());
                	
                	List<Course> course = courseService.getAllCourses();
                	  System.out.println("==============================================");
          			System.out.println("Total course : "+course.size());
          			System.out.println();
	
		                
		                System.out.println("\n╔════════════════════════════════════════════════════════════╗");
		                System.out.println("║                  COURSE DETAILS                           ║");
		                System.out.println("╠════╦══════════════════════╦═══════════════════════════════╣");
		                for(Course c:course)
		                {
		                	
		                
		                	  System.out.printf(
		                			  "║ %-2d ║ %-20s ║ %-29s ║%n",
			                            c.getCourseId(),
			                            c.getCourseCode(),
			                            c.getCourseName(),
			                            c.getDuration(),
			                            c.getFees()
			                             
			                    );
		                }
		                System.out.println("╚════╩══════════════════════╩═══════════════════════════════╝");
                    break;

                case 4:
//                    System.err.println("Total Enrollment: " + enrollmentService.getTotalEnrollments());
                	List<Enrollment> enrollment = enrollmentService.getAllEnrollments();
              	  System.out.println("==============================================");
        			System.out.println("Total Enrollment : "+enrollment.size());
        			System.out.println();
		                
		               
		                
		                System.out.println("\n╔════════════════════════════════════════════════════════════╗");
		                System.out.println("║                  ENROLLMENT DETAILS                       ║");
		                System.out.println("╠════╦══════════════════════╦═══════════════════════════════╣");
		                
		                for(Enrollment e:enrollment)
		                {
		                	
		                
		                	  System.out.printf(
		                			  "║ %-2d ║ %-20s ║ %-29s ║%n",
			                            e.getEnrollmentId(),
			                            e.getCourse().getCourseName(),
			                            e.getStudent().getName(),
			                            e.getEnrollmentDate()
			                       
			                    );
		                }
		                
		                System.out.println("╚════╩══════════════════════╩═══════════════════════════════╝");
                	
                    break;

                case 5:
                    System.err.println("Back to Main Menu...");
                    break;

                default:
                    System.err.println("Invalid Choice");
            }

        } while (choice != 5);
        
        
    	}catch(ResourceNotFoundException e) {
            System.out.println("NOT FOUND ERROR: " + e.getMessage());

		}catch(DatabaseOperationException e) {
			System.out.println("DATABASE ERROR: " + e.getMessage());
            e.printStackTrace();
            
		}catch (Exception e) {

		    System.out.println("\n System Error occurred. Please contact admin.");
		}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//  ===============================================================
//====== Case 6 Operation =========================================================
  
  public static void viewStudentCourse(Scanner sc) {
  	
  	try {

      System.out.print("Enter Student Id: ");
      int studentId = sc.nextInt();

      List<Enrollment> enrollments = enrollmentService.getCoursesByStudent(studentId);

      if (enrollments == null || enrollments.isEmpty()) {

          System.err.println("No Courses Found For This Student.");

      } else {

          System.out.println("\n========== STUDENT COURSES ==========");

          enrollments.forEach(e -> {

          	System.out.println("----------------------------------");
          	System.out.println("Student Id     : " + e.getStudent().getStudentId());
          	System.out.println("Student Name     : " + e.getStudent().getName());
              System.out.println("----------------------------------");
              System.out.println("Course Id     : " + e.getCourse().getCourseId());
              System.out.println("Course Name   : " + e.getCourse().getCourseName());
              System.out.println("Course Code   : " + e.getCourse().getCourseCode());
              System.out.println("Duration      : " + e.getCourse().getDuration());
              System.out.println("Fees          : " + e.getCourse().getFees());
              System.out.println("Enroll Date   : " + e.getEnrollmentDate());
          });

          System.out.println("====================================\n");
      }
      
  	}catch(DatabaseOperationException e) {
  		System.out.println("Database Error : "+e.getMessage());
  		
  	}catch(Exception e) {
 		 System.err.println("Unexpected Error : " + e.getMessage());
  	}
  }
  
  
  
//========= Case 7 Operation ======================================================
  
  public static void viewCourseStudent(Scanner sc) {
  	
  	try {

      System.out.println("View Course Students");

      System.out.print("Enter Course Id: ");
      int courseId = sc.nextInt();

      List<Enrollment> enrollments =
              enrollmentService.getStudentsByCourse(courseId);

      if (enrollments == null || enrollments.isEmpty()) {

          System.err.println("No Students Found For This Course.");

      } else {

          System.out.println("\n========== COURSE STUDENTS ==========");

          enrollments.forEach(e -> {

              System.out.println("----------------------------------");
              System.out.println("Course Id     : " + e.getCourse().getCourseId());
              System.out.println("Course Name   : " + e.getCourse().getCourseName());
              System.out.println("----------------------------------");
              System.out.println("Student Id    : " + e.getStudent().getStudentId());
              System.out.println("Name          : " + e.getStudent().getName());
              System.out.println("Email         : " + e.getStudent().getEmail());
              System.out.println("Mobile        : " + e.getStudent().getMobile());
              System.out.println("City          : " + e.getStudent().getCity());
              System.out.println("----------------------------------");
          });

          System.out.println("====================================\n");
      }
      
  	}catch(DatabaseOperationException e) {
  		
  		System.out.println("Database Error : "+e.getMessage());
  	
  	}catch(Exception e) {
  		 System.err.println("Unexpected Error : " + e.getMessage());
  	}
  }   
}