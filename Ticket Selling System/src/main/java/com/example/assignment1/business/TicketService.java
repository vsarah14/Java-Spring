package com.example.assignment1.business;

import com.example.assignment1.model.Concert;
import com.example.assignment1.model.Ticket;
import com.example.assignment1.persistance.ConcertRepository;
import com.example.assignment1.persistance.TicketRepositoryCSV;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Slf4j
public class TicketService {

    @Autowired
    private TicketRepositoryCSV ticketRepository;

    @Autowired
    private ConcertRepository concertRepository;

    //C - create operation
    public Ticket createTicket(Ticket ticket) {

        int concertId = ticket.getConcertId();
        Optional<Concert> c = concertRepository.findById(concertId);
        int noTickets = c.get().getTickets() - ticket.getPersonNumber();
        if(noTickets >= 0){
            c.get().setTickets(noTickets);
            concertRepository.save(c.get());
            return ticketRepository.save(ticket);
        }
        else{
            log.error("No more tickets");
            return null;
        }
    }

    //R - read operation
    public List<Ticket> readTickets() {
        return ticketRepository.findAll();
    }

    //U - update operation
    public Ticket updateTicket(Ticket ticket, Integer ticketId) {
        Ticket t = ticketRepository.findById(ticketId).get();

        if (Objects.nonNull(ticket.getConcertId()) && !"".equalsIgnoreCase(Integer.toString(ticket.getConcertId()))) {
            t.setConcertId(ticket.getConcertId());
        }

        if (Objects.nonNull(ticket.getPersonNumber()) && !"".equalsIgnoreCase(Integer.toString(ticket.getPersonNumber()))) {
            t.setPersonNumber(ticket.getPersonNumber());
        }

        if (Objects.nonNull(ticket.getPrice()) && !"".equalsIgnoreCase(Double.toString(ticket.getPrice()))) {
            t.setPrice(ticket.getPrice());
        }

        return ticketRepository.save(t);
    }

    //D - delete operation
    public void deleteTicket(Integer ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        int concertId = ticket.getConcertId();
        Concert c = concertRepository.findById(concertId).orElseThrow();
        int noTickets = c.getTickets() + ticket.getPersonNumber();
        c.setTickets(noTickets);
        ticketRepository.deleteById(ticketId);
    }
}
