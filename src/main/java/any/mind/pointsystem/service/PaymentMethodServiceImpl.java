package any.mind.pointsystem.service;

import any.mind.pointsystem.controller.exception.PaymentMethodNotFoundException;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.entity.Payment;
import any.mind.pointsystem.entity.PaymentMethod;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService{

    private final Map<String, PaymentMethod> paymentMethods = new HashMap<>(){{
        put("CASH", new PaymentMethod("CASH", 0.05, 0.9, 1));
        put("CASH_ON_DELIVERY", new PaymentMethod("CASH_ON_DELIVERY", 0.05, 1, 1.02));
        put("VISA", new PaymentMethod("VISA", 0.03, 0.95, 1));
        put("MASTERCARD", new PaymentMethod("MASTERCARD", 0.03, 0.95, 1));
        put("AMEX", new PaymentMethod("AMEX", 0.02, 0.98, 1.01));
        put("JCB", new PaymentMethod("JCB", 0.05, 0.95, 1));
    }};

    @Override
    public Map<String, PaymentMethod> paymentMethods() {
        return paymentMethods;
    }

    @Override
    public void validate(PrePaymentDetailsDto prePaymentDetails) throws PaymentMethodNotFoundException {
        if(!paymentMethods.containsKey(prePaymentDetails.getPaymentMethod())) {
            throw new PaymentMethodNotFoundException(String.format("[%s] No such payment method", prePaymentDetails.getPaymentMethod()));
        }
    }

    @Override
    public Payment process(PrePaymentDetailsDto prePaymentDetails) {
        double finalPrice = Double.parseDouble(prePaymentDetails.getPrice()) * prePaymentDetails.getPriceModifier();
        int points = (int) (Double.parseDouble(prePaymentDetails.getPrice()) *
                paymentMethods.get(prePaymentDetails.getPaymentMethod()).getPointsModifier());
        return new Payment(null, String.valueOf(finalPrice), points, prePaymentDetails.getPaymentMethod(),
                LocalDateTime.parse(prePaymentDetails.getDatetime(), DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC.normalized())));
    }
}
