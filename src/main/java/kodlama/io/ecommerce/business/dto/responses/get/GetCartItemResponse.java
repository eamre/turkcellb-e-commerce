package kodlama.io.ecommerce.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetCartItemResponse {
    private int id;
    private int cartId;
    private int productId;
    private int quantity;
    private double price;

}
