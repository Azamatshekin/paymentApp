package kg.azamatshekin.payment.dao;

import kg.azamatshekin.payment.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<AccountTransaction, Long> {
}
