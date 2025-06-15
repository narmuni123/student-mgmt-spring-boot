package com.munikiran.studentManagementApp.repository;

import com.munikiran.studentManagementApp.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepo extends JpaRepository<Batch, Integer> {
}
