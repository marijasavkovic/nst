package com.master.nst.sheard.response;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Response<T> implements Serializable{
    private T data;
    private ResponseStatus status = ResponseStatus.OK;
    private String message;

    public Response(T data) {
        this.data = data;
    }

    public Response(T data, ResponseStatus status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public Response(ResponseStatus status) {
        this.status = status;
    }

    public Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(Throwable throwable) {
        status = ResponseStatus.INTERNAL_SERVER_ERROR;
        message = throwable.getMessage();
    }

    public Response(ResponseStatus status, Throwable throwable) {
        this.status = status;
        this.message = throwable.getMessage();
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(final ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
