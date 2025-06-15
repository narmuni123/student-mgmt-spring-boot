package com.munikiran.studentManagementApp.controller;

import com.munikiran.studentManagementApp.dto.teacher.TeacherDTO;
import com.munikiran.studentManagementApp.dto.teacher.TeacherUpdateDTO;
import com.munikiran.studentManagementApp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@CrossOrigin
@RequestMapping("api/v1/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        try {
            String teacherName = teacherService.addTeacher(teacherDTO);
            return ResponseEntity.ok("Teacher" + teacherName + " added.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add teacher: " + e.getMessage());
        }
    }

    @GetMapping(path = "/teachers")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        try{
            List<TeacherDTO> teacherDTOS = teacherService.getAllTeachers();
            return ResponseEntity.ok(teacherDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Integer id) {
        try{
            TeacherDTO teacherDTO = teacherService.getTeacherById(id);
            if(teacherDTO != null) {
                return ResponseEntity.ok(teacherDTO);
            } else{
                return ResponseEntity.ok(null);
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable Integer id, @RequestBody TeacherUpdateDTO teacherUpdateDTO) {
        try {
            String result = teacherService.updateTeacher(id, teacherUpdateDTO);
            if (result.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating teacher.");
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer id) {
        try {
            String result = teacherService.deleteTeacher(id);
            if (result.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting teacher.");
        }
    }


}
