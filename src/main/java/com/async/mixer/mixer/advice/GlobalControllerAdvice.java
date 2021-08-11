package com.async.mixer.mixer.advice;

import com.async.mixer.mixer.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.CompletionException;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = CompletionException.class)
    public ResponseEntity<BaseResponse> handleCompletionException(CompletionException e) {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<BaseResponse> errorResponse(HttpStatus status, String message) {
        BaseResponse response = new BaseResponse();
        response.setMessage(message);
        response.setHttpStatus(status);
        return ResponseEntity.status(status).body(response);
    }

}