package com.userService.app.getAllUserResponseVO;

import java.util.List;

import com.userService.app.entity.Users;



public class GetAllUserResponse{
	
	private String responseMessage;
	
	private String responseCode;
	
	private List<Users> responseObject;
	
	

	public GetAllUserResponse(String responseMessage, String responseCode, List<Users> responseObject) {
		super();
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
		this.responseObject = responseObject;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public List<Users> getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(List<Users> responseObject) {
		this.responseObject = responseObject;
	}
	
	
	
}