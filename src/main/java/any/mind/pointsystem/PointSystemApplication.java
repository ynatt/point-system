package any.mind.pointsystem;

import any.mind.pointsystem.entity.PaymentMethod;
import any.mind.pointsystem.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class PointSystemApplication {

	private final PaymentMethodRepository paymentMethodRepository;

	public static void main(String[] args) {
		SpringApplication.run(PointSystemApplication.class, args);
	}

	private final List<PaymentMethod> paymentMethods = new ArrayList<>(){{
		add(new PaymentMethod("CASH", 0.05, 0.9, 1));
		add(new PaymentMethod("CASH_ON_DELIVERY", 0.05, 1, 1.02));
		add(new PaymentMethod("VISA", 0.03, 0.95, 1));
		add(new PaymentMethod("MASTERCARD", 0.03, 0.95, 1));
		add(new PaymentMethod("AMEX", 0.02, 0.98, 1.01));
		add(new PaymentMethod("JCB", 0.05, 0.95, 1));
	}};

	@EventListener
	public void savePaymentMethods(ContextRefreshedEvent event){
		for(PaymentMethod paymentMethod: paymentMethods) {
			paymentMethodRepository.save(paymentMethod);
		}
	}
}
