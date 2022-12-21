package loan.management.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import loan.management.exception.BadRequestException;
import loan.management.exception.DuplicateNameException;
import loan.management.exception.NotFoundException;
import loan.management.exception.ServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiCallError<String>> handleNotFoundException(HttpServletRequest request,
                                                                        NotFoundException ex) {
        log.error("NotFoundException {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiCallError<>("Not found exception", Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiCallError<String>> handleValidationException(HttpServletRequest request,
                                                                          ValidationException ex) {
        log.error("ValidationException {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>("Validation exception", Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiCallError<String>> handleBindException(HttpServletRequest request,
                                                                    BindException ex) {
        log.error("BindException {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>("Bind exception", ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiCallError<String>> handleMissingServletRequestParameterException(HttpServletRequest request,
                                                                                              MissingServletRequestParameterException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>("Missing request parameter", Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiCallError<Map<String, String>>> handleMethodArgumentTypeMismatchException(
            HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
        log.error("HandleMethodArgumentTypeMismatchException {}\n", request.getRequestURI(), ex);

        Map<String, String> details = new HashMap<>();
        details.put("paramName", ex.getName());
        details.put("paramValue", ofNullable(ex.getValue()).map(Object::toString).orElse(""));
        details.put("errorMessage", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>("Method argument type mismatch", Collections.singletonList(details)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiCallError<Map<String, String>>> handleMethodArgumentNotValidException(
            HttpServletRequest request, MethodArgumentNotValidException ex) {
        log.error("HandleMethodArgumentNotValidException {}\n", request.getRequestURI(), ex);

        List<Map<String, String>> details = new ArrayList<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    Map<String, String> detail = new HashMap<>();
                    detail.put("objectName", fieldError.getObjectName());
                    detail.put("field", fieldError.getField());
                    detail.put("rejectedValue", "" + fieldError.getRejectedValue());
                    detail.put("errorMessage", fieldError.getDefaultMessage());
                    details.add(detail);
                });

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>("Method argument validation failed", details));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiCallError<String>> handleAccessDeniedException(HttpServletRequest request,
                                                                            AccessDeniedException ex) {
        log.error("HandleAccessDeniedException {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiCallError<>("Access denied!", Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiCallError<String>> handleSqlException(SQLException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiCallError<>(exception.getMessage(), Collections.singletonList(exception.getMessage())));
    }


    @ExceptionHandler(value = {BadRequestException.class, DuplicateNameException.class})
    public ResponseEntity<ApiCallError<String>> handleBadRequestException(HttpServletRequest request, BadRequestException ex) {
        log.error("Bad request {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiCallError<>("Bad request!", Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class, ServerErrorException.class})
    public ResponseEntity<ApiCallError<String>> handleInternalServerError(HttpServletRequest request, Exception ex) {
        log.error("handleInternalServerError {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiCallError<>("Internal server error", Collections.singletonList(ex.getMessage())));
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiCallError<T> {

        private String message;
        private List<T> details;

    }
}