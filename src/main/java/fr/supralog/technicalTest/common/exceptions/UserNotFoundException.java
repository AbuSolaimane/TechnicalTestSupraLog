package fr.supralog.technicalTest.common.exceptions;

public class UserNotFoundException extends RuntimeException {

	private String[] params;

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(String message, String[] params) {
		super(message);
		this.params = params;
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
}