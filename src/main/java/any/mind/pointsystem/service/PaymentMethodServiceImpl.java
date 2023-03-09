package any.mind.pointsystem.service;

import any.mind.pointsystem.DateHelper;
import any.mind.pointsystem.controller.exception.PriceIllegalValueException;
import any.mind.pointsystem.controller.exception.PaymentMethodNotFoundException;
import any.mind.pointsystem.controller.exception.PriceModifierIllegalValueException;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.entity.Payment;
import any.mind.pointsystem.entity.PaymentMethod;
import any.mind.pointsystem.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static any.mind.pointsystem.DateHelper.DATE_ISO_INSTANT_UTC;
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
    public void validate(PrePaymentDetailsDto prePaymentDetails) throws RuntimeException {
        if(!paymentMethods().containsKey(prePaymentDetails.getPaymentMethod())) {
            throw new PaymentMethodNotFoundException(String.format("[%s] No such payment method", prePaymentDetails.getPaymentMethod()));
        }
        try {
            if (new BigDecimal(prePaymentDetails.getPrice()).compareTo(BigDecimal.ZERO) < 0) {
                throw new PriceIllegalValueException("Price is less than 0");
            }
        } catch (NumberFormatException e) {
            throw new PriceIllegalValueException("Illegal format of price, not a decimal");
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
        BigDecimal price = new BigDecimal(prePaymentDetails.getPrice());
        BigDecimal finalPrice = price.multiply(BigDecimal.valueOf(prePaymentDetails.getPriceModifier()));
        int points = price.multiply(
                BigDecimal.valueOf(paymentMethods().get(prePaymentDetails.getPaymentMethod()).getPointsModifier()))
                .intValue();
        return Payment.builder()
                .finalPrice(finalPrice.toString())
                .points(points)
                .paymentMethod(prePaymentDetails.getPaymentMethod())
                .dateTime(DateHelper.toLocalDate(prePaymentDetails.getDateTime(), DATE_ISO_INSTANT_UTC))
                .build();
    }
}
