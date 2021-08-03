package com.practice.response;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class DataResponse extends Response {

	Object data;

	public DataResponse(HttpStatus httpStatus, String message, Object data) {
		super(httpStatus, message);
		this.data = data;
	}

	public DataResponse() {
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}


