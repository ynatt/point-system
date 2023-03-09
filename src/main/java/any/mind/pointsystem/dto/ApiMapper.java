package any.mind.pointsystem.dto;

import any.mind.pointsystem.DateHelper;
import any.mind.pointsystem.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiMapper {

    @Mapping(target = "dateTime", source = "dateTime", dateFormat = DateHelper.DATE_ISO_INSTANT_UTC)
    PaymentDto entityToDto(Payment payment);
}
