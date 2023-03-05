package any.mind.pointsystem.service;

import any.mind.pointsystem.controller.exception.PaymentMethodNotFoundException;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.entity.Payment;
import any.mind.pointsystem.entity.PaymentMethod;

import java.util.Map;

public interface PaymentMethodService {

    Map<String, PaymentMethod> paymentMethods();
    void validate(PrePaymentDetailsDto prePaymentDetails) ;

    Payment process(PrePaymentDetailsDto prePaymentDetailsDto);
}
