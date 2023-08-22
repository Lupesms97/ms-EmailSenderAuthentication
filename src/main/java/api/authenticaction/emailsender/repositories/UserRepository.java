package api.authenticaction.emailsender.repositories;

import api.authenticaction.emailsender.model.EmailModel;
import api.authenticaction.emailsender.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    UserDetails findByLogin(String login);
    @Query("UPDATE users u SET u.emails = ?2 WHERE u.login = ?1")
    void saveEmail(String login, EmailModel newEmail);
}




