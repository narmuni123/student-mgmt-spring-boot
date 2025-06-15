package com.munikiran.studentManagementApp.service;

import com.munikiran.studentManagementApp.dto.course.CourseDTO;
import com.munikiran.studentManagementApp.dto.course.CourseSavedDTO;
import com.munikiran.studentManagementApp.dto.course.CourseUpdateDTO;

import java.util.List;

public interface CourseService {
    String addCourse(CourseSavedDTO courseSavedDTO);

    List<CourseDTO> getAllCourses();

    CourseDTO geCourseById(Integer id);

    String updateCourse(Integer id, CourseUpdateDTO courseUpdateDTO);

    String deleteCourse(Integer id);
}
