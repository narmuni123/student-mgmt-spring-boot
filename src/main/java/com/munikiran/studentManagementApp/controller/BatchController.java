package com.munikiran.studentManagementApp.controller;

import com.munikiran.studentManagementApp.dto.batch.BatchDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchSavedDTO;
import com.munikiran.studentManagementApp.dto.batch.BatchUpdatedDTO;
import com.munikiran.studentManagementApp.dto.course.CourseDTO;
import com.munikiran.studentManagementApp.dto.course.CourseUpdateDTO;
import com.munikiran.studentManagementApp.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@CrossOrigin
@RequestMapping("api/v1/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveBatch(@RequestBody BatchSavedDTO batchSavedDTO) {
        try{
            String btName = batchService.addBatch(batchSavedDTO);
            return ResponseEntity.ok("Batch " + btName + " added.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add batch: " + e.getMessage());
        }
    }

    @GetMapping(path = "/batches")
    public ResponseEntity<List<BatchDTO>> getAllCourses() {
        try {
            List<BatchDTO> batches = batchService.getAllBatches();
            return ResponseEntity.ok(batches);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BatchDTO> getBatchById(@PathVariable Integer id) {
        try {
            BatchDTO batch = batchService.getBatchById(id);
            if (batch != null) {
                return ResponseEntity.ok(batch);
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
    public ResponseEntity<String> updateBatch(@PathVariable Integer id , @RequestBody BatchUpdatedDTO batchUpdatedDTO) {
        try {
            String result = batchService.updateBatch(id, batchUpdatedDTO);
            if (result.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating batch.");
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteBatch(@PathVariable Integer id) {
        try {
            String result = batchService.deleteBatch(id);
            if (result.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting batch.");
        }
    }
}
