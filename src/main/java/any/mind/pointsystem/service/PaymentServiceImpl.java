package any.mind.pointsystem.service;

import any.mind.pointsystem.DateHelper;
import any.mind.pointsystem.dto.ApiMapper;
import any.mind.pointsystem.dto.PaymentDto;
import any.mind.pointsystem.dto.PrePaymentDetailsDto;
import any.mind.pointsystem.dto.SaleDto;
import any.mind.pointsystem.entity.Payment;
import any.mind.pointsystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static any.mind.pointsystem.DateHelper.DATE_ISO_INSTANT_UTC;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final ApiMapper mapper;

    private final PaymentRepository paymentRepository;

    private final PaymentMethodService paymentMethodService;

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentDto processPayment(PrePaymentDetailsDto details) {
        paymentMethodService.validate(details);
        Payment payment = paymentMethodService.process(details);
        return mapper.entityToDto(paymentRepository.save(payment));
    }

    @Override
    public List<SaleDto> salesByHour(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<SaleDto> result = new ArrayList<>();
        LocalDateTime start;
        LocalDateTime end;
        SaleDto saleDto;
        start = startDateTime;
        while(start.isBefore(endDateTime)) {
            end = start.plus(1, ChronoUnit.HOURS).isBefore(endDateTime) ? start.plus(1, ChronoUnit.HOURS) : endDateTime;
            saleDto = new SaleDto();
            saleDto.setDateTime(DateHelper.toFormattedDate(start, DATE_ISO_INSTANT_UTC));
            for(Payment payment : paymentRepository.findAllByDateTimeGreaterThanEqualAndDateTimeLessThan(start, end)) {
                addPriceAndPoints(payment, saleDto);
            }
            result.add(saleDto);
            start = end;
        }
        return result;
    }

    private void addPriceAndPoints(Payment payment, SaleDto saleDto){
        saleDto.setSales(saleDto.getSales() + Double.parseDouble(payment.getFinalPrice()));
        saleDto.setPoints(saleDto.getPoints() + payment.getPoints());
    }
}
