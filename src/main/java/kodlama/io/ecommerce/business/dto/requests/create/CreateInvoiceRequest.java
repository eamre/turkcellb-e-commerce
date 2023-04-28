package kodlama.io.ecommerce.business.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateInvoiceRequest {
    private String cardHolder;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;
    private LocalDateTime saleDate;
}
