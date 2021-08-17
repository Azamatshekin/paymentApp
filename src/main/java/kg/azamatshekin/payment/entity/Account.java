package kg.azamatshekin.payment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_account")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account extends BaseEntity {

    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer=10, fraction=2)
    @Column(name = "c_balance", nullable = false)
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "c_user_id")
    private User user;


}
