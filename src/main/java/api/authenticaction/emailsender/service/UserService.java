package api.authenticaction.emailsender.service;

import api.authenticaction.emailsender.dto.EmailDto;
import api.authenticaction.emailsender.dto.UserDto;
import api.authenticaction.emailsender.model.EmailModel;
import api.authenticaction.emailsender.model.UserModel;
import api.authenticaction.emailsender.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel update(EmailModel email){
        UserModel userModel = new UserModel();
        var user = userRepository.findByLogin(email.getUser().getLogin());
        if(user != null){
            BeanUtils.copyProperties(user, userModel);
            userRepository.saveEmail(String.valueOf(email.getUser().getLogin()), email);
        }
        return userModel;
    }
}
