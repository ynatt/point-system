package any.mind.pointsystem.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PrePaymentDetailsDto {

    @NotNull
    private final String price;
    private final double priceModifier;
    @NotNull
    private final String paymentMethod;

    private final String datetime;
}
