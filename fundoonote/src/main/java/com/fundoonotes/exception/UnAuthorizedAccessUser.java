package com.fundoonotes.exception;

import com.fundoonotes.utility.Response;

public class UnAuthorizedAccessUser  extends RuntimeException {
	public UnAuthorizedAccessUser() {
		super("login denied");
	}
}
