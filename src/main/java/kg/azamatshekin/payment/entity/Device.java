package kg.azamatshekin.payment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "t_device")
@Inheritance(strategy = InheritanceType.JOINED)
public class Device extends BaseEntity {

    @Column(name = "c_device_name", nullable = true)
    private String deviceName;

    @Column(name = "c_token", nullable = true, unique = true)
    private String token;

    @Column(name = "c_date", nullable = true)
    private LocalDateTime date;

    @Column(name = "c_valid", nullable = true)
    private Boolean valid;

    @ManyToOne
    @JoinColumn(name = "c_user_id")
    private User user;


}
