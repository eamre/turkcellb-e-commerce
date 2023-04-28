package kodlama.io.ecommerce.business.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {
//    @NotBlank(message = "Kart numarası boş bırakılamaz.")
//    @Length(min = 16, max = 16, message = "Kart numarası 16 haneden oluşmak zorunda.")
    private String cardNumber;
//    @NotBlank
//    @Length(min = 5)
    private String cardHolder;
//    @NotNull
//    @Min(value = 2023)
    private int cardExpirationYear;
//    @NotNull
//    @Max(value = 12)
//    @Min(value = 1)
    private int cardExpirationMonth;
//    @NotBlank
//    @Length(min = 3, max = 3)
    private String cardCvv;
}
