package com.munikiran.studentManagementApp.service.IMPL;
import com.munikiran.studentManagementApp.dto.teacher.TeacherDTO;
import com.munikiran.studentManagementApp.dto.teacher.TeacherUpdateDTO;
import com.munikiran.studentManagementApp.entity.Teacher;
import com.munikiran.studentManagementApp.repository.TeacherRepo;
import com.munikiran.studentManagementApp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceIMPL implements TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public String addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher(
                teacherDTO.getTeacherName(),
                teacherDTO.getAddress(),
                teacherDTO.getPhoneNumber()
        );

        teacherRepo.save(teacher);

        return teacher.getTeacherName();
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepo.findAll();
        List<TeacherDTO> teacherDTOSList = new ArrayList<>();

        for(Teacher teacher : teachers) {
            teacherDTOSList.add(
                    new TeacherDTO(
                            teacher.getTeacherId(),
                            teacher.getTeacherName(),
                            teacher.getAddress(),
                            teacher.getPhoneNumber()
                    )
            );
        }
        return  teacherDTOSList;
    }

    @Override
    public TeacherDTO getTeacherById(Integer id) {
        Teacher teacher = teacherRepo.findById(id).orElse(null);;

        if(teacher != null) {
           return new TeacherDTO(
                    teacher.getTeacherId(),
                    teacher.getTeacherName(),
                    teacher.getAddress(),
                    teacher.getPhoneNumber()
            );
        } else{
            return  null;
        }
    }

    @Override
    public String updateTeacher(Integer id, TeacherUpdateDTO teacherUpdateDTO) {
        Teacher teacher = teacherRepo.findById(id).orElse(null);

        if(teacher != null) {
            teacher.setTeacherName(teacherUpdateDTO.getTeacherName());
            teacher.setAddress(teacherUpdateDTO.getAddress());
            teacher.setPhoneNumber(teacherUpdateDTO.getPhoneNumber());
            teacherRepo.save(teacher);
            return "Teacher updated successfully.";
        } else {
            return "Teacher with ID " + id + " not found.";
        }
    }

    @Override
    public String deleteTeacher(Integer id) {
        return teacherRepo.findById(id).map(teacher -> {
            teacherRepo.deleteById(teacher.getTeacherId());
            return "Teacher deleted successfully.";
        }).orElse("Teacher with ID " + id + " not found.");
    }
}
