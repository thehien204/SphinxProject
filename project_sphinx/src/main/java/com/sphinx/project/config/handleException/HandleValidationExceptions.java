package com.sphinx.project.config.handleException;

import com.sphinx.project.hepler.ApiResponseValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class HandleValidationExceptions {

	@Autowired
	MessageSource messageSource;

	@Autowired
	HttpServletRequest httpServletRequest;

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		ApiResponseValidation response = new ApiResponseValidation();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        response.setFieldName(fieldName);
			if (errorMessage != null) {
				response.setMessage(convertErrorToMessage(errorMessage, httpServletRequest));
			}
			response.setCode(400);
	    });
	    Gson gson = new Gson();
	    String json = gson.toJson(response);
	   return ResponseEntity.ok().body(json.getBytes(StandardCharsets.UTF_8));
	    
	}

	private String convertErrorToMessage(String messageError, HttpServletRequest httpServletRequest){
		switch (messageError) {
			case "NotNull":
				messageError = messageSource.getMessage("not.null", null,  httpServletRequest.getLocale());
				break;
			case "Required":
				messageError = messageSource.getMessage("dto.Required", null,  httpServletRequest.getLocale());
				break;
			default:
				messageError = "";
		}
		return messageError;
	}
}
