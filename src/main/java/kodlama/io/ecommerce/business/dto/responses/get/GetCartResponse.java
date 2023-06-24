package kodlama.io.ecommerce.business.dto.responses.get;

import kodlama.io.ecommerce.common.dto.CartItemResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetCartResponse {
    private int id;
    private double totalPrice;
    private List<CartItemResponse> cartItems;
}
