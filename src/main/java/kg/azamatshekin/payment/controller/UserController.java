package kg.azamatshekin.payment.controller;

import kg.azamatshekin.payment.dto.LoginDto;
import kg.azamatshekin.payment.dto.LoginResponseDto;
import kg.azamatshekin.payment.dto.LogoutDto;
import kg.azamatshekin.payment.service.DeviceService;
import kg.azamatshekin.payment.service.UserService;
import kg.azamatshekin.payment.utils.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;
    private final DeviceService deviceService;

    public UserController(
            UserService userService,
            DeviceService deviceService
    ) {
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDto loginDto)
            throws BusinessException {
        return userService.login(loginDto);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutDto logoutDto) {
        deviceService.logout(logoutDto.getToken());

    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            BusinessException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
