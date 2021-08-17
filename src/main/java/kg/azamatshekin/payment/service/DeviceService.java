package kg.azamatshekin.payment.service;


import kg.azamatshekin.payment.dao.DeviceDao;
import kg.azamatshekin.payment.entity.Device;
import kg.azamatshekin.payment.entity.User;
import kg.azamatshekin.payment.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DeviceService {

    private final DeviceDao deviceDao;

    public DeviceService(
            DeviceDao deviceDao
    ) {
        this.deviceDao = deviceDao;
    }

    public Device create(User user) {

        Device device = new Device();
        device.setDate(LocalDateTime.now());
        device.setDeviceName("WEB");
        device.setToken(UUID.randomUUID().toString());
        device.setUser(user);
        device.setValid(true);

        deviceDao.save(device);

        return device;
    }

    public void logout(String token) {
        Device device = deviceDao.findByToken(token);
        if (null != device) {
            device.setValid(false);
            deviceDao.save(device);
        }
    }

    public User getCurrentUser(String token) throws BusinessException{
        Device device = deviceDao.findByToken(token);
        if (null == device) {
            throw new BusinessException("Session closed");
        }
        if (!device.getValid()){
            throw new BusinessException("Session expired");
        }
        if (device.getDate().isBefore(LocalDateTime.now().minusHours(8))){
            throw new BusinessException("Session expired");
        }
        return device.getUser();
    }

}
