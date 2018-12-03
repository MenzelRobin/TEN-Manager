package com.example.robin.angrynerds_wip.data.models.tens;

import com.example.robin.angrynerds_wip.data.models.utils.Task;

public class Todo extends TEN {

    private double progress;
    private String note;
    private Task[] tasks;

    //Constructors
    public Todo(String title){
        super(title);
        tasks = new Task[]{new Task("",false)};
        progress = calculateProgress();
    }

    public Todo(String title, String note){
        super(title);
        this.note = note;
        tasks = new Task[]{new Task("",false)};
    }

    public Todo(String title, String note, Task[] tasks){
        super(title);
        this.note = note;
        this.tasks = tasks;
        this.progress = calculateProgress();
    }

    public Todo(String title, int color, String note, Task[] tasks){
        super(title, color);
        this.note = note;
        this.tasks = tasks;
        this.progress = calculateProgress();
    }

    //Getters and Setters
    public double getProgress(){return progress;}

    public String getNote(){return note;}
    public void setNote(String note){this.note = note;}

    public Task[] getTasks() {return tasks;}
    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
        progress = calculateProgress();
    }

    public double calculateProgress(){
        int completed = 0;

        if(tasks == null)
            return 0;

        for(Task task:tasks){
            if(task.getStatus() == true)
                completed++;
        }
        return (double)completed/tasks.length;
    }
}
