package com.familyfirstsoftware.ServerTestBackend.enums;

public enum Status {
    SERVER_UP("SERVER_UP"),
    SEVER_DOWN("SERVER_DOWN");

    private final String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
