package any.mind.pointsystem.controller;

import any.mind.pointsystem.dto.PaymentDto;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.dto.SaleDto;
import any.mind.pointsystem.entity.Payment;
import any.mind.pointsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @QueryMapping
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @QueryMapping
    public List<SaleDto> salesByHour(@Argument String startDateTime, @Argument String endDateTime) {
        return paymentService.salesByHour(LocalDateTime.parse(startDateTime, DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC.normalized())),
                LocalDateTime.parse(endDateTime, DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC.normalized())));
    }

    @MutationMapping
    public PaymentDto processPayment(@Argument @Valid PrePaymentDetailsDto prePaymentDetails) {
        return paymentService.processPayment(prePaymentDetails);
    }
}
