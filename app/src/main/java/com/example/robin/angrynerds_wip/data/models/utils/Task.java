package com.example.robin.angrynerds_wip.data.models.utils;

public class Task {

    private String description;
    private boolean status;

    //Constructors
    public Task(){
        this.description = "";
        this.status = false;
    }

    public Task(String description){
        this.description = description;
        this.status = false;
    }

    public Task(String description, boolean status){
        this.description = description;
        this.status = status;
    }

    //Getters and Setters
    public String getDescription(){return description;}
    public void setDescription(String description) {this.description = description;}

    public boolean getStatus(){return status;}
    public void setStatus(boolean status){this.status = status;}
}
