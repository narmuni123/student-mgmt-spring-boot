package com.munikiran.studentManagementApp.service;

import com.munikiran.studentManagementApp.dto.batch.BatchDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchSavedDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchUpdatedDTO;
import com.munikiran.studentManagementApp.dto.course.CourseDTO;

import java.util.List;

public interface BatchService {
    String addBatch(BatchSavedDTO batchSavedDTO);

    List<BatchDTO> getAllBatches();

    BatchDTO getBatchById(Integer id);

    String updateBatch(Integer id, BatchUpdatedDTO batchUpdatedDTO);

    String deleteBatch(Integer id);
}
