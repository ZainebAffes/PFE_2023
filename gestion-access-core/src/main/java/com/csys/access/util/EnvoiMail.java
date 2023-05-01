/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service("envoiMail")
public class EnvoiMail {

    private String mailHost = "smtppro.tunet.tn";
    private Properties props;
    private Session mailSession;
    String to = "nihel.turki@csys.com.tn,marwen.hadrich@csys.com.tn,dhouha.mnif@csys.com.tn,aymen.azem@clinisys.com.tn,sofien.samet@csys.com.tn";//change accordingly
    String from = "marwen.hadrich@csys.com.tn";//change accordingly
    private MimeMessage message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void sendEmail(String subject, String text) {
        //Creating a Session
        props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", mailHost);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
//        props.put("mail.smtp.user", );
//        props.put("password", );

        mailSession = Session.getDefaultInstance(props, null);
        try {
            message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message, "marwen.hadrich@csys.com.tn", "ad34shmk5");
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
