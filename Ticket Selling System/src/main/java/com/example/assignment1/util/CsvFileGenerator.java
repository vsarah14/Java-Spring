package com.example.assignment1.util;

import com.example.assignment1.model.Ticket;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;


@Component
public class CsvFileGenerator {

    public void writeTickets(List<Ticket> tickets, Writer writer) {

        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for(Ticket ticket: tickets){
                printer.printRecord("Ticket id: ",ticket.getId(), "Concert id: ", ticket.getConcertId(), "Concert price: ", ticket.getPrice(),"Number of persons: ", ticket.getPersonNumber());
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
