package com.ozhegov.tennis.model;

import java.io.Serializable;

//@Getter
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
