package kodlama.io.ecommerce.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CartItemResponse {
    private int id;
    private String productName;
    private int quantity;
    private double price;
}
