package any.mind.pointsystem.service;

import any.mind.pointsystem.controller.exception.PaymentMethodNotFoundException;
import any.mind.pointsystem.controller.exception.PriceModifierIllegalValueException;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.entity.Payment;
import any.mind.pointsystem.entity.PaymentMethod;
import any.mind.pointsystem.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService{

    private final PaymentMethodRepository paymentMethodRepository;

    private Map<String, PaymentMethod> paymentMethods;

    private void initPayments() {
        paymentMethods = new HashMap<>();
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
        paymentMethodList.forEach(paymentMethod -> paymentMethods.put(paymentMethod.getName(), paymentMethod));
    }

    @Override
    public Map<String, PaymentMethod> paymentMethods() {
        if (isNull(paymentMethods)) {
            initPayments();
        }
        return paymentMethods;
    }

    @Override
    public void validate(PrePaymentDetailsDto prePaymentDetails) throws PaymentMethodNotFoundException {
        if(!paymentMethods().containsKey(prePaymentDetails.getPaymentMethod())) {
            throw new PaymentMethodNotFoundException(String.format("[%s] No such payment method", prePaymentDetails.getPaymentMethod()));
        }
        PaymentMethod paymentMethod = paymentMethods().get(prePaymentDetails.getPaymentMethod());
        if(prePaymentDetails.getPriceModifier() < paymentMethod.getPriceModifierMin()
                || prePaymentDetails.getPriceModifier() > paymentMethod.getPriceModifierMax()) {
            throw new PriceModifierIllegalValueException(
                    String.format("Price modifier [%s] is illegal value, should be from [%s] to [%s] range",
                            prePaymentDetails.getPriceModifier(), paymentMethod.getPriceModifierMin(), paymentMethod.getPriceModifierMax()));
        }
    }

    @Override
    public Payment process(PrePaymentDetailsDto prePaymentDetails) {
        double finalPrice = Double.parseDouble(prePaymentDetails.getPrice()) * prePaymentDetails.getPriceModifier();
        int points = (int) (Double.parseDouble(prePaymentDetails.getPrice()) *
                paymentMethods().get(prePaymentDetails.getPaymentMethod()).getPointsModifier());
        return new Payment(null, String.valueOf(finalPrice), points, prePaymentDetails.getPaymentMethod(),
                LocalDateTime.parse(prePaymentDetails.getDateTime(), DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC.normalized())));
    }
}
