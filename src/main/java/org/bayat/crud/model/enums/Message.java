package org.bayat.crud.model.enums;

public enum Message {
    UNKNOWN_ERROR_IS("unknown error is"),
    DELETED_SERVICE_CALL("Delete service called"),
    GETDATA_SERVICE_CALL("GetData service called"),
    ADD_SERVICE_CALL("add service called"),
    EDIT_PHONE_SERVICE_CALL("edit phone service called"),
    REGISTER_SERVICE_CALL("register service called");
    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
