package kodlama.io.ecommerce.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetSaleResponse {
    private int id;
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice; //readonly
    private LocalDateTime saleDate;

}
