package at.refugeescode.profiles.controller;

import at.refugeescode.profiles.logic.MailSender;
import at.refugeescode.profiles.logic.ProfileService;
import at.refugeescode.profiles.persistence.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import at.refugeescode.profiles.security.UserPrincipal;


import javax.mail.MessagingException;
import java.util.Optional;

@Controller
public class LikingController {

    ProfileService profileService;

    MailSender mailSender;

    public LikingController(ProfileService profileService, MailSender mailSender) {
        this.profileService = profileService;
        this.mailSender = mailSender;
    }


    @GetMapping("../{id}/like")
    String interested(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) throws MessagingException {
        Long companyid = principal.getCompany().getId();
        Long participantId = id;
        Optional<Profile> par = profileService.findOne(participantId);
        //log.info("Spring Mail - Sending Email with Inline Attachment Example");
        mailSender.sendEmail(principal,par);
        return "redirect:/profile";
    }
}
