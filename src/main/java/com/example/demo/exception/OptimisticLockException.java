package com.example.demo.exception;

/**
 * @author liulinbao
 * 当更新数据库时，没有数据被更新，返回该异常
 * 注意： 该类继承的是RuntimeException，而不是直接继承Exception
 *
 */
public class OptimisticLockException extends RuntimeException {
	private static final long serialVersionUID = 2280148126932820858L;

	private Integer code;

	public OptimisticLockException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
