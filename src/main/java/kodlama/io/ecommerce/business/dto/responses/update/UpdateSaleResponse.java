package kodlama.io.ecommerce.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdateSaleResponse{
    private int id;
    private int productId;
    private double price;
    private int quantity;
    private double totalPrice;
    private LocalDateTime saleDate;
}
