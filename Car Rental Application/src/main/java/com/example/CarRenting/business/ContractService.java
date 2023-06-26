package com.example.CarRenting.business;

import com.example.CarRenting.model.Car;
import com.example.CarRenting.model.Contract;
import com.example.CarRenting.model.Rental;
import com.example.CarRenting.model.dto.CarDTO;
import com.example.CarRenting.model.dto.ContractDTO;
import com.example.CarRenting.model.dto.RentalDTO;
import com.example.CarRenting.persistance.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;
    private RentalService rentalService;

    public Contract toEntity(ContractDTO contract){
        Rental rental = rentalService.findById(contract.getRental_id());
        return Contract.builder()
                .contractId(contract.getContractId())
                .signature(contract.getSignature())
                .rental(rental)
                .build();
    }

    public ContractDTO fromEntity(Contract contract){
        return ContractDTO.builder()
                .contractId(contract.getContractId())
                .signature(contract.getSignature())
                .rental_id(contract.getRental().getRentalId())
                .build();
    }

    //C - create a contract
    public Contract createContract(ContractDTO contract) {
        Contract c = toEntity(contract);
        return contractRepository.save(c);
    }

    //U - update a contract
    public Contract updateContract(ContractDTO contract, Integer contractId) {
        Contract c = contractRepository.findById(contractId).get();
        ContractDTO contractDTO = fromEntity(c);

        if (Objects.nonNull(contract.getSignature()) && !"".equalsIgnoreCase(contract.getSignature())) {
            contractDTO.setSignature(contract.getSignature());
        }
        return contractRepository.save(toEntity(contractDTO));
    }
}
