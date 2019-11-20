package com.uday.git.studentsearch.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id                                                     // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long studentId;

    private String firstName;
    private String lastName;
    private String dob;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
