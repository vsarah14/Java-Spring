package com.example.assignment1.controller;

import com.example.assignment1.business.TicketService;
import com.example.assignment1.model.Ticket;
import com.example.assignment1.util.CsvFileGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@EnableMethodSecurity
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CsvFileGenerator csvFileGenerator;

    @GetMapping("/export-to-csv")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        csvFileGenerator.writeTickets(ticketService.readTickets(), response.getWriter());
    }


    //sell a ticket
    @PostMapping("/tickets/sell")
    @PreAuthorize("hasAuthority('CASHIER')")
    public Ticket sellTicket(@RequestBody Ticket ticket){
        return ticketService.createTicket(ticket);
    }

    //see all tickets
    @GetMapping("/tickets/seeTickets")
    @PreAuthorize("hasAuthority('CASHIER')")
    public List<Ticket> seeTickets(){
        return ticketService.readTickets();
    }

    //edit tickets
    @PutMapping("/tickets/editTickets/{id}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public Ticket editTicket(@RequestBody Ticket ticket, @PathVariable("id") Integer ticketId){
        return ticketService.updateTicket(ticket, ticketId);
    }

    //cancel reservation
    @DeleteMapping("/tickets/cancelReservation/{id}")
    @PreAuthorize("hasAuthority('CASHIER')")
    public String cancelReservation(@PathVariable("id") Integer ticketId){
        ticketService.deleteTicket(ticketId);
        return "Ticket deleted successfully.";
    }

}
