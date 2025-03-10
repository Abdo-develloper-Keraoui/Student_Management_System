package com.MyStudentApp.model.admin;

//TODO: Add Admin Roles and CRUD Operations


import com.MyStudentApp.model.security.PasswordHasher;
import com.MyStudentApp.model.student.Student;
import com.MyStudentApp.model.student.DegreeProgram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static com.MyStudentApp.model.security.AdminAuthenticator.authenticateAdmin;

public class Admin {
    public static int StudentIDCount = 555209;
    private ArrayList<Student> students;
    //private String UniversityName; //= "University of Science and Engineering";


    public Admin() {
        this.students = new ArrayList<>();
        StudentIDCount++;
    }

    //methods && functions

    //Functions, Methods, Getters, Setters
    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getStudentsList() {
        return students;
    }

    public Student getStudentByID(String studentID) {
        for(Student student: students) {
            if(student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }


    public Student createStudent() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();

        //TODO: Add input validation
        //TODO: Verify the phone number to be in the moroccan format (06 or 07 or 05 and to be written as required


        System.out.println("Enter Student first name: ");
        student.setFirstName(scanner.nextLine());

        System.out.println("Enter Student last name: ");
        student.setLastName(scanner.nextLine());

        System.out.println("Enter Student birth date (YYYY-MM-DD): ");
        student.setBirthDate(LocalDate.parse(scanner.nextLine()));

        System.out.println("Enter Student phone number: ");
        student.setPhoneNumber(scanner.nextLine());

        System.out.println("Enter the student Degree Program");
        student.setDegreeProgram(DegreeProgram.valueOf(scanner.nextLine().toUpperCase()));

        //adding the generated studentID and email

        student.setStudentID(StudentIDCount, "FSTS");
        student.setEmail();

        return student;
    }


    public void readStudent(String studentID) {
        System.out.println(getStudentByID(studentID));
    }

    public void UpdateStudent(String studentID)
    {
        //TODO: add switch case to determine which info to be updated

        //updating the student Phone number as test
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student new phone number: ");
        getStudentByID(studentID).setPhoneNumber(scanner.nextLine());

    }

    public void deleteStudent(String studentID) {

        students.remove(getStudentByID(studentID));
    }

}
