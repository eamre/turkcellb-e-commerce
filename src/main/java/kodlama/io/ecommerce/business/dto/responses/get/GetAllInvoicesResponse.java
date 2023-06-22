package kodlama.io.ecommerce.business.dto.responses.get;

import kodlama.io.ecommerce.common.dto.CartItemResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetAllInvoicesResponse {
    private int id;
    private String cardHolder;
//    private String productName;
//    private double price;
//    private int quantity;
    private double totalPrice;
    private LocalDateTime saleDate;
    private List<CartItemResponse> cartItems;
}
