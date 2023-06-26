package com.example.CarRenting.business;

import com.example.CarRenting.model.Administrator;
import com.example.CarRenting.persistance.AdministratorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    //C - create operation
    public Administrator createAdministrator(Administrator administrator) {
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        return administratorRepository.save(administrator);
    }

    //R - read operation
    public List<Administrator> readAdministrator() {
        return administratorRepository.findAll();
    }

    //U - update operation
    public Administrator updateAdministrator(Administrator administrator, Integer administratorId) {
        Administrator a = administratorRepository.findById(administratorId).get();

        if (Objects.nonNull(administrator.getUsername()) && !"".equalsIgnoreCase(administrator.getUsername())) {
            a.setUsername(administrator.getUsername());
        }

        if (Objects.nonNull(administrator.getPassword()) && !"".equalsIgnoreCase(administrator.getPassword())) {
            a.setPassword(passwordEncoder.encode(administrator.getPassword()));
        }

        if (Objects.nonNull(administrator.getEmail()) && !"".equalsIgnoreCase(administrator.getEmail())) {
            a.setEmail(administrator.getEmail());
        }

        if (Objects.nonNull(administrator.getFullName()) && !"".equalsIgnoreCase(administrator.getFullName())) {
            a.setFullName(administrator.getFullName());
        }

        return administratorRepository.save(a);
    }

    //D - delete operation
    public String deleteAdministrator(Integer administratorId) {
        administratorRepository.deleteById(administratorId);
        return "Administrator deleted successfully.";
    }

    public Administrator findById(int id) {
        var val = administratorRepository.findById(id);
        if (val.isPresent()) {
            Administrator a = val.get();
            return a;
        } else return null;
    }

}
