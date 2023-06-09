package kodlama.io.ecommerce.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cardHolder;
    private double totalPrice;
    private LocalDateTime saleDate;
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
