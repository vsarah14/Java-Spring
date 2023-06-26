package com.example.assignment2.repository;

import com.example.assignment2.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Integer>, CrudRepository<Laboratory, Integer> {
}
