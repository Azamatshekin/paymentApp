package kg.azamatshekin.payment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "t_account_transaction")
@Inheritance(strategy = InheritanceType.JOINED)
public class AccountTransaction extends BaseEntity {

    @Column(name = "c_amount", nullable = false)
    private Double amount;

    @Column(name = "c_date", nullable = true)
    private LocalDateTime date;

    @Column(name = "c_is_debit", nullable = true)
    private Boolean is_debit;

    @OneToOne
    @JoinColumn(name = "c_account_id")
    private Account account;


}
