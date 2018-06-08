package at.refugeescode.profiles.logic;

import at.refugeescode.profiles.persistence.model.Mail;
import at.refugeescode.profiles.persistence.model.Profile;
import com.sun.security.auth.UserPrincipal;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MailSender {

    private static Logger log = LoggerFactory.getLogger(Application.class);


    Mail mail;
    EmailService emailService;


    public MailSender(Mail mail, EmailService emailService) {
        this.mail = mail;
        this.emailService = emailService;
    }


    public void sendEmail(at.refugeescode.profiles.security.UserPrincipal principal, Optional<Profile> par) {
        try {
            mail.setFrom("no-reply@memorynotfound.com");
            mail.setTo("mohammad.sawas2016@gmail.com");
            mail.setSubject("Sending Email with Inline Attachment Example");
            mail.setContent(principal.getCompany().getName() + " is interested in " + par.get().getName());

            emailService.sendSimpleMessage(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // interests.interested(userId, participantId);
    }
}
