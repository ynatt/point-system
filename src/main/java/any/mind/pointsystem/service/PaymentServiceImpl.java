package any.mind.pointsystem.service;

import any.mind.pointsystem.controller.exception.PaymentMethodNotFoundException;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.entity.Payment;
import any.mind.pointsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    private final PaymentMethodService paymentMethodService;

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.getAllPayments();
    }

    @Override
    public Payment processPayment(PrePaymentDetailsDto details) {
        paymentMethodService.validate(details);
        Payment payment = paymentMethodService.process(details);
        return paymentRepository.save(payment);
    }
}
