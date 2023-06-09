package kodlama.io.ecommerce.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateProductResponse {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private boolean isActive;
    private String description;
    private List<Integer> categoryIds;
}
