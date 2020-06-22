package com.nativejava.exceptions;

import java.util.Date;

public class ExceptionResponseBean {

	private Date timestamp;
	private String message;
	private String detail;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public ExceptionResponseBean(Date timestamp, String message, String detail, String code) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.detail = detail;
		this.code = code;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
