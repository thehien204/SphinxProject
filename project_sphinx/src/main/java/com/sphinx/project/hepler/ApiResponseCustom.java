package com.sphinx.project.hepler;

import lombok.Data;

@Data
public class ApiResponseCustom {
    private int code;
    private String message;
    private Object data1;
    private Object data2;
    
    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData1() {
		return data1;
	}

	public void setData1(Object data1) {
		this.data1 = data1;
	}

	public Object getData2() {
		return data2;
	}

	public void setData2(Object data2) {
		this.data2 = data2;
	}

	public ApiResponseCustom(int code, String message, Object data1, Object data2) {
        super();
        this.code = code;
        this.message = message;
        this.data1 = data1;
        this.data1 = data2;
    }

    public ApiResponseCustom(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ApiResponseCustom() {
        // TODO Auto-generated constructor stub
        super();
    }

    public static ApiResponseCustom build(int code, boolean status, String errors, Object data1,Object data2)
    {
        ApiResponseCustom apiResponse = new ApiResponseCustom();
        apiResponse.setCode(code);
        apiResponse.setData1(data1);
        apiResponse.setData2(data2);
        apiResponse.setMessage(errors);
        return apiResponse;
    }


}
