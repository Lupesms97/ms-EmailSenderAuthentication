package api.authenticaction.emailsender.exepctionsHandle;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
            super(message);

    }
}
