package api.authenticaction.emailsender.dto;


public record ResponseEmailDto(String userDto,String text,String emailTo) {
    public ResponseEmailDto(EmailDto emailDto) {
        this(emailDto.getUser().login(),emailDto.getText(),emailDto.getEmailTo());
    }

}
