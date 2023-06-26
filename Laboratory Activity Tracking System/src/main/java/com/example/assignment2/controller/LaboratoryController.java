package com.example.assignment2.controller;

import com.example.assignment2.model.Laboratory;
import com.example.assignment2.model.dto.LaboratoryDTO;
import com.example.assignment2.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class LaboratoryController {

    @Autowired
    private final LaboratoryService laboratoryService;

    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    //C - create operation
    @PostMapping("/laboratory/createLaboratory")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Laboratory createLaboratory(@RequestBody LaboratoryDTO laboratory) {
        return laboratoryService.createLaboratory(laboratory);
    }

    //R - read operation
    @GetMapping("/laboratory/readLaboratory")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<Laboratory> readLaboratory() {
        return laboratoryService.readLaboratory();
    }

    //U - update operation
    @PutMapping("/laboratory/updateLaboratory/{laboratoryId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Laboratory updateLaboratory(@RequestBody LaboratoryDTO laboratory, @PathVariable("laboratoryId") Integer laboratoryId) {
        return laboratoryService.updateLaboratory(laboratory, laboratoryId);
    }

    //D - delete operation
    @DeleteMapping("/laboratory/deleteLaboratory/{laboratoryId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String deleteLaboratory(@PathVariable("laboratoryId") Integer laboratoryId) {
        return laboratoryService.deleteLaboratory(laboratoryId);
    }

}
