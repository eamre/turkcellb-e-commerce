package kodlama.io.ecommerce.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateSaleResponse {
    private int id;
    private int productId;
    private double price;
    private int quantity;
    private double totalPrice;
    private LocalDateTime saleDate;
}
