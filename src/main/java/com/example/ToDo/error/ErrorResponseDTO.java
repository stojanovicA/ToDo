package com.example.ToDo.error;

import java.time.LocalDate;

public class ErrorResponseDTO {
	private String message;
	private int status;
	private LocalDate timestamp;
	public ErrorResponseDTO(String message, int status, LocalDate timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDate getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
