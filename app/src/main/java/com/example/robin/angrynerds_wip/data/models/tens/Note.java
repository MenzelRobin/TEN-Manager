package com.example.robin.angrynerds_wip.data.models.tens;

public class Note extends TEN{

    private String description;

    //Constructors
    public Note(String title){
        super(title);
    }

    public Note(String title, String description){
        super(title);
        this.description = description;
    }

    public Note(String title, int color, String description){
        super(title, color);
        this.description = description;
    }

    //Getter and Setter

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
