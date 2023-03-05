package any.mind.pointsystem.controller.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.graphql.execution.ErrorType;

import java.util.List;

public class PaymentMethodNotFoundException extends RuntimeException implements GraphQLError {

    public PaymentMethodNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.BAD_REQUEST;
    }
}
