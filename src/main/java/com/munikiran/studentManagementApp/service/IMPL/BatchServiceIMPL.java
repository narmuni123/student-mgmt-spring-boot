package com.munikiran.studentManagementApp.service.IMPL;


import com.munikiran.studentManagementApp.dto.batch.BatchDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchSavedDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchUpdatedDTO;
import com.munikiran.studentManagementApp.entity.Batch;
import com.munikiran.studentManagementApp.entity.Course;
import com.munikiran.studentManagementApp.repository.BatchRepo;
import com.munikiran.studentManagementApp.repository.CourseRepo;
import com.munikiran.studentManagementApp.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatchServiceIMPL implements BatchService {

    @Autowired
    private BatchRepo batchRepo;

    @Autowired
    private CourseRepo courseRepo; // Assume you have this injected


    @Override
    public String addBatch(BatchSavedDTO batchSavedDTO) {

        Course course =  courseRepo.findById(batchSavedDTO.getCourseId()).orElse(null);

        if(course != null) {
            Batch batch = new Batch(
                    batchSavedDTO.getBatchName(),
                    course,
                    batchSavedDTO.getStartDate()
            );

            batchRepo.save(batch);

            return batch.getBatchName();
        } else {
            throw new RuntimeException("Course with ID " + batchSavedDTO.getCourseId() + " not found.");
        }
    }

    @Override
    public List<BatchDTO> getAllBatches() {
        List<Batch> getBatches = batchRepo.findAll();
        List<BatchDTO> batchDTOList = new ArrayList<>();
        for(Batch batch : getBatches) {
            BatchDTO batchDTO = new BatchDTO(
                    batch.getBatchId(),
                    batch.getBatchName(),
                    batch.getCourse().getCourseId(),
                    batch.getStartDate()
            );
            batchDTOList.add(batchDTO);
        }
        return batchDTOList;
    }

    @Override
    public BatchDTO getBatchById(Integer id) {
        Batch batch =  batchRepo.findById(id).orElse(null);

        if(batch != null) {
            return new BatchDTO(
                    batch.getBatchId(),
                    batch.getBatchName(),
                    batch.getCourse().getCourseId(),
                    batch.getStartDate()
            );
        } else {
            return null;
        }
    }

    @Override
    public String updateBatch(Integer id, BatchUpdatedDTO batchUpdatedDTO) {
        Batch batch = batchRepo.findById(id).orElse(null);

        Course course =  courseRepo.findById(batchUpdatedDTO.getCourseId()).orElse(null);
        if(course == null) {
            return "Course with ID " + id + " not found.";
        }
        if(batch != null) {
            batch.setBatchName(batchUpdatedDTO.getBatchName());
            batch.setCourse(course);
            batch.setStartDate(batchUpdatedDTO.getStartDate());
            batchRepo.save(batch);
            return "Batch updated successfully.";
        } else {
            return "Batch with ID " + id + " not found.";
        }
    }

    @Override
    public String deleteBatch(Integer id) {
        return batchRepo.findById(id).map(batch -> {
            batchRepo.deleteById(batch.getBatchId());
            return "Batch deleted successfully.";
        }).orElse("Batch with ID " + id + " not found.");
    }
}
