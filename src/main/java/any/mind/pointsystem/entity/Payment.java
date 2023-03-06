package any.mind.pointsystem.entity;

import graphql.schema.DataFetchingEnvironment;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Payment {
    private final String id;
    private final String finalPrice;
    private final int points;
    private final String paymentMethod;
    private final LocalDateTime dateTime;


    public String getDateTime(DataFetchingEnvironment environment) {
        String format = environment.getArgument("format");
        return DateTimeFormatter.ofPattern(format).format(dateTime);
    }
}
