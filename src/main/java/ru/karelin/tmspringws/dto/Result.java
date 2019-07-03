package ru.karelin.tmspringws.dto;

public class Result {
    private boolean success;
    private String message;
    public Result(){
        this.success=true;
    }
    public Result(boolean isSuccess){
        this.success=isSuccess;
    }
    public Result (boolean isSuccess, String message){
        this.success = isSuccess;
        this.message = message;
    }


    //getters and setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
