package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CartItemService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateCartItemRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateCartItemRequest;
import kodlama.io.ecommerce.entities.concretes.CartItem;
import kodlama.io.ecommerce.repository.abstracts.CartItemRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemManager implements CartItemService {

    private final CartItemRepository repository;
    private final ModelMapper mapper;
    @Override
    public CartItem add(CreateCartItemRequest request) {
        CartItem cartItem = mapper.map(request, CartItem.class);
        cartItem.setCart(request.getCart());
        cartItem.setProduct(request.getProduct());
        return repository.save(cartItem);
    }

    public CartItem update(int id, UpdateCartItemRequest request){
        var cartItem = mapper.map(request, CartItem.class);
        cartItem.setId(id);

        repository.save(cartItem);

        return cartItem;
    }

    @Override
    public CartItem getById(int id) {
        CartItem cartItem = repository.findById(id).orElseThrow();
        return cartItem;
    }

//    public void delete(int cartItemId) {
//        CartItem cartItem = repository.findById(cartItemId).orElseThrow();
//        Cart cart = cartItem.getCart();
//        List<CartItem> cartItems = cart.getCartItems();
//        cartItems.remove(cartItem);
//
//        double totalPrice = cartItems.stream()
//                .mapToDouble(item -> item.getPrice() * item.getQuantity())
//                .sum();
//        cart.setTotalPrice(totalPrice);
//
//        cartService.calculateTotalPrice(cart.getId(),totalPrice);
//    }
}
