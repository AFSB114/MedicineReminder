package com.sena.medicinereminder.DTO;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseDTO {
    private HttpStatus status;
    private String message;
    private boolean ok;
    private List<Object> data;
    private String Errors;

    public ResponseDTO() {
    }

    public ResponseDTO(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.ok = status.is2xxSuccessful();
    }

    public ResponseDTO(HttpStatus status, String message, boolean ok, List<Object> data) {
        this.status = status;
        this.message = message;
        this.ok = status.is2xxSuccessful();
        this.data = data;
    }

    public ResponseDTO(HttpStatus status, String message, String Errors) {
        this.status = status;
        this.message = message;
        this.ok = status.is2xxSuccessful();
        this.Errors = Errors;
    }

    static public ResponseDTO ok(String message) {return new ResponseDTO(HttpStatus.OK, message);}

    static public ResponseDTO error(String message) {return new ResponseDTO(HttpStatus.BAD_REQUEST, message);}

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public String getErrors() {
        return Errors;
    }

    public void setErrors(String errors) {
        Errors = errors;
    }
}
