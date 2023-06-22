package kodlama.io.ecommerce.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdateCartItemRequest {
    private int quantity;
    private double price;
    private LocalDateTime saleDate;
}
