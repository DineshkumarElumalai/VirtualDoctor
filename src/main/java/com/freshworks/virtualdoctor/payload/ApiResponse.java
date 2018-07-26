package com.freshworks.virtualdoctor.payload;

public class ApiResponse {
    private Boolean success;
    private String message;
    private int id = 1;


    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
