package com.ozhegov.tennis.model.dto;

import lombok.Getter;

import java.io.Serializable;

//@Getter
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String message;

    public MessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
