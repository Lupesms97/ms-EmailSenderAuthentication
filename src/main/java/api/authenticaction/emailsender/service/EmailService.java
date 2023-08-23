package api.authenticaction.emailsender.service;

import api.authenticaction.emailsender.enums.StatusEmail;
import api.authenticaction.emailsender.exepctionsHandle.UserNotFoundException;
import api.authenticaction.emailsender.model.EmailModel;
import api.authenticaction.emailsender.model.UserModel;
import api.authenticaction.emailsender.repositories.EmailRepository;
import api.authenticaction.emailsender.repositories.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailModel sendEmail(String login,EmailModel emailModel){
        emailModel.setSendDateEmail(LocalDateTime.now());

        Optional<UserModel> userModel = userRepository.findUserModelByLogin(login);
        UserModel user = (userModel.isPresent())?userModel.get(): null;

        try{

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailModel.getEmailFrom());
            helper.setTo(emailModel.getEmailTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText(),true); // true indica que é conteúdo HTML

            javaMailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

            user.getEmails().add(emailModel);
            emailModel.setUser(user);
            userRepository.save(user);

        }catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }finally {

            return emailRepository.save(emailModel);
        }

    }


    public List<EmailModel> getEmailByLogin(String login) {
        Optional<UserModel> userModelOptional = userRepository.findUserModelByLogin(login);
        if (userModelOptional.isEmpty()) {

            throw new UserNotFoundException("Usuário não encontrado com o login: " + login);

        }else {
            UserModel user = userModelOptional.get();
            Optional<List<EmailModel>> emailOptional = emailRepository.findByUser_Login(user.getLogin());
            if (emailOptional.isEmpty()) {
                throw new UserNotFoundException("Email não encontrado com o login: " + login);
            }
            return emailOptional.get();

        }


    }
}
