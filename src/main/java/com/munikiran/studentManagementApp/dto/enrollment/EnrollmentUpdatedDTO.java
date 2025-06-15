package com.munikiran.studentManagementApp.dto.enrollment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentUpdatedDTO {

    private  int enrollmentId;

    private int studentId;

    private int batchId;

    private LocalDate joiningDate;

    private double fee;
}
