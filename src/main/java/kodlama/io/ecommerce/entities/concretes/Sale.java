package kodlama.io.ecommerce.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity

@Table(name="sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double totalPrice; //readonly
    private LocalDateTime saleDate;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
//    @ManyToMany
//    @JoinTable(name = "product_sale",
//            joinColumns = @JoinColumn(name = "sale_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Set<Product> products;
}
