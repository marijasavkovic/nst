package com.master.nst.sheard.response;

public enum ResponseStatus {
    OK(200, "OK", "success"),
    BAD_REQUEST(400, "Bad Request", "error"),
    NOT_FOUND(404, "Not Found", "error"),
    UNAUTHORIZED(401, "Unauthorized", "error"),
    FORBIDDEN(403, "Forbidden", "error"),
    METHOD_NOT_ALLOWED(405, "Method not allowed", "error"),
    NOT_ACCEPTABLE(406, "Not Acceptable", "error"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type", "error"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "fail");

    private final int code;
    private final String reason;
    private final String type;

    ResponseStatus(int code, String reason, String type) {
        this.code = code;
        this.reason = reason;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    public String getType() {
        return type;
    }

}
