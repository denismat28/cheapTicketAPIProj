package com.crazysusanin.planning.utils;

import com.crazysusanin.planning.model.AviaTicket;
import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.service.AviaTicketInfoService;
import com.crazysusanin.planning.service.AviaTicketService;
import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

@Component
public class GeneratePDF {
    @Autowired
    AviaTicketInfoService aviaTicketInfoService;

    @Autowired
    AviaTicketService aviaTicketService;

    public Document generatePDFByAviaTicket(int id) throws  IOException, FileNotFoundException {

        AviaTicket aviaTicket = aviaTicketService.findAviaTicketById(id);

        String outFileName = "D:\\generate_pdf\\test.pdf";

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outFileName));
        Document doc = new Document(pdfDoc);



        // Creating a table

        Table table = new Table(new float[]{50, 50, 50, 50, 50, 50})
                //line of heading
                .addCell(new Cell().add(new Paragraph("Price")))
                .addCell(new Cell().add(new Paragraph("Airline")))
                .addCell(new Cell().add(new Paragraph("Flight number")))
                .addCell(new Cell().add(new Paragraph("Departure")))
                .addCell(new Cell().add(new Paragraph("Return")))
                .addCell(new Cell().add(new Paragraph("Expires")))
        //line with info
                .addCell(new Cell().add(new Paragraph(String.valueOf(aviaTicket.getPrice()))))
                .addCell(new Cell().add(new Paragraph(aviaTicket.getAirline())))
                .addCell(new Cell().add(new Paragraph(String.valueOf(aviaTicket.getFlightNumber()))))
                .addCell(new Cell().add(new Paragraph(aviaTicket.getDepartDate())))
                .addCell(new Cell().add(new Paragraph(aviaTicket.getReturnDate())))
                .addCell(new Cell().add(new Paragraph(aviaTicket.getExpiresAt())));
        doc.add(table);
        doc.close();

return doc;
    }

    @Transactional(readOnly = true)
    public Document generatePDFByAviaTicketInfo(int id) throws  IOException, FileNotFoundException {

        AviaTicketInfo aviaTicketinfo = aviaTicketInfoService.findAviaTicketInfoById(id);

        String outFileName = "D:\\generate_pdf\\ticketInfoForPro"+id+".pdf";

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outFileName));
        Document doc = new Document(pdfDoc);

        // Creating a table

        Table table = new Table(new float[]{50, 50, 50, 50, 50, 50})
                //line of heading
                .addCell(new Cell().add(new Paragraph("Price")))
                .addCell(new Cell().add(new Paragraph("Airline")))
                .addCell(new Cell().add(new Paragraph("Flight number")))
                .addCell(new Cell().add(new Paragraph("Departure")))
                .addCell(new Cell().add(new Paragraph("Return")))
                .addCell(new Cell().add(new Paragraph("Expires")))
                //line with info
                .addCell(new Cell().add(new Paragraph(String.valueOf(aviaTicketinfo.getPrice()))))
                .addCell(new Cell().add(new Paragraph(aviaTicketinfo.getAirline())))
                .addCell(new Cell().add(new Paragraph(String.valueOf(aviaTicketinfo.getFlightNumber()))))
                .addCell(new Cell().add(new Paragraph(aviaTicketinfo.getDepartDate())))
                .addCell(new Cell().add(new Paragraph(aviaTicketinfo.getReturnDate())))
                .addCell(new Cell().add(new Paragraph(aviaTicketinfo.getExpiresAt())));
        doc.add(table);
        doc.close();

        return doc;
    }
}
