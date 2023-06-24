package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.requests.create.CreateCartItemRequest;
import kodlama.io.ecommerce.entities.concretes.CartItem;

public interface CartItemService {
    CartItem add(CreateCartItemRequest request);
    CartItem getById(int id);
}
