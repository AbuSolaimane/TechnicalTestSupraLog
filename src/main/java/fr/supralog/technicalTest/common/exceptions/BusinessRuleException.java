package fr.supralog.technicalTest.common.exceptions;


import org.springframework.http.HttpStatus;

public class BusinessRuleException extends RuntimeException {

	private String[] params;

	private HttpStatus httpStatus = HttpStatus.CONFLICT;

	public BusinessRuleException(String message) {
		super(message);
	}
	
	public BusinessRuleException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public BusinessRuleException(String message, String[] params) {
		super(message);
		this.params = params;
	}
	
	

	public BusinessRuleException(String message,String[] params, HttpStatus httpStatus) {
		super(message);
		this.params = params;
		this.httpStatus = httpStatus;
	}
	public BusinessRuleException(Throwable cause) {
		super(cause);
	}

	public BusinessRuleException(String message, Throwable cause) {
		super(message, cause);
	}

	public String[] getParams() {
		return this.params;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}