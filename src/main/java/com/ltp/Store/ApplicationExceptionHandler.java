package com.ltp.Store;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ltp.Store.exception.ErrorResponse;
import com.ltp.Store.exception.OrderNotFoundWithIdException;
import com.ltp.Store.exception.ProductNotFoundWithIdException;
import com.ltp.Store.exception.RoleNotFoundWithDescriptionException;
import com.ltp.Store.exception.UserNotFoundWithIdException;
import com.ltp.Store.exception.UserNotFoundWithUsername;
import com.ltp.Store.exception.UsernameExistsException;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UserNotFoundWithIdException.class, RoleNotFoundWithDescriptionException.class,
            UserNotFoundWithUsername.class, ProductNotFoundWithIdException.class, OrderNotFoundWithIdException.class })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException ex) {
        ErrorResponse errors = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UsernameExistsException.class })
    public ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        ErrorResponse errors = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }
}
