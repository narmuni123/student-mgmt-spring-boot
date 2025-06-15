package com.munikiran.studentManagementApp.controller;


import com.munikiran.studentManagementApp.dto.course.CourseDTO;
import com.munikiran.studentManagementApp.dto.course.CourseSavedDTO;
import com.munikiran.studentManagementApp.dto.course.CourseUpdateDTO;
import com.munikiran.studentManagementApp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@CrossOrigin
@RequestMapping("api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveCourse(@RequestBody CourseSavedDTO courseSavedDTO) {
        try {
            String stName = courseService.addCourse(courseSavedDTO);
            return ResponseEntity.ok("Course " + stName + " added.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add course: " + e.getMessage());
        }
    }

    @GetMapping(path = "/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        try {
            List<CourseDTO> courses = courseService.getAllCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer id) {
        try {
            CourseDTO course = courseService.geCourseById(id);
            if (course != null) {
                return ResponseEntity.ok(course);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Integer id , @RequestBody CourseUpdateDTO courseUpdateDTO) {
        try {
            String result = courseService.updateCourse(id, courseUpdateDTO);
            if (result.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating student.");
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id) {
        try {
            String result = courseService.deleteCourse(id);
            if (result.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting student.");
        }
    }
}
