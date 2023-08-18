package api.authenticaction.emailsender.dto;

import api.authenticaction.emailsender.enums.UserRole;

public record RegisterDto(String login, String password, UserRole role){
}
