package kodlama.io.ecommerce.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductSaleRequest {
    private int productId;
    private double price;
    private int quantity;
}
