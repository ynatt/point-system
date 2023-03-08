package any.mind.pointsystem.entity;

import graphql.schema.DataFetchingEnvironment;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FINAL_PRICE")
    private String finalPrice;
    @Column(name = "POINTS")
    private int points;

    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;


    public String getDateTime(DataFetchingEnvironment environment) {
        String format = environment.getArgument("format");
        return DateTimeFormatter.ofPattern(format).format(dateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Payment payment = (Payment) o;
        return getId() != null && Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
