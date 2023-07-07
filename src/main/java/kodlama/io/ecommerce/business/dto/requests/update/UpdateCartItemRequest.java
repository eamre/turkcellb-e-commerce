package kodlama.io.ecommerce.business.dto.requests.update;

import kodlama.io.ecommerce.entities.concretes.Cart;
import kodlama.io.ecommerce.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdateCartItemRequest {
    private Cart cart;
    private Product product;
    private int quantity;
    private double price;
    private LocalDateTime saleDate;
}
