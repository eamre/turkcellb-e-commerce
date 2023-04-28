package kodlama.io.ecommerce.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdateProductResponse {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private boolean isActive;
    private String description;
    private List<String> categoryNames;

}
