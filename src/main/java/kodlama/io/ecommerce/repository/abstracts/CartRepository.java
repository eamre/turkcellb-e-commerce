package kodlama.io.ecommerce.repository.abstracts;

import kodlama.io.ecommerce.entities.concretes.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
