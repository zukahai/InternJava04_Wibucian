package com.java04.wibucian.models;

public class Message {
    boolean check;
    String message;
    String value;

    public Message(boolean check, String message, String value) {
        this.check = check;
        this.message = message;
        this.value = value;
    }
    public Message(){
        super();
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "check=" + check +
                ", message='" + message + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
