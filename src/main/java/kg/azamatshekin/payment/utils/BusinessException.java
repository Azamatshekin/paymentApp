package kg.azamatshekin.payment.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusinessException extends ResponseStatusException {

    public BusinessException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}
