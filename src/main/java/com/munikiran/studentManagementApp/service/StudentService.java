package com.munikiran.studentManagementApp.service;

import com.munikiran.studentManagementApp.dto.student.StudentDTO;
import com.munikiran.studentManagementApp.dto.student.StudentSavedDTO;
import com.munikiran.studentManagementApp.dto.student.StudentUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    String addStudent(StudentSavedDTO studentSavedDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Integer id);

    String updateStudent(Integer id, StudentUpdateDTO studentUpdatedDTO);

    String deleteStudent(Integer id);
}
