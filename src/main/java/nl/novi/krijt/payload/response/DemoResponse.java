package nl.novi.krijt.payload.response;

import nl.novi.krijt.domain.User;

public class DemoResponse {

    private String message;

    public DemoResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
