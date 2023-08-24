package api.authenticaction.emailsender.controller;

import api.authenticaction.emailsender.dto.EmailDto;
import api.authenticaction.emailsender.dto.ResponseEmailDto;
import api.authenticaction.emailsender.model.EmailModel;
import api.authenticaction.emailsender.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/email")
public class EmailContorller {

    @Autowired
    EmailService emailService;



    @PostMapping("/sending-email")
    public ResponseEntity<ResponseEmailDto> sendEmail(@RequestBody @Valid EmailDto emailDto) {

        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailDto.getUser().login(),emailModel);

        ResponseEmailDto responseEmailDto = new ResponseEmailDto(emailDto.getUser().login(), emailDto.getEmailFrom(), emailDto.getText());

        return new ResponseEntity<>(responseEmailDto, HttpStatus.ACCEPTED);
    }
//    Apenas ADMIN
    @GetMapping("/getEmails/{login}")
    public ResponseEntity<List<ResponseEmailDto>> getEmailbyLogin(@PathVariable String login) {
        List<EmailModel> emailModel = emailService.getEmailByLogin(login);
        List<ResponseEmailDto> responseEmailDtos = new ArrayList<>();
        emailModel.forEach(email -> responseEmailDtos.add(new ResponseEmailDto(email.getUser().getLogin(), email.getEmailFrom(), email.getText())));

        return new ResponseEntity<>(responseEmailDtos, HttpStatus.OK);

    }

    @GetMapping("/getEmailSession")
    public ResponseEntity<List<ResponseEmailDto>> getemails(){
        List<EmailModel> emailModel = emailService.getListEmail();
        List<ResponseEmailDto> responseEmailDtos = new ArrayList<>();
        emailModel.forEach(email -> responseEmailDtos.add(new ResponseEmailDto(email.getUser().getLogin(), email.getEmailFrom(), email.getText())));

        return new ResponseEntity<>(responseEmailDtos, HttpStatus.OK);
    }
}
