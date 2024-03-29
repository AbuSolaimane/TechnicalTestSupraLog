package fr.supralog.technicalTest.common.exceptions;

import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.supralog.technicalTest.common.beans.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        
    	BindingResult result = ex.getBindingResult();
        StringJoiner joiner = new StringJoiner(", ");

        for (FieldError error : result.getFieldErrors()) {
        	joiner.add(messageSource.getMessage(error.getDefaultMessage(), new String[] { error.getField() }, null));
        }
        
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, joiner.toString());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessExceptions(BusinessRuleException ex) {
        
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, messageSource.getMessage(ex.getMessage(), ex.getParams(), null));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundExceptions(UserNotFoundException ex) {
        
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, messageSource.getMessage(ex.getMessage(), ex.getParams(), null));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralExceptions(Exception ex) {
        
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
