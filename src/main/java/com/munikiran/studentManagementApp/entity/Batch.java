package com.munikiran.studentManagementApp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "batch_id", length = 11)
    private int batchId;

    @Column(name = "batch_name", length = 45)
    private String batchName;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @Column(name = "start_date")
    private LocalDate startDate;


    public Batch() {
    }

    public Batch(String batchName, Course course, LocalDate startDate) {
        this.batchName = batchName;
        this.course = course;
        this.startDate = startDate;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchId=" + batchId +
                ", batchName='" + batchName + '\'' +
                ", course=" + course +
                ", startDate=" + startDate +
                '}';
    }
}
