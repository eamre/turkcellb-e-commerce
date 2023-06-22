package kodlama.io.ecommerce.business.dto.requests.create;

import kodlama.io.ecommerce.entities.concretes.Cart;
import kodlama.io.ecommerce.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateCartItemRequest {
    private Cart cart;
    private Product product;
    private int quantity;
    private double price;
}
