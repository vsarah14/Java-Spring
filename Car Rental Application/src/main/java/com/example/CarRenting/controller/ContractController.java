package com.example.CarRenting.controller;

import com.example.CarRenting.business.ContractService;
import com.example.CarRenting.model.Contract;
import com.example.CarRenting.model.dto.ContractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
public class ContractController {
    @Autowired
    private ContractService contractService;

    //C - create operation
    @PostMapping("/contract/createContract")
    @PreAuthorize("hasAuthority('CLIENT')")
    public Contract createContract(@RequestBody ContractDTO contract) {
        return contractService.createContract(contract);
    }

    //U - update operation
    @PutMapping("/contract/updateContract/{contractId}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public Contract updateContract(@RequestBody ContractDTO contract, @PathVariable("contractId") Integer contractId) {
        return contractService.updateContract(contract, contractId);
    }
}
