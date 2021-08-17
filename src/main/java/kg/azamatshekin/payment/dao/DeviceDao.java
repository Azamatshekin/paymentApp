package kg.azamatshekin.payment.dao;

import kg.azamatshekin.payment.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDao extends JpaRepository<Device, Long> {
    Device findByToken(String token);
}
