package com.example.demo.exception;

/**
 * @author liulinbao
 */
public class ServerException extends RuntimeException {

	private Integer code;

	public ServerException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
