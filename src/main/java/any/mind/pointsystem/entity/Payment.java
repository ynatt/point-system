package any.mind.pointsystem.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Payment {
    private final String id;
    private final String finalPrice;
    private final int points;
    private final String paymentMethod;
    private final LocalDateTime datetime;
}
