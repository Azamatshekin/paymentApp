package kg.azamatshekin.payment.dao;

import kg.azamatshekin.payment.entity.Account;
import kg.azamatshekin.payment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, Long> {
    Account findByUser(User user);
}
