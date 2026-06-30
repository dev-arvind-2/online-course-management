package com.client;

import java.util.Scanner;

import com.entity.User;
import com.util.UserAuthentication;
import menu.MenuService;


public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean systemRunning = true;

        while (systemRunning) {
        	
        	

//        	login handled by UserAuthentication util
            User user = UserAuthentication.login(sc);

            
            System.out.println();
            System.err.println("\nLogin Successful");  
            System.err.println("Welcome: " + user.getUsername());

//            Menu Handled By Menu Service
            MenuService.startMenu(user, sc);
        }

        
        
        sc.close();
    }
}