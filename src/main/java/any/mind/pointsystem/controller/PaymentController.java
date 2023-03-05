package any.mind.pointsystem.controller;

import any.mind.pointsystem.controller.exception.PaymentMethodNotFoundException;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.entity.Payment;
import any.mind.pointsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @QueryMapping
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @MutationMapping
    public Payment processPayment(@Argument @Valid PrePaymentDetailsDto prePaymentDetails) {
        return paymentService.processPayment(prePaymentDetails);
    }
}
