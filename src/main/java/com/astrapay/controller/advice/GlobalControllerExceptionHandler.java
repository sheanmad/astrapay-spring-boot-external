package com.astrapay.controller.advice;

import com.astrapay.dto.APIResponse;
import com.astrapay.exception.CustomCheckedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(CustomCheckedException.class)
    public ResponseEntity<APIResponse<Void>> handleCustomException(CustomCheckedException e){
        return new ResponseEntity<>(
                new APIResponse<>(e.getErrorStatus(), List.of(e.getMessage())),
                HttpStatus.valueOf(e.getErrorHttpStatus())
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new APIResponse<>("Failed", errors));
    }
}