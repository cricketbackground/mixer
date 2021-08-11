package com.async.mixer.mixer.advice;

import com.async.mixer.mixer.exception.InternalServerError;
import com.async.mixer.mixer.model.BaseResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = FeignException.NotFound.class)
    public ResponseEntity<BaseResponse> handleCompletionException(FeignException.NotFound ex) {
        return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(value = InternalServerError.class)
    public ResponseEntity<BaseResponse> handleCompletionException(InternalServerError ex) {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<BaseResponse> errorResponse(HttpStatus status, String message) {
        BaseResponse response = new BaseResponse();
        response.setMessage(message);
        response.setHttpStatus(status);
        return ResponseEntity.status(status).body(response);
    }

}