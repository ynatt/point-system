package any.mind.pointsystem.service;

import any.mind.pointsystem.controller.exception.PaymentMethodNotFoundException;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getPayments();

    Payment processPayment(PrePaymentDetailsDto details);
}
