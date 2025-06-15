package com.munikiran.studentManagementApp.controller;

import com.munikiran.studentManagementApp.dto.batch.BatchDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchSavedDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchUpdatedDTO;
import com.munikiran.studentManagementApp.dto.enrollment.EnrollmentDTO;
import com.munikiran.studentManagementApp.dto.enrollment.EnrollmentSavedDTO;
import com.munikiran.studentManagementApp.dto.enrollment.EnrollmentUpdatedDTO;
import com.munikiran.studentManagementApp.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@CrossOrigin
@RequestMapping("api/v1/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveEnrollment(@RequestBody EnrollmentSavedDTO enrollmentSavedDTO) {
        try{
            String btName = enrollmentService.addEnrollment(enrollmentSavedDTO);
            return ResponseEntity.ok("Enrollment " + btName + " added.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add enrollment: " + e.getMessage());
        }
    }

    @GetMapping(path = "/enrollments")
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        try {
            List<EnrollmentDTO> enrollments = enrollmentService.getAllEnrollments();
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Integer id) {
        try {
            EnrollmentDTO enrollment = enrollmentService.getEnrollmentById(id);
            if (enrollment != null) {
                return ResponseEntity.ok(enrollment);
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
    public ResponseEntity<String> updateEnrollment(@PathVariable Integer id , @RequestBody EnrollmentUpdatedDTO enrollmentUpdatedDTO) {
        try {
            String result = enrollmentService.updateEnrollment(id, enrollmentUpdatedDTO);
            if (result.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating enrollment.");
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Integer id) {
        try {
            String result = enrollmentService.deleteEnrollment(id);
            if (result.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting enrollment.");
        }
    }
}
