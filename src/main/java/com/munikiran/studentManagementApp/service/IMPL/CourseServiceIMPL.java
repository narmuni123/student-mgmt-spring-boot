package com.munikiran.studentManagementApp.service.IMPL;

import com.munikiran.studentManagementApp.dto.course.CourseDTO;
import com.munikiran.studentManagementApp.dto.course.CourseSavedDTO;
import com.munikiran.studentManagementApp.dto.course.CourseUpdateDTO;
import com.munikiran.studentManagementApp.entity.Course;
import com.munikiran.studentManagementApp.repository.CourseRepo;
import com.munikiran.studentManagementApp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceIMPL implements CourseService{
    @Autowired
    private CourseRepo courseRepo;

    @Override
    public String addCourse(CourseSavedDTO courseSavedDTO) {

        Course course = new Course(
                courseSavedDTO.getCourseName(),
                courseSavedDTO.getSyllabus(),
                courseSavedDTO.getDuration()
        );

        courseRepo.save(course);

        return course.getCourseName();
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> getCourses = courseRepo.findAll();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for(Course course : getCourses) {
            CourseDTO courseDTO = new CourseDTO(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getSyllabus(),
                    course.getDuration()
            );
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    @Override
    public CourseDTO geCourseById(Integer id) {
        Course course =  courseRepo.findById(id).orElse(null);

        if(course != null) {
            return new CourseDTO(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getSyllabus(),
                    course.getDuration()
            );
        } else {
            return null;
        }
    }

    @Override
    public String updateCourse(Integer id, CourseUpdateDTO courseUpdateDTO) {
        Course course = courseRepo.findById(id).orElse(null);

        if(course != null) {
            course.setCourseName(courseUpdateDTO.getCourseName());
            course.setSyllabus(courseUpdateDTO.getSyllabus());
            course.setDuration(courseUpdateDTO.getDuration());
            courseRepo.save(course);
            return "Course updated successfully.";
        } else {
            return "Course with ID " + id + " not found.";
        }
    }

    @Override
    public String deleteCourse(Integer id) {
        return courseRepo.findById(id).map(course -> {
            courseRepo.deleteById(course.getCourseId());
            return "Course deleted successfully.";
        }).orElse("Course with ID " + id + " not found.");
    }

}

