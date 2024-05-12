package com.ronyelison.blog.controller.exceptions;

public class ErroResponse {
    private String message;
    private String status;

    public ErroResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
