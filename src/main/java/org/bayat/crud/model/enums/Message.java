package org.bayat.crud.model.enums;

public enum Message {
    UNKNOWN_ERROR_IS("unknown error is"),
    DELETED_SERVICE_CALL("Delete service called"),
    GETDATA_SERVICE_CALL("GetData service called"),
    Edit_SERVICE_CALL("edit service called"),
    EDIT_PHONE_SERVICE_CALL("edit phone service called"),
    REGISTER_SERVICE_CALL("register service called"),
    DATA_NOT_FOUND("Data not found with id:"),
    DELETE_USER("کاربر با آیدی مورد نظر پاک شد"),
    SUCCESSFUL("موفق"),
    USER_ERROR("خطای کاربر"),
    FIND_USER("کاربر مورد نظر یافت شد"),
    USER_REGISTERED("کاربر مورد نظر ثبت شد"),
    UPDATE_USER("شماره تلفن بروزرسانی شد");
    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
