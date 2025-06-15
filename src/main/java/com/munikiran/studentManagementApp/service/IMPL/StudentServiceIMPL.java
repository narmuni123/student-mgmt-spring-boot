package com.munikiran.studentManagementApp.service.IMPL;

import com.munikiran.studentManagementApp.dto.student.StudentDTO;
import com.munikiran.studentManagementApp.dto.student.StudentSavedDTO;
import com.munikiran.studentManagementApp.dto.student.StudentUpdateDTO;
import com.munikiran.studentManagementApp.entity.Student;
import com.munikiran.studentManagementApp.repository.StudentRepo;
import com.munikiran.studentManagementApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceIMPL implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public String addStudent(StudentSavedDTO studentSavedDTO) {

        Student student = new Student(
                studentSavedDTO.getStudentName(),
                studentSavedDTO.getAddress(),
                studentSavedDTO.getPhoneNumber()
        );

        studentRepo.save(student);

        return student.getStudentName();
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> getStudents = studentRepo.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for(Student student : getStudents) {
            StudentDTO studentDTO = new StudentDTO(
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getAddress(),
                    student.getPhoneNumber()
            );
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }

    @Override
    public StudentDTO getStudentById(Integer id) {
        Student student =  studentRepo.findById(id).orElse(null);

        if(student != null) {
            return new StudentDTO(
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getAddress(),
                    student.getPhoneNumber()
            );
        } else {
            return null;
        }
    }

    @Override
    public String updateStudent(Integer id, StudentUpdateDTO studentUpdatedDTO) {
        Student student = studentRepo.findById(id).orElse(null);

        if(student != null) {
            student.setStudentName(studentUpdatedDTO.getStudentName());
            student.setAddress(studentUpdatedDTO.getAddress());
            student.setPhoneNumber(studentUpdatedDTO.getPhoneNumber());
            studentRepo.save(student);
            return "Student updated successfully.";
        } else {
            return "Student with ID " + id + " not found.";
        }
    }

    @Override
    public String deleteStudent(Integer id) {
        return studentRepo.findById(id).map(student -> {
            studentRepo.deleteById(student.getStudentId());
            return "Student deleted successfully.";
        }).orElse("Student with ID " + id + " not found.");
    }

}
