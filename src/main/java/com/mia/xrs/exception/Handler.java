//package com.mia.xrs.exception;
//
//import jakarta.servlet.http.HttpServletRequest;
//import com.mia.xrs.dto.ErrorResponseDto;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDateTime;
//
//@ControllerAdvice
//public class Handler {
//
//    @ExceptionHandler(NotFoundException.class)
//    public ErrorResponse handleNotFoundException(NotFoundException exception, HttpServletRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                LocalDateTime.now(),
//                HttpStatus.NOT_FOUND.value(),
//                exception.getMessage(),
//                request.getRequestURI()
//        );
//        return errorResponse;
//    }
//
//
//
//}
