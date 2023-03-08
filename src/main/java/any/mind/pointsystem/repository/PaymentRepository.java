package any.mind.pointsystem.repository;

import any.mind.pointsystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByDateTimeGreaterThanEqualAndDateTimeLessThan(LocalDateTime start, LocalDateTime end);
}
