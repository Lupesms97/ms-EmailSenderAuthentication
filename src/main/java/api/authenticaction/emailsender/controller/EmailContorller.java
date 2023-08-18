package api.authenticaction.emailsender.controller;

import api.authenticaction.emailsender.dto.EmailDto;
import api.authenticaction.emailsender.model.EmailModel;
import api.authenticaction.emailsender.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("email")
public class EmailContorller {

    @Autowired
    EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getEmails")
    public List<EmailDto> getAll() {
        EmailDto emailDto = new EmailDto();
        List<EmailDto> emailDtos = new ArrayList<>();
        List<EmailModel> emailModels = emailService.getAllEmails();
        for (EmailModel email : emailModels) {
            emailDtos.add(emailDto.convertForDto(email));
        }
        return emailDtos;
    }
}
