package com.util;



import java.util.Scanner;

import com.entity.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

public class UserAuthentication {

    public static User login(Scanner sc) {
    	
        UserService userService = new UserServiceImpl();

        User user = null;

        while (user == null) {

            System.out.println("\n===== LOGIN SYSTEM =====");

            System.out.print("Username: ");
            String username = sc.next();

            System.out.print("Password: ");
            String password = sc.next();

            user = userService.login(username, password);

            if (user == null) {
                System.err.println("Invalid Username or Password. Try again...\n");
            }
        }

        return user;
    }
}
