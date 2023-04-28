package kodlama.io.ecommerce.business.dto.requests.create;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateProductRequest {
    private List<Integer> categoryIds;
    @NotNull
    @Length(min = 1, max = 100)
    private String name;
    @Min(1)
    private int quantity;
    @DecimalMin("1.00")
    private double price;
    private boolean isActive;
    @Length(min = 1, max = 250)
    private String description;

}
