package kg.azamatshekin.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PaymentDto {
    private String token;
    private Double amount;
}
