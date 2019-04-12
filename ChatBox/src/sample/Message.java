package sample;

import java.io.Serializable;

public class Message implements Serializable {
    protected static final long serialVersionUID = 1112122200L;

    public static final int WHOIS = 0, MESSAGE = 1, LOGOUT = 2;
    private int type;
    private String message;

    // constructor
    public Message(int type, String message) {
        this.type = type;
        this.message = message;
    }

    // getters
    public int getType() {
        return type;
    }
    public String getMessage() {
        return message;
    }
}
