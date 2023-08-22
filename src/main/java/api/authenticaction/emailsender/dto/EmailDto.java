package api.authenticaction.emailsender.dto;


import api.authenticaction.emailsender.model.EmailModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    private UserDto user;

    public EmailDto convertForDto(EmailModel emailModel){
        EmailDto emailDto = new EmailDto();
        BeanUtils.copyProperties(emailModel, emailDto);
        return emailDto;
    }

}
