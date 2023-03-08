package any.mind.pointsystem.service;

import any.mind.pointsystem.dto.PaymentDto;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.dto.SaleDto;
import any.mind.pointsystem.entity.Payment;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {

    List<Payment> getPayments();

    PaymentDto processPayment(PrePaymentDetailsDto details);

    List<SaleDto> salesByHour(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
