package com.osepoo.angamizaactual;

public class TasksModel {

    private  int id;
    private String signinname,empid,task,priority,date_created;

    public TasksModel(int id, String signinname, String empid,String task, String priority, String date_created) {
        this.id=id;
        this.signinname=signinname;
        this.empid=empid;
        this.priority=priority;
        this.task=task;
        this.date_created=date_created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSigninname() {
        return signinname;
    }

    public void setSigninname(String signinname) {
        this.signinname = signinname;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
