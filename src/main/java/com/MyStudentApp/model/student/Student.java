package com.MyStudentApp.model.student;

//import java.util.*;
//Time to use: LocalDate and period
import com.MyStudentApp.model.security.ConfigLoader;

import java.time.*;
import java.lang.*;

public class Student {


    //Java variable naming convention: camelCase
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String studentID;
    private DegreeProgram degreeProgram;

    //Student Constructor
    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.birthDate = null;
        this.phoneNumber = "";
        this.studentID = "";
        this.email = "";
        this.degreeProgram = null;
    }

    //Generate StudentID


    public String GenerateStudentID(int IDCount) {

        //Fuck you I want to declare the studentID then use it so i can remember!!
        ConfigLoader configLoader = new ConfigLoader("config.properties");
        String CollegeInitials = configLoader.getPropertyString("college.initials");

        return CollegeInitials + "_" +  LocalDate.now().getYear() + "_" +  IDCount;
    }

    //Getters and Setters

    //firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    //lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    //birthDate nad Age
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if(birthDate.isBefore(LocalDate.of(1950, 1, 1)))
        {
            throw new IllegalArgumentException("Birthdate cannot be before 1950!");
        }
        this.birthDate = birthDate;
    }

    public int getStudentAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    //email
    public String getEmail() {
        return email;
    }

    public String GenerateEmail()  {
        return this.firstName.charAt(0) + "." + this.lastName  + "@bluedot.org";
    }

    public void setEmail() {
        this.email = GenerateEmail();
    }


    //phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //studentID
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(int IDCount) {
        this.studentID = GenerateStudentID(IDCount);
    }

    //degreeProgram
    public DegreeProgram getDegreeProgram() {
        return degreeProgram;
    }
    public void setDegreeProgram(DegreeProgram degreeProgram) {
        this.degreeProgram = degreeProgram;
    }

    //toString
    @Override
    public String toString() {
        String StudentInformation = "";
        StudentInformation = "First Name: " + this.firstName + "\n" +
                "Last Name: " + this.lastName + "\n" +
                "Birth Date: " + this.birthDate + "\n" +
                "Email: " + this.email + "\n" +
                "Phone Number: " + this.phoneNumber + "\n" +
                "Student ID: " + this.studentID + "\n" +
                "Degree Program: " + this.degreeProgram + "\n";
        return StudentInformation;
    }




}
