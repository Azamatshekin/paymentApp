package kg.azamatshekin.payment.dao;

import kg.azamatshekin.payment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
