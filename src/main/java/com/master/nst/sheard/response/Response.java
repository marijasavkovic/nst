package com.master.nst.sheard.response;

import com.master.nst.sheard.errors.Error;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Response<T> implements Serializable {
    private T data;
    private ResponseStatus status = ResponseStatus.OK;
    private String message;
    private List<Error> errors;

    public Response(T data) {
        this.data = data;
    }

    public Response(final T data, final ResponseStatus status, final String message, final List<Error> errors) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public Response(T data, ResponseStatus status, List<Error> errors) {
        this.data = data;
        this.status = status;
        this.errors = errors;
    }

    public Response(ResponseStatus status) {
        this.status = status;
    }

    public Response(ResponseStatus status, List<Error> errors) {
        this.status = status;
        this.errors = errors;
    }

    public Response(Throwable throwable) {
        status = ResponseStatus.INTERNAL_SERVER_ERROR;
        message = throwable.getMessage();
    }

    public Response(ResponseStatus status, Throwable throwable) {
        this.status = status;
        this.message = throwable.getMessage();
    }

    public Response(ResponseStatus status, Error error) {
        this.status = status;
        this.errors = Collections.singletonList(error);
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

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(final List<Error> errors) {
        this.errors = errors;
    }
}
