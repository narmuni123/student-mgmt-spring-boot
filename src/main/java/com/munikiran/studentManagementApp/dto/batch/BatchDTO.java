package com.munikiran.studentManagementApp.dto.batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {

    private int batchId;

    private String batchName;

    private int courseId;

    private LocalDate startDate;
}
