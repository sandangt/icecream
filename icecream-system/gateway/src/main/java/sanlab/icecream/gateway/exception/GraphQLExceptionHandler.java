package sanlab.icecream.gateway.exception;

import java.util.Collections;
import java.util.List;

import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
import sanlab.icecream.gateway.exception.controller.BadRequestException;
import sanlab.icecream.gateway.exception.controller.NotFoundException;
import sanlab.icecream.gateway.exception.controller.UnprocessableEntityException;


@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected List<GraphQLError> resolveToMultipleErrors(@NotNull Throwable ex, DataFetchingEnvironment env) {
        List<SourceLocation> sourceLocation = List.of(env.getField().getSourceLocation());

        if (ex instanceof ConstraintViolationException constraintViolationException) {
            return constraintViolationException.getConstraintViolations().stream()
                .map(constraint -> new BadRequestException(constraint.getMessageTemplate(), sourceLocation))
                .map(badRequestException -> (GraphQLError) badRequestException)
                .toList();
        }

        return Collections.emptyList();
    }

    @Override
    protected GraphQLError resolveToSingleError(@NotNull Throwable ex, DataFetchingEnvironment env) {

        List<SourceLocation> sourceLocation = List.of(env.getField().getSourceLocation());

        if (ex instanceof NotFoundException notFoundException) {
            notFoundException.setLocations(sourceLocation);
            return notFoundException;
        }

        if (ex instanceof UnprocessableEntityException unprocessableEntityException) {
            unprocessableEntityException.setLocations(sourceLocation);
            return unprocessableEntityException;
        }
        return (GraphQLError) ex;
    }
}
