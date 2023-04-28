package kodlama.io.ecommerce.business.dto.requests.update;

import kodlama.io.ecommerce.business.dto.requests.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdatePaymentRequest extends PaymentRequest {
    private double balance;
}
