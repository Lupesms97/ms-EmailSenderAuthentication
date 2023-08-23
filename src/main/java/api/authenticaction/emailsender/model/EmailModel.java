package api.authenticaction.emailsender.model;

import api.authenticaction.emailsender.dto.EmailDto;
import api.authenticaction.emailsender.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_EMAIL")
public class EmailModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email_id")
    private UUID Emailid;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserModel user;

//    @JoinTable(name = "user_email_id",
//            joinColumns = @JoinColumn(name = "email_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private UserModel user;


}
