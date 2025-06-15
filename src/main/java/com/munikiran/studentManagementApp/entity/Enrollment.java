package com.munikiran.studentManagementApp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "enrollment_id", length = 12)
    private int enrollmentId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "batch_id", referencedColumnName = "batch_id", nullable = false)
    private Batch batch;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "fee")
    private double fee;


    public Enrollment() {
    }

    public Enrollment(Student student, Batch batch, LocalDate joiningDate, double fee) {
        this.student = student;
        this.batch = batch;
        this.joiningDate = joiningDate;
        this.fee = fee;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", student=" + student +
                ", batch=" + batch +
                ", joiningDate=" + joiningDate +
                ", fee=" + fee +
                '}';
    }
}
