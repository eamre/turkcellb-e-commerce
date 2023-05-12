package kodlama.io.ecommerce.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdateShippingRequest {

    private int saleId;

    private String shippingCode;

    private LocalDate shippingDate;

    private String fullName;

    private String address;
}
