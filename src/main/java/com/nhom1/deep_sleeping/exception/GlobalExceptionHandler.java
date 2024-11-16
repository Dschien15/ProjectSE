package com.nhom1.deep_sleeping.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String handlingException(Exception exception, Model model) {
        log.info("Exception: ", exception);
        model.addAttribute("errorMessage", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(value = AppException.class)
    public String handlingAppException(AppException exception, Model model) {
        log.info("Exception: ", exception);
        model.addAttribute("errorMessage", exception.getErrorCode().getMessage());
        return "error";
    }
}