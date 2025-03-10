package com.MyStudentApp.model.student;

public enum DegreeProgram {

    COMPUTER_SCIENCE("Computer Science"),
    SOFTWARE_ENGINEERING("Software Engineering"),
    BIOLOGY("Biology"),
    BUSINESS_ADMINISTRATION("Business Administration"),
    PHYSICS("Physics"),
    APPLIED_MATHEMATICS("Applied Mathematics"),
    ELECTRICAL_ENGINEERING("Electrical Engineering"),
    MECHANICAL_ENGINEERING("Mechanical Engineering"),
    CIVIL_ENGINEERING("Civil Engineering");


    private final String Description;

    DegreeProgram(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return Description; // Return the description instead of the enum name
    }


}