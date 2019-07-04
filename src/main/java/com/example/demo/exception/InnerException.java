package com.example.demo.exception;

/**
 * 内部异常
 * @author lm
 */
public class InnerException extends RuntimeException {

	private Integer code = 666;

	public InnerException(String msg) {
		super(msg);
	}

	public Integer getCode() {
		return code;
	}
}
