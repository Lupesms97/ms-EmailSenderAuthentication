package api.authenticaction.emailsender.controller;

import api.authenticaction.emailsender.dto.EmailDto;
import api.authenticaction.emailsender.dto.ResponseEmailDto;
import api.authenticaction.emailsender.model.EmailModel;
import api.authenticaction.emailsender.service.EmailService;
import api.authenticaction.emailsender.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ResponseEmailDto responseEmailDto = new ResponseEmailDto(emailDto.getUser().login(), emailDto.getEmailFrom(), emailDto.getText());
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);


        return new ResponseEntity<>(responseEmailDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getEmails")
    public ResponseEntity<?> getAll() {
        EmailDto emailDto = new EmailDto();
        List<EmailDto> emailDtos = new ArrayList<>();
        List<EmailModel> emailModels = emailService.getAllEmails();
        if(!emailModels.isEmpty()){
            for (EmailModel email : emailModels) {
                emailDtos.add(emailDto.convertForDto(email));
            }
            return ResponseEntity.ok(emailDtos);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum email encontrado.");
        }

    }
}
