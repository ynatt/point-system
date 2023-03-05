package any.mind.pointsystem.repository;

import any.mind.pointsystem.entity.Payment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{

    List<Payment> payments = new CopyOnWriteArrayList<>(){{
        add(new Payment("1", "95", 5, "VISA", LocalDateTime.now()));
        add(new Payment("2", "100", 5, "MASTERCARD", LocalDateTime.now()));
        add(new Payment("3", "200", 10, "MASTERCARD", LocalDateTime.now()));
    }};

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public Payment save(Payment payment) {
        payments.add(new Payment(String.valueOf(payments.size()),
                payment.getFinalPrice(),
                payment.getPoints(),
                payment.getPaymentMethod(),
                payment.getDatetime()));
        return payment;
    }
}
