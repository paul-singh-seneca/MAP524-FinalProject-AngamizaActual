package com.osepoo.angamizaactual;

public class TasksLeaderModel {


    private  int id;
    private String name,precinct,photo;

    public TasksLeaderModel(int id, String name, String precinct, String photo) {
        this.id=id;
        this.name=name;
        this.precinct=precinct;
        this.photo=photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrecinct() {
        return precinct;
    }

    public void setPrecinct(String precinct) {
        this.precinct = precinct;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
