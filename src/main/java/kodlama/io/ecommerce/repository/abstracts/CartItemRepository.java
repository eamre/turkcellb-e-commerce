package kodlama.io.ecommerce.repository.abstracts;

import kodlama.io.ecommerce.entities.concretes.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

}
