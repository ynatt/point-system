package any.mind.pointsystem.controller.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import static org.springframework.graphql.execution.ErrorType.BAD_REQUEST;

@Component
public class CustomErrorResolver extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        Throwable cause = NestedExceptionUtils.getMostSpecificCause(ex);
        if(cause instanceof PaymentMethodNotFoundException || cause instanceof PriceModifierIllegalValueException
                || cause instanceof PriceIllegalValueException) {
            return GraphqlErrorBuilder.newError(env)
                    .errorType(BAD_REQUEST)
                    .message(cause.getMessage())
                    .build();
        }
        return super.resolveToSingleError(ex, env);
    }
}
