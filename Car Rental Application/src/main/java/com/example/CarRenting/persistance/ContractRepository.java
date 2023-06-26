package com.example.CarRenting.persistance;

import com.example.CarRenting.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer>, CrudRepository<Contract, Integer> {
}
