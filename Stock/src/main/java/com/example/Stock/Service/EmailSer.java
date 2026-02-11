package com.example.Stock.Service;

import com.example.Stock.Model.Stock;
import com.example.Stock.Repositary.StockRepo;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Service
public class EmailSer {
    @Autowired
    StockRepo stockRepo;

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Value("${admin.email}")
    private String adminEmail;

    public void sendStockReport(File csvFile) throws Exception {

        try {
            System.out.println("EMAIL METHOD CALLED ✅");

            Email from = new Email("from mail", "Stock MS");
            Email to = new Email("to mail");
            String subject = "Stock Alert Report - From Stock MS System";
            Content content = new Content("text/html",
                    "<h3>Stock Report</h3>" +
                            "<p>Please find attached stock status report.</p>" +
                            "<p><b>System:</b> Stock MS</p>");
            Mail mail = new Mail(from, subject, to, content);

            byte[] data = Files.readAllBytes(csvFile.toPath());
            String encoded = Base64.getEncoder().encodeToString(data);

            Attachments attach = new Attachments();
            attach.setFilename("stock-report.csv");
            attach.setType("text/csv");
            attach.setDisposition("attachment");
            attach.setContent(encoded);
            mail.addAttachments(attach);

            SendGrid sg = new SendGrid(apiKey);
            Request req = new Request();
            req.setMethod(Method.POST);
            req.setEndpoint("mail/send");
            req.setBody(mail.build());

            // ✅ IMPORTANT: RESPONSE CAPTURE
            com.sendgrid.Response response = sg.api(req);

            System.out.println("EMAIL STATUS = " + response.getStatusCode());
            System.out.println("EMAIL BODY = " + response.getBody());
            System.out.println("EMAIL HEADERS = " + response.getHeaders());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
