package com.sphinx.project.hepler;

import lombok.Data;

@Data
public class ApiResponse {
    private int code;
    private String message;
    private Object data;
    private String content;
    private String totals;

    
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTotals() {
		return totals;
	}

	public void setTotals(String totals) {
		this.totals = totals;
	}

	public ApiResponse(int code, String message, Object data, String content) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
        this.content = content;

    }

    public ApiResponse(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ApiResponse() {
        // TODO Auto-generated constructor stub
        super();
    }

    public static ApiResponse build(int code, boolean status ,String errors, Object data)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setData(data);
        apiResponse.setMessage(errors);
        return apiResponse;
    }

    public static ApiResponse builder(int code, boolean status ,String errors, Object data, String content)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setData(data);
        apiResponse.setMessage(errors);
        apiResponse.setContent(content);
        return apiResponse;
    }

    public static ApiResponse builders(int code, boolean status ,String errors, Object data, String content)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setData(data);
        apiResponse.setMessage(errors);
        apiResponse.setContent(content);
        return apiResponse;
    }

    public static ApiResponse buildFindBy(int code, boolean status ,String errors, Object data,String totals)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setData(data);
        apiResponse.setMessage(errors);
        apiResponse.setTotals(totals);
        return apiResponse;
    }

}
