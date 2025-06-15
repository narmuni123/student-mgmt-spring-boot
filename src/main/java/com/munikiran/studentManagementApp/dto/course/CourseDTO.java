package com.munikiran.studentManagementApp.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private int courseId;

    private String courseName;

    private String syllabus;

    private String duration;
}
