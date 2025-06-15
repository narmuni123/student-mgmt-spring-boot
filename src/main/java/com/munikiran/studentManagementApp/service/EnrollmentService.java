package com.munikiran.studentManagementApp.service;

import com.munikiran.studentManagementApp.dto.enrollment.EnrollmentDTO;
import com.munikiran.studentManagementApp.dto.enrollment.EnrollmentSavedDTO;
import com.munikiran.studentManagementApp.dto.enrollment.EnrollmentUpdatedDTO;

import java.util.List;

public interface EnrollmentService {
    String addEnrollment(EnrollmentSavedDTO enrollmentSavedDTO);

    List<EnrollmentDTO> getAllEnrollments();

    EnrollmentDTO getEnrollmentById(Integer id);

    String deleteEnrollment(Integer id);

    String updateEnrollment(Integer id, EnrollmentUpdatedDTO enrollmentUpdatedDTO);
}
