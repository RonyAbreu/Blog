package com.ronyelison.blog.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ErroResponse{
    private List<FieldMessage> fieldMessages = new ArrayList<>();

    public ValidationError(String message, String status) {
        super(message, status);
    }

    public void addFieldMessage(String field, String message){
        fieldMessages.add(new FieldMessage(field, message));
    }

    public List<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }
}
