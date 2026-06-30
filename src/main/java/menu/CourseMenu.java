package menu;

import java.util.List;
import java.util.Scanner;

import com.entity.Course;
import com.exception.DatabaseOperationException;
import com.exception.ResourceNotFoundException;
import com.exception.ValidationException;
import com.service.CourseService;
import com.service.impl.CourseServiceImpl;

public class CourseMenu {
	
	private static CourseService courseService=new CourseServiceImpl();
	
	public static void showMenu(Scanner sc) {
		
		try {

        int choice;

        do {

            System.out.println("\n----- COURSE MANAGEMENT -----");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. View Course By Id");
            System.out.println("5. View All Courses");
            System.out.println("6. Back");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addCourse(sc);
                    break;

                case 2:
                	 updateCourse(sc);
                    break;

                case 3:
                	 deleteCourse(sc);
                    break;

                case 4:
                	viewCourseById(sc);
                    break;

                case 5:
                	viewAllCourse();
                    break;

                case 6:
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
            
		}catch(ValidationException e) {
			
			 System.out.println("\n=================================");
			 System.out.println(" VALIDATION FAILED ");
			 System.out.println("=================================");
			 System.err.println(e.getMessage());
			    
			
		} catch (Exception e) {

		    System.out.println("\n System Error occurred. Please contact admin.");
		}
    }
	
	
	

	private static void addCourse(Scanner sc) {

		System.out.println("Enter Course Code : ");
		String courseCode = sc.next();

		System.out.println("Enter Course Name: ");
		String courseName = sc.next();

		System.out.println("Enter Course Duration : ");
		int courseDuration = sc.nextInt();

		System.out.println("Enter Course Fees: ");
		double courseFees = sc.nextDouble();

		
		   Course course = Course.builder()
					            .courseCode(courseCode)
					            .courseName(courseName)
					            .duration(courseDuration)
					            .fees(courseFees)
					            .build();

		   	courseService.addCourse(course);
		    

		System.err.println("Course Added Succesfully");

	}
	
	
	
	

	private static void updateCourse(Scanner sc) {

	    System.out.print("Enter Course Id: ");
	    int id = sc.nextInt();

	    System.out.println("\n===== UPDATE COURSE MENU =====");
	    System.out.println("1. Update Course Code");
	    System.out.println("2. Update Course Name");
	    System.out.println("3. Update Duration");
	    System.out.println("4. Update Fees");
	    System.out.println("5. Update All Details");

	    System.out.print("Enter Choice: ");
	    int choice = sc.nextInt();

	    Course course = Course.builder()
	            .courseId(id)
	            .build();

	    switch(choice) {

	    case 1:
	        System.out.print("Enter New Course Code: ");
	        course.setCourseCode(sc.next());
	        break;

	    case 2:
	        System.out.print("Enter New Course Name: ");
	        course.setCourseName(sc.next());
	        break;

	    case 3:
	        System.out.print("Enter New Duration: ");
	        course.setDuration(sc.nextInt());
	        break;

	    case 4:
	        System.out.print("Enter New Fees: ");
	        course.setFees(sc.nextDouble());
	        break;

	    case 5:

	        System.out.print("Enter New Course Code: ");
	        course.setCourseCode(sc.next());

	        System.out.print("Enter New Course Name: ");
	        course.setCourseName(sc.next());

	        System.out.print("Enter New Duration: ");
	        course.setDuration(sc.nextInt());

	        System.out.print("Enter New Fees: ");
	        course.setFees(sc.nextDouble());

	        break;

	    default:
	        System.out.println("Invalid Choice");
	        return;
	    }

	    courseService.updateCourse(course);

	    System.out.println("Course Updated Successfully");
	}
	
	
	
	
	private static void deleteCourse(Scanner sc)
	{
		System.out.println("Enter Course Id: ");
		int id=sc.nextInt();
		
		System.out.print("Are you sure you want to delete this Course? (yes/no): ");
		String confirm=sc.next();
		
		
		if(confirm.equalsIgnoreCase("yes"))
		{
			courseService.deleteCourse(id);
			System.err.println("Course Deleted Successfully..");
			
		}else {
			System.err.println("Delete Opertion Cancelled..");
		}
	
	
	}
	
	
	
	private static void viewCourseById(Scanner sc)
	{
		System.out.println("Enter Course Id: ");
		int id=sc.nextInt();
		
		Course course=courseService.getCourseById(id);
		
		if(course!=null)
		{
			System.out.println("\n Course Details");
			System.out.println("ID   : "+course.getCourseId());
			System.out.println("Course Code  : "+course.getCourseCode());
			System.out.println("Course Name   : "+course.getCourseName());
			System.out.println("Duration   : "+course.getDuration());
			System.out.println("Fees   : "+course.getFees());
			
		}else {
			System.err.println("Course Not Found...");
		}
	}
	
	
	
	
	
	
	
	private static void viewAllCourse()
	{
		List<Course> courses = courseService.getAllCourses();
		
		if(courses.isEmpty())
		{
			System.out.println("No Course Found..");
			return;
		}
		
		
		
		System.out.println("\n===== Course LIST =====");
		
		for(Course course:courses)
		{
			System.out.println("------------------------");
			System.out.println("ID    : "+course.getCourseId());
			System.out.println("Course Code   : "+course.getCourseCode());
			System.out.println("Course Name   : "+course.getCourseName());
			System.out.println("Duration    : "+course.getDuration());
			System.out.println("Fees    : "+course.getFees());
		}
		
		
	}
	
	
	

}
