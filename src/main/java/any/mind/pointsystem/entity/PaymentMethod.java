package any.mind.pointsystem.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYMENT_METHOD")
public class PaymentMethod {

    @Id
    private String name;

    @Column(name = "POINTS_MODIFIER")
    private double pointsModifier;

    @Column(name = "PRICE_MODIFIER_MIN")
    private double priceModifierMin;

    @Column(name = "PRICE_MODIFIER_MAX")
    private double priceModifierMax;
}
