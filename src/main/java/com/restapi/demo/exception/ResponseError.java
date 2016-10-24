package com.restapi.demo.exception;

import java.util.List;

public class ResponseError{
	
	private String errorCode;
	private String httpStatus;
	private String errorMessage;
	private String rootErrorMessage;
	private List<String> errorList;
	

	public ResponseError(String errorCode, String httpStatus,
			String errorMessage, String rootErrorMessage, List<String> errorList) {
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.rootErrorMessage = rootErrorMessage;
		this.errorList = errorList;
	}
	
	public ResponseError(String errorCode, String errorMessage,String rootErrorMessage){
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
		this.rootErrorMessage=rootErrorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getRootErrorMessage() {
		return rootErrorMessage;
	}
	public void setRootErrorMessage(String rootErrorMessage) {
		this.rootErrorMessage = rootErrorMessage;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	
	
	
}
