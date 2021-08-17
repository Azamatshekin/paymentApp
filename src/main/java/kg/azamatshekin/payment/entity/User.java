package kg.azamatshekin.payment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    @Column(name = "c_username")
    private String username;

    @Column(name = "c_password")
    private String password;

    @Column(name = "c_is_blocked")
    private Boolean isBlocked;


}
