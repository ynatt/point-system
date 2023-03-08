package any.mind.pointsystem.dto;


import lombok.Data;

@Data
public class PaymentDto {

    private String id;
    private String finalPrice;
    private int points;
    private String paymentMethod;
    private String dateTime;
}
