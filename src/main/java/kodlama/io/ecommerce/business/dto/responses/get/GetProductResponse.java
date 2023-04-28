package kodlama.io.ecommerce.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetProductResponse {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private boolean isActive;
    private String description;
    private List<String> categoryNames;

}
