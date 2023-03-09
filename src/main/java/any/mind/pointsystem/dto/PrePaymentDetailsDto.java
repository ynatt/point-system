package any.mind.pointsystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PrePaymentDetailsDto {

    @NotNull
    @NotBlank
    private final String price;
    private final double priceModifier;
    @NotNull
    @NotBlank
    private final String paymentMethod;
    @NotNull
    private final String dateTime;
}
