package com.bank.rest.api.constants;

public class Constants {
	
	public static final long TOKEN_LIFETIME_SECONDS = 5 * 60 * 60;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	static final String AUTHORITIES_KEY = "scopes";

	public static final String JWT_ILLEGAL_ARGUMENT_MESSAGE = "An error occured during getting username from token";
	public static final String JWT_EXPIRED_MESSAGE = "The token is expired and not valid anymore";
	public static final String JWT_SIGNATURE_MESSAGE = "Authentication Failed. Username or Password not valid.";
	public static final String UNAUTHORIZED_MESSAGE = "You are not authorized to view the resource";
	public static final String FORBIDDEN_MESSAGE = "You don't have the right to access to this resource";
	public static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource not found.";
	public static final String INVALID_DATA_MESSAGE = "One or many parameters in the request's body are invalid";

	public static final String MESSAGE_KEY = "message";
	public static final String DATA_KEY = "message";
	public static final String INVALID_TOKEN_MESSAGE = "The token is invalid!";
	public static final String TOKEN_EXPIRED_MESSAGE = "You token has been expired!";
	public static final String RESET_PASSWORD_SUCCESS_MESSAGE = "Your password has been resetted successfully!";
	public static final String VALIDATE_TOKEN_SUCCESS_MESSAGE = "valid";
	public static final String TOKEN_NOT_FOUND_MESSAGE = "You token has been expired!";
	public static final String PASSWORD_NOT_MATCH_MESSAGE = "The current password don't match!";
	public static final String USER_PICTURE_NO_ACTION_MESSAGE = "Unknown action!";

	public static final double MAX_DEPOSIT_PER_TRANSACTION = 50000; // $50k
	public static final double MAX_DEPOSIT_PER_DAY = 150000; // $150k
	public static final int MAX_DEPOSIT_TRANSACTIONS_PER_DAY = 5;

	public static final double MAX_WITHDRAWAL_PER_TRANSACTION = 12000; // $12k
	public static final double MAX_WITHDRAWAL_PER_DAY = 50000; // $50k
	public static final int MAX_WITHDRAWAL_TRANSACTIONS_PER_DAY = 3;
	

}