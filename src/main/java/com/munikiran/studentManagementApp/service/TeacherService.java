package com.munikiran.studentManagementApp.service;

import com.munikiran.studentManagementApp.dto.teacher.TeacherDTO;
import com.munikiran.studentManagementApp.dto.teacher.TeacherUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {
    String addTeacher(TeacherDTO teacherDTO);

    List<TeacherDTO> getAllTeachers();

    TeacherDTO getTeacherById(Integer id);

    String updateTeacher(Integer id, TeacherUpdateDTO teacherUpdateDTO);

    String deleteTeacher(Integer id);
}
