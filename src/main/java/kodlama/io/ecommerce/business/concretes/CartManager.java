package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CartItemService;
import kodlama.io.ecommerce.business.abstracts.CartService;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.abstracts.UserService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateCartItemRequest;
import kodlama.io.ecommerce.business.dto.requests.create.CreateCartRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateCartItemRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateCartRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateCartResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllCartResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetCartResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateCartResponse;
import kodlama.io.ecommerce.business.rules.CartBusinessRules;
import kodlama.io.ecommerce.entities.concretes.Cart;
import kodlama.io.ecommerce.entities.concretes.CartItem;
import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.entities.concretes.User;
import kodlama.io.ecommerce.repository.abstracts.CartRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartManager implements CartService {
    private final CartRepository repository;
    private final ModelMapper mapper;
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final CartBusinessRules rules;
    private final UserService userService;
    @Override
    public List<GetAllCartResponse> getAll() {
        List<Cart> carts = repository.findAll();
        List<GetAllCartResponse> responses = carts.stream()
                .map(cart -> mapper.map(cart, GetAllCartResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetCartResponse getById(int id) {
        Cart cart = repository.findById(id).orElseThrow();
        var response = mapper.map(cart, GetCartResponse.class);

        return response;
    }

    @Override
    public CreateCartResponse add(CreateCartRequest request) {
        Cart cart = repository.findById(request.getCartId()).orElseGet(() -> createNewCart());
        User user = mapper.map(userService.getById(request.getUserId()), User.class);
        Product product = mapper.map(productService.getById(request.getProductId()), Product.class);

        rules.checkIsProductActiveOrInStock(product.isActive(), product.getQuantity(), request.getQuantity());

        CartItem cartItem = updateOrCreateCartItem(cart, product, request);

        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getPrice())
                .sum();
        cart.setTotalPrice(totalPrice);

        cart.setUser(user);

        repository.save(cart);

        CreateCartResponse response = mapper.map(cartItem, CreateCartResponse.class);
        return response;
    }

    @Override
    public UpdateCartResponse update(int id, UpdateCartRequest request) {
        Cart cart = mapper.map(request, Cart.class);
        cart.setId(id);
        repository.save(cart);

        var response = mapper.map(cart, UpdateCartResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteCartItem(int cartItemId) {
        CartItem cartItem = cartItemService.getById(cartItemId);
        Cart cart = cartItem.getCart();
        cart.getCartItems().remove(cartItem);
        recalculateTotalPrice(cart);
        repository.save(cart);
    }

    private CartItem updateOrCreateCartItem(Cart cart, Product product, CreateCartRequest request){
        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId() == product.getId())
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(existingCartItem.getPrice() + (request.getPrice() * request.getQuantity()));

            var updateCartItem = mapper.map(existingCartItem, UpdateCartItemRequest.class);
            cartItemService.update(existingCartItem.getId(), updateCartItem);
            return existingCartItem;

        } else {
            CreateCartItemRequest cartItemRequest = new CreateCartItemRequest();
            cartItemRequest.setCart(cart);
            cartItemRequest.setProduct(product);
            cartItemRequest.setQuantity(request.getQuantity());
            cartItemRequest.setPrice(request.getPrice() * request.getQuantity());

            cartItemService.add(cartItemRequest);

            return mapper.map(cartItemRequest, CartItem.class);

        }
    }
    private void recalculateTotalPrice(Cart cart) {
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);
    }
    private Cart createNewCart() {
        Cart cart = new Cart();
        return repository.save(cart);
    }
}
