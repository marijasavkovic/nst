package com.master.nst.sheard.response;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Response<T> implements Serializable{
    private T data;
    private ResponseStatus status = ResponseStatus.OK;
    private List<Error> errors;

    public Response(T data) {
        this.data = data;
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

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(final List<Error> errors) {
        this.errors = errors;
    }

}
