package com.munikiran.studentManagementApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_id", length = 11)
    private int teacherId;

    @Column(name = "teacher_name", length = 45)
    private String teacherName;

    @Column(name = "address", length = 60)
    private  String address;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    public Teacher() {

    }

    public Teacher(int teacherId, String teacherName, String address, String phoneNumber) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Teacher(String teacherName, String address, String phoneNumber) {
        this.teacherName = teacherName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
