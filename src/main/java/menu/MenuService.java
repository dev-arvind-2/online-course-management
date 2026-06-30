package menu;


import java.util.Scanner;

import com.entity.User;
import com.enumCode.Role;

public class MenuService {

    public static void startMenu(User user, Scanner sc) {

    	
        boolean isAdmin = (user.getRole() == Role.ADMIN);

        
        
        
        boolean loggedIn = true;

        while (loggedIn) {

        	
        	
            System.out.println("\n===== ONLINE COURSE MANAGEMENT SYSTEM =====");

          
            if (isAdmin) {

                System.out.println("1. Student Management");
                System.out.println("2. Faculty Management");
                System.out.println("3. Course Management");
                System.out.println("4. Assign Faculty To Course");
                System.out.println("5. Enroll Student In Course");
                System.out.println("6. View Student Courses");
                System.out.println("7. View Course Student");
                System.out.println("8. Reports");
                System.out.println("9. Logout");
                System.out.println("10. Exit System");

            } else {

                System.out.println("1. View Course Details");
                System.out.println("2. Logout");
                System.out.println("3. Exit System");
            }
            
            
            
            
            

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            if (isAdmin) {

                switch (choice) {

                    case 1: StudentMenu.showMenu(sc);
                    		break;
                    		
                    case 2: FacultyMenu.showMenu(sc); 
                    		break;
                    		
                    case 3: CourseMenu.showMenu(sc); 
                    		break;
                    		
                    case 4: FacultyCourseMenu.showMenu(sc); 
                    		break;
                    		
                    case 5: EnrollmentMenu.showMenu(sc); 
                    		break;
                    		
                    case 6: ReportMenu.viewStudentCourse(sc); 
                    		break;
                    		
                    case 7: ReportMenu.viewCourseStudent(sc); 
                    		break;
                    		
                    case 8: ReportMenu.showMenu(sc); 
                    		break;

                    case 9:
	                        System.out.println("Logging out...");
	                        loggedIn = false;
	                        break;

                    case 10:
	                        System.out.println("Exiting System...");
	                        System.exit(0);

                    default:
                        	System.out.println("Invalid Choice");
                }

            } else {

                switch (choice) {

                    case 1:
                        ReportMenu.showMenu(sc);
                        break;

                    case 2:
                        System.out.println("Logging out...");
                        loggedIn = false;
                        break;

                    case 3:
                        System.out.println("Exiting System...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }
            }
        }
    }
}
