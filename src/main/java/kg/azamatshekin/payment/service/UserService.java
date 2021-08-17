package kg.azamatshekin.payment.service;


import kg.azamatshekin.payment.dao.DeviceDao;
import kg.azamatshekin.payment.dao.UserDao;
import kg.azamatshekin.payment.dto.LoginDto;
import kg.azamatshekin.payment.dto.LoginResponseDto;
import kg.azamatshekin.payment.entity.Device;
import kg.azamatshekin.payment.entity.User;
import kg.azamatshekin.payment.utils.BusinessException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class UserService {

    private final UserDao userDao;
    private final DeviceService deviceService;
    private final RedisService redisService;

    public UserService(
            UserDao userDao, DeviceService deviceService, RedisService redisService
    ) {
        this.userDao = userDao;
        this.deviceService = deviceService;
        this.redisService = redisService;
    }


    public LoginResponseDto login(LoginDto loginDto) throws BusinessException{

        redisService.checkBrute(loginDto.getUsername());

        User user = userDao.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (null == user){
            redisService.bruteTrial(loginDto.getUsername());
            throw new BusinessException("Invalid username or password! Please contact C tech center");
        }

        Device d  = deviceService.create(user);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUsername(user.getUsername());
        loginResponseDto.setToken(d.getToken());

        return loginResponseDto;
    }

}
