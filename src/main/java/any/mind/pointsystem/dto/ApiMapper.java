package any.mind.pointsystem.dto;

import any.mind.pointsystem.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApiMapper {

    @Mapping(target = "dateTime", source = "dateTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    PaymentDto entityToDto(Payment payment);
}
