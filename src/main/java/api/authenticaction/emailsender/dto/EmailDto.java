package api.authenticaction.emailsender.dto;


import api.authenticaction.emailsender.model.EmailModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    @NotBlank
    private String ownerRef;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;

    public EmailDto convertForDto(EmailModel emailModel){
        EmailDto emailDto = new EmailDto();
        BeanUtils.copyProperties(emailModel, emailDto);
        return emailDto;
    }

}
