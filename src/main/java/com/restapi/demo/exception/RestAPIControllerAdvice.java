package com.restapi.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@ControllerAdvice
public class RestAPIControllerAdvice extends ResponseEntityExceptionHandler{
	
//	Handle my custom exception
	@ExceptionHandler(ResourceDoesNotExistException.class)
	public ResponseEntity<Object> handleResouceDoesNotExistException(Exception ex, WebRequest request){
//		String body = "The resource does not exist. Please try with other parameter";
		ResponseError body = createResponseError(ex, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString(), "ERROR_BUSINESS_EXCEPTION");
		return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);
	}

//	Override Spring Exceptions
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(
			MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String body="Parameters invalid. Please review the guideline";
		return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String body="Request invalid. Please, review the guideline";
		return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
	}


	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
			HttpMediaTypeNotAcceptableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
//		String body="Media Type invalid. Please try again using applicatio/json";
		MediaType mediaType= MediaType.parseMediaType(request.getHeader("accept"));
		HttpMediaTypeNotSupportedException exa = new HttpMediaTypeNotSupportedException(mediaType, ex.getSupportedMediaTypes());
		headers.setAccept(ex.getSupportedMediaTypes());
		ResponseError body= createResponseError(exa, HttpStatus.UNSUPPORTED_MEDIA_TYPE,HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), "ERROR_BUSINESS_EXCEPTION");
		return handleExceptionInternal(exa,body, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
	}
	
	private ResponseError createResponseError (Throwable ex, HttpStatus httpStatus, String httpStatusStr, String errorType){
			ResponseError responseError = new ResponseError("ERROR_CODE",ex.getLocalizedMessage(),ex.getMessage());
			responseError.setErrorMessage(httpStatus.getReasonPhrase()+". "+responseError.getErrorMessage());
			responseError.setHttpStatus(httpStatusStr);
			responseError.setErrorCode(errorType);
			return responseError;
	}
}
