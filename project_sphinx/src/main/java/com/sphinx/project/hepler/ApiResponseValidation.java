package com.sphinx.project.hepler;

import lombok.Data;

@Data
public class ApiResponseValidation {
	
	 private int code;
	private String fieldName;
	private String message;
	private Object data;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
    public static ApiResponseValidation build(int code , String message)
    {
    	ApiResponseValidation apiResponse = new ApiResponseValidation();
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        return apiResponse;
    }
}
