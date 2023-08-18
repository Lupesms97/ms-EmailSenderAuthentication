package api.authenticaction.emailsender.service;

import api.authenticaction.emailsender.enums.StatusEmail;
import api.authenticaction.emailsender.model.EmailModel;
import api.authenticaction.emailsender.repositories.EmailRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailModel sendEmail(EmailModel emailModel){
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailModel.getEmailFrom());
            helper.setTo(emailModel.getEmailTo());
            helper.setCc("noreply.institutodajlmam@gmail.com");
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText(),true); // true indica que é conteúdo HTML

            javaMailSender.send(message);

        }catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }finally {
            return emailRepository.save(emailModel);
        }

    }

    public List<EmailModel> getAllEmails(){
        return emailRepository.findAll();
    }
}
