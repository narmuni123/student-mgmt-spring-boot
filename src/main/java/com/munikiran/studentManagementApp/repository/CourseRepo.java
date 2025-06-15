package com.munikiran.studentManagementApp.repository;

import com.munikiran.studentManagementApp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}
