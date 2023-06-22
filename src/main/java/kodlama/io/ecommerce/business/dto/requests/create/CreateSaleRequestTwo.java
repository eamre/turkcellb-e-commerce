package kodlama.io.ecommerce.business.dto.requests.create;

import kodlama.io.ecommerce.business.dto.requests.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateSaleRequestTwo {
    private int cartId;
    private CreateShippingRequest shippingRequest;
    private PaymentRequest paymentRequest;
}
