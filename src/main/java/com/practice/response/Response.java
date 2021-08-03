package com.practice.response;


import org.springframework.http.HttpStatus;

public class Response {

	private int responseCode;
	private String responseMessage;
	private HttpStatus httpStatus;

	public Response() {
	}
	public Response(int responseCode, String responseMessage) {
		super();
		this.httpStatus = HttpStatus.valueOf(responseCode);
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	public Response(HttpStatus httpStatus, String responseMessage) {
		super();
		this.httpStatus = httpStatus;
		this.responseCode = httpStatus.value();
		this.responseMessage = responseMessage;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}