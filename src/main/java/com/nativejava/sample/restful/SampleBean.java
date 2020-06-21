package com.nativejava.sample.restful;

public class SampleBean {

	private String msg;

	public SampleBean(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "SampleBean [msg=" + msg + "]";
	}

}
