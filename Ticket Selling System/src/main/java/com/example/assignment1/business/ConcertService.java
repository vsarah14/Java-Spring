package com.example.assignment1.business;

import com.example.assignment1.model.Concert;
import com.example.assignment1.persistance.ConcertRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Service
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Slf4j
public class ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    //C - create operation
    public Concert createConcert(Concert concert){

        return concertRepository.save(concert);
    }

    //R - read operation
    public List<Concert> readConcert(){
        return (List<Concert>) concertRepository.findAll();
    }

    //U - update operation
    public Concert updateConcert(Concert concert, Integer concertId){

        Concert c = concertRepository.findById(concertId).get();

        if(Objects.nonNull(concert.getName()) && !"".equalsIgnoreCase( concert.getName())){
            c.setName(concert.getName());
        }


        if(Objects.nonNull(concert.getTickets()) && !"".equalsIgnoreCase(Integer.toString(concert.getTickets()))){
            if(concert.getTickets() >=0 ) {
                c.setTickets(concert.getTickets());
            }
            else{
                log.error("Invalid number of tickets.");
                return null;
            }
        }

        if(Objects.nonNull(concert.getDate()) && !"".equalsIgnoreCase(concert.getDate())){
            c.setDate(concert.getDate());
        }

        if(Objects.nonNull(concert.getDate()) && !"".equalsIgnoreCase(concert.getDate())){
            c.setDate(concert.getDate());
        }

        if(Objects.nonNull(concert.getBands()) && !"".equalsIgnoreCase(concert.getBands().toString())){
            c.setBands(concert.getBands());
        }

        return concertRepository.save(c);
    }

    //D - delete operation
    public void deleteConcert(Integer concertId){
        concertRepository.deleteById(concertId);
    }

    //find concert by id
    public Concert findConcert(Integer concertId){
        return concertRepository.findById(concertId).get();
    }
}
