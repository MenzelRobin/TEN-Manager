package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo extends TEN {

    private double progress;
    private String note;
    private Date startDate;
    private Date endDate;
    private ArrayList<Task> tasks;

    //Constructors
    public Todo(){
        super();
    }

    public Todo(String title){
        super(title);
    }

    public Todo(String title, String note){
        super(title);
        this.note = note;
    }

    public Todo(String title, String note, ArrayList<Task> tasks){
        super(title);
        this.note = note;
        this.tasks = tasks;
        this.progress = calculateProgress();
    }

    public Todo(String title, String note, ArrayList<Task> tasks, Date endDate){
        super(title);
        this.note = note;
        this.tasks = tasks;
        this.progress = calculateProgress();
        this.endDate = endDate;
    }

    public Todo(String title, String note, ArrayList<Task> tasks, Date startDate, Date endDate){
        super(title);
        this.note = note;
        this.tasks = tasks;
        this.progress = calculateProgress();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //all Attributes for complete reconstruction
    public Todo(String title, String ID, int color, int accentColor, Date dateOfCreation, String note, ArrayList<Task> tasks, Date startDate, Date endDate){
        super(title, ID, color, accentColor, dateOfCreation);
        this.note = note;
        this.tasks = tasks;
        this.progress = calculateProgress();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Bundle getBundle(){
        Bundle bundle = super.getBundle();
        bundle.putString("Note", note);
        boolean[] status = new boolean[tasks.size()];
        String[] description = new String[tasks.size()];
        int index = 0;
        for(Task task : tasks){
            description[index] = task.getDescription();
            status[index] = task.getStatus();
            index++;
        }
        bundle.putBooleanArray("Status", status);
        bundle.putStringArray("Description", description);
        return bundle;
    }

    //Getters and Setters
    public double getProgress(){return progress;}
    public String getNote(){return note;}
    public void setNote(String note){this.note = note;}
    public Date getStartDate(){return startDate;}
    public void setStartDate(Date startDate){this.startDate = startDate;}
    public Date getEndDate(){return endDate;}
    public void setEndDate(Date endDate){this.endDate = endDate;}
    public ArrayList<Task> getTasks() {return tasks;}

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        progress = calculateProgress();
    }

    public double calculateProgress(){
        int completed = 0;
        if(tasks == null)
            return 0;

        for(Task task:tasks){
            if(task.getStatus())
                completed++;
        }
        return (double)completed/tasks.size();
    }
}
