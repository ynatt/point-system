package any.mind.pointsystem.repository;

import any.mind.pointsystem.entity.Payment;

import java.util.List;

public interface PaymentRepository {

    List<Payment> getAllPayments();

    Payment save(Payment payment);
}
