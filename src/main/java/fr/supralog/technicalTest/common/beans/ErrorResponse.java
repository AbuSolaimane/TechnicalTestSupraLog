package fr.supralog.technicalTest.common.beans;


import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.http.HttpStatus;


public class ErrorResponse implements Serializable {

	private Timestamp timestamp;
	private int status;
	private String message;

	public ErrorResponse() {

	}

	public ErrorResponse(HttpStatus httpStatus, String message) {
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.status = httpStatus.value();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
