package com.food.ordering.system.applicaiton.order.service.application.rest.exception.handler;

import com.food.ordering.system.applicaiton.ErrorDTO;
import com.food.ordering.system.applicaiton.handler.GlobalExceptionHandler;
import com.food.ordering.system.applicaiton.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.applicaiton.order.service.domain.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {OrderDomainException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(OrderDomainException ex) {
        log.error("Order domain exception occurred: {}", ex.getMessage());
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {OrderNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(OrderNotFoundException ex) {
        log.error("Order domain exception occurred: {}", ex.getMessage());
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .build();
    }
}
