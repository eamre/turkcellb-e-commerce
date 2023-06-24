package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.requests.create.CreateCartRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateCartRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateCartResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllCartResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetCartResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateCartResponse;

import java.util.List;

public interface CartService {
    List<GetAllCartResponse> getAll();
    GetCartResponse getById(int id);
    CreateCartResponse add(CreateCartRequest request);
    UpdateCartResponse update(int id, UpdateCartRequest request);
    void delete(int id);

    void deleteCartItem(int cartItemId);
}
