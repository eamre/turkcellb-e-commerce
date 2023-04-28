package kodlama.io.ecommerce.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdateProductRequest {
    private List<Integer> categoryIds;
    private String name;
    private int quantity;
    private double price;
    private boolean isActive;
    private String description;
}
