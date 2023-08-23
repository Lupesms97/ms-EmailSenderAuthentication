package api.authenticaction.emailsender.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ResponseEmailDto{
    private String text;
    private String emailTo ;
    private String user;

    public ResponseEmailDto(String user, String emailTo, String text) {
        this.user = user;
        this.emailTo = emailTo;
        this.text = text;
    }
}
