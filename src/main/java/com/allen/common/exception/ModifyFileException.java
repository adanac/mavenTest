package com.allen.common.exception;

public class ModifyFileException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2139264123917809615L;

	public ModifyFileException(String msg) {
		super(msg);
	}

	public ModifyFileException(Throwable e) {
		super(e);
	}

	public ModifyFileException(String msg, Throwable e) {
		super(msg, e);
	}

}
