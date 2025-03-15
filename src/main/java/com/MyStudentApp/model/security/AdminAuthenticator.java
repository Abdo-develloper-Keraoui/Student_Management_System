package com.MyStudentApp.model.security;
import com.MyStudentApp.model.admin.Admin;
import com.MyStudentApp.model.security.ConfigLoader;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Scanner;



import static com.MyStudentApp.model.security.SHA256WithSaltVerification.*;
import static com.MyStudentApp.model.security.ConfigLoader.*;

public class AdminAuthenticator {

    public AdminAuthenticator() {

    }

    public static boolean VerifyUserName(String EnteredUsername, String StoredUserName) {
        return EnteredUsername.equals(StoredUserName);
    }

    public static boolean authenticateAdmin(Admin admin) throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        // Load configuration
        ConfigLoader configLoader = new ConfigLoader("admin.properties");

        // Retrieve stored Username, salt and password hash and trial Times from config file
        String storedUserName = configLoader.getPropertyString("admin.username");
        String storedSalt = configLoader.getPropertyString("admin.hashed_salt");
        String storedHash = configLoader.getPropertyString("admin.hashed_password");

        Integer storedTrialTimes = configLoader.getPropertyInteger("admin.trials_Number");

        //Initialising the Authentication process
        System.out.println("Please Enter your user Name: ");
        String EnteredUserName = scanner.nextLine();

        String PasswordToVerify = "";

        //Prompting admin to enter password
        while(storedTrialTimes > 0) {

            if(!VerifyUserName(EnteredUserName, storedUserName)) {
                System.out.println("Wrong User Name! Please Try Again!");
                System.out.println("Please Enter your user Name: ");
                EnteredUserName = scanner.nextLine();
                continue;
            }
            System.out.println("Please Enter the password to verify: ");

            PasswordToVerify = scanner.nextLine();

            if(verifyPassword(PasswordToVerify, storedHash, storedSalt)){
                System.out.println("Password is correct");
                return true;
            } else {
                System.out.println("Password is incorrect");
                System.out.println("Please enter the Password Again!");
                storedTrialTimes--;
                System.out.println(storedTrialTimes + " trials Remaining");
            }
        }
        return false;
    }
}
