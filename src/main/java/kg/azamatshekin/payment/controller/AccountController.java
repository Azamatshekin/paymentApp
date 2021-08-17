package kg.azamatshekin.payment.controller;


import kg.azamatshekin.payment.dto.PaymentDto;
import kg.azamatshekin.payment.service.AccountService;
import kg.azamatshekin.payment.utils.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(
            AccountService accountService
    ) {
        this.accountService = accountService;
    }

    @PostMapping("/payment")
    public void login(@RequestBody PaymentDto paymentDto)
            throws BusinessException {
        accountService.payment(paymentDto);
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