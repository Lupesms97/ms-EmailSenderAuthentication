package api.authenticaction.emailsender.repositories;
import api.authenticaction.emailsender.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    UserDetails findByLogin(String login);

    Optional<UserModel> findUserModelByLogin(String email);



}




