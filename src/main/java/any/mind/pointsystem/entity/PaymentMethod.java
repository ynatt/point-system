package any.mind.pointsystem.entity;

import lombok.Data;

@Data
public class PaymentMethod {

    private final String name;

    private final double pointsModifier;

    private final double priceModifierMin;

    private final double priceModifierMan;
}
