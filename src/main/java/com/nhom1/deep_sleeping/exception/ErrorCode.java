package com.nhom1.deep_sleeping.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION("Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY("Uncategorized error", HttpStatus.BAD_REQUEST),
    INVALID_NAME("Invalid name", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL("Invalid email", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD("Password must be at least 5 character", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED("Email already exist", HttpStatus.BAD_REQUEST),
    AUTHENTICATION_FAILED("Email or password is not correct", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED("Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("Unauthorized", HttpStatus.FORBIDDEN),
    ENROLLMENT_EXISTED("Enrollment already exist", HttpStatus.BAD_REQUEST),
    PARSE_ERROR("Type cannot be converted, please enter correct value", HttpStatus.BAD_REQUEST),
    COURSE_IS_FULL("The course is full", HttpStatus.BAD_REQUEST),
    COURSE_IS_STARTED("Course have already started", HttpStatus.BAD_REQUEST),
    INVALID_TEACHER("Teacher cannot be blank", HttpStatus.BAD_REQUEST),
    INVALID_DURATION("DURATION must be in [0.5, 100]", HttpStatus.BAD_REQUEST),
    EMAIL_REQUIRED("Email is required", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED("Password is required", HttpStatus.BAD_REQUEST),
    NO_RESOURCE_FOUND("404 not found", HttpStatus.BAD_REQUEST),;
    ErrorCode(String message, HttpStatusCode httpStatusCode) {
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
