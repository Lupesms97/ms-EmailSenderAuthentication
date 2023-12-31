package api.authenticaction.emailsender.repositories;

import api.authenticaction.emailsender.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

    Optional<List<EmailModel>> findByUser_Login(String login);
}
