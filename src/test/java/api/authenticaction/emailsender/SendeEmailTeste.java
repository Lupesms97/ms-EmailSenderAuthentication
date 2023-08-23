package api.authenticaction.emailsender;

import api.authenticaction.emailsender.enums.StatusEmail;
import api.authenticaction.emailsender.model.EmailModel;
import api.authenticaction.emailsender.model.UserModel;
import api.authenticaction.emailsender.repositories.EmailRepository;
import api.authenticaction.emailsender.repositories.UserRepository;
import api.authenticaction.emailsender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SendeEmailTeste {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void testSendEmail() {
        String login = "testuser";
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailFrom("from@example.com");
        emailModel.setEmailTo("to@example.com");
        emailModel.setSubject("Test Subject");
        emailModel.setText("Test Body");

        UserModel user = new UserModel();
        user.setLogin(login);

        when(userRepository.findUserModelByLogin(login)).thenReturn(Optional.of(user));

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);


        doNothing().when(javaMailSender).send(any(MimeMessage.class));

        when(emailRepository.save(any(EmailModel.class))).thenReturn(emailModel);

        EmailModel result = emailService.sendEmail(login, emailModel);


        verify(emailRepository).save(emailModel);
        assertEquals(StatusEmail.SENT, result.getStatusEmail());
        assertEquals(user, result.getUser());
    }

    @Test
    public void testSendEmailFailure() throws MessagingException {

    }
}