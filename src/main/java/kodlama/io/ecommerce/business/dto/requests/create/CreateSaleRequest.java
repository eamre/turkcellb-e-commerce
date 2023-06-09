package kodlama.io.ecommerce.business.dto.requests.create;

import kodlama.io.ecommerce.business.dto.requests.PaymentRequest;
import kodlama.io.ecommerce.common.dto.ProductSaleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateSaleRequest {
    private List<ProductSaleRequest> productSales;
    private CreateShippingRequest shippingRequest;
    private PaymentRequest paymentRequest;
}
