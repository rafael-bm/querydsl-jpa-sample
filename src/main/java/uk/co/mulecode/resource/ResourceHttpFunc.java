package uk.co.mulecode.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Function;
import java.util.function.Supplier;

public interface ResourceHttpFunc {

    Function<String, Supplier<ResponseStatusException>> notFoundResponse = resourceId ->
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource '" + resourceId + "' not found.");

    Supplier<ResponseStatusException> internalErrorSupplierResponse = () ->
            new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

    Supplier<ResponseStatusException> noContentFoundSupplierResponse = () ->
            new ResponseStatusException(HttpStatus.NO_CONTENT);

    Function<String, Supplier<ResponseStatusException>> noContentFoundResponse = message ->
            () -> new ResponseStatusException(HttpStatus.NO_CONTENT, message);

    Function<String, Supplier<ResponseStatusException>> badRequestResponse = message ->
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
}
