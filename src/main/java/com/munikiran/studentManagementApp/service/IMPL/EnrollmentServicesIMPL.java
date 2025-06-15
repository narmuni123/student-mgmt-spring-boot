package com.munikiran.studentManagementApp.service.IMPL;

import com.munikiran.studentManagementApp.dto.batch.BatchDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchSavedDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchUpdatedDTO;
import com.munikiran.studentManagementApp.dto.enrollment.EnrollmentDTO;
import com.munikiran.studentManagementApp.dto.enrollment.EnrollmentSavedDTO;
import com.munikiran.studentManagementApp.dto.enrollment.EnrollmentUpdatedDTO;
import com.munikiran.studentManagementApp.entity.Batch;
import com.munikiran.studentManagementApp.entity.Course;
import com.munikiran.studentManagementApp.entity.Enrollment;
import com.munikiran.studentManagementApp.entity.Student;
import com.munikiran.studentManagementApp.repository.BatchRepo;
import com.munikiran.studentManagementApp.repository.CourseRepo;
import com.munikiran.studentManagementApp.repository.EnrollmentRepo;
import com.munikiran.studentManagementApp.repository.StudentRepo;
import com.munikiran.studentManagementApp.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentServicesIMPL implements EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private CourseRepo courseRepo; // Assume you have this injected

    @Autowired
    private StudentRepo studentRepo; // Assume you have this injected

    @Autowired
    private BatchRepo batchRepo;

    @Override
    public String addEnrollment(EnrollmentSavedDTO enrollmentSavedDTO) {

        Batch batch =  batchRepo.findById(enrollmentSavedDTO.getBatchId()).orElse(null);
        Student student =  studentRepo.findById(enrollmentSavedDTO.getStudentId()).orElse(null);

        if(batch != null && student != null) {
            Enrollment enrollment = new Enrollment(
                    student,
                    batch,
                    enrollmentSavedDTO.getJoiningDate(),
                    enrollmentSavedDTO.getFee()
            );

            enrollmentRepo.save(enrollment);

            return "Student " + enrollment.getStudent().getStudentName() + " is enrolled to ";
        } else if(batch == null){
            throw new RuntimeException("Batch is not found");
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> getEnrollments = enrollmentRepo.findAll();
        List<EnrollmentDTO> enrollmentDTOList = new ArrayList<>();
        for(Enrollment enrollment : getEnrollments) {
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO(
                    enrollment.getEnrollmentId(),
                    enrollment.getStudent().getStudentId(),
                    enrollment.getBatch().getBatchId(),
                    enrollment.getJoiningDate(),
                    enrollment.getFee()
            );
            enrollmentDTOList.add(enrollmentDTO);
        }
        return enrollmentDTOList;
    }

    @Override
    public EnrollmentDTO getEnrollmentById(Integer id) {
        Enrollment enrollment =  enrollmentRepo.findById(id).orElse(null);

        if(enrollment != null) {
            return new EnrollmentDTO(
                    enrollment.getEnrollmentId(),
                    enrollment.getStudent().getStudentId(),
                    enrollment.getBatch().getBatchId(),
                    enrollment.getJoiningDate(),
                    enrollment.getFee()
            );
        } else {
            return null;
        }
    }

    @Override
    public String updateEnrollment(Integer id, EnrollmentUpdatedDTO enrollmentUpdatedDTO) {
        Batch batch =  batchRepo.findById(enrollmentUpdatedDTO.getBatchId()).orElse(null);
        Student student =  studentRepo.findById(enrollmentUpdatedDTO.getStudentId()).orElse(null);
        Enrollment enrollment = enrollmentRepo.findById(id).orElse(null);
        if(batch == null) {
            return "Batch with ID " + id + " not found.";
        } else if(student == null) {
            return "Student with ID " + id + " not found.";
        }
        if(enrollment != null) {
            enrollment.setBatch(batch);
            enrollment.setStudent(student);
            enrollment.setJoiningDate(enrollmentUpdatedDTO.getJoiningDate());
            enrollment.setFee(enrollmentUpdatedDTO.getFee());
            enrollmentRepo.save(enrollment);
            return "Enrollment updated successfully.";
        } else {
            return "Enrollment with ID " + id + " not found.";
        }
    }

    @Override
    public String deleteEnrollment(Integer id) {
        return enrollmentRepo.findById(id).map(enrollment -> {
            enrollmentRepo.deleteById(enrollment.getEnrollmentId());
            return "Enrollment deleted successfully.";
        }).orElse("Enrollment with ID " + id + " not found.");
    }
}
