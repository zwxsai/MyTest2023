package com.example.mytest2023.Api.Utils;

/**
 * Created by zwx on 2018/6/5.
 * Describer:
 */
public class ServerException extends RuntimeException {
	public int code;
	public String message;

	public ServerException(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
