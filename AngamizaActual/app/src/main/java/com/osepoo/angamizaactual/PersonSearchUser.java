package com.osepoo.angamizaactual;

public class PersonSearchUser {

    private int id;
    private String name;
    private String sex;
    private String race;
    private String height;
    private String build;
    private String age;
    private String facial;
    private String clothing;
    private String voice;
    private String location;
    private String other_desc;
    private String image_desc;
    private String picture;

    public  PersonSearchUser(int id, String name, String sex, String race, String height, String build,
                             String age, String facial, String clothing, String voice, String location,
                             String other_desc, String image_desc, String picture){

        this.id=id;
        this.name=name;
        this.sex = sex;
        this.race = race;
        this.height = height;
        this.build = build;
        this.age = age;
        this.facial = facial;
        this.clothing = clothing;
        this.voice = voice;
        this.location = location;
        this.other_desc = other_desc;
        this.image_desc = image_desc;
        this.picture = picture;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFacial() {
        return facial;
    }

    public void setFacial(String facial) {
        this.facial = facial;
    }

    public String getClothing() {
        return clothing;
    }

    public void setClothing(String clothing) {
        this.clothing = clothing;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOther_desc() {
        return other_desc;
    }

    public void setOther_desc(String other_desc) {
        this.other_desc = other_desc;
    }

    public String getImage_desc() {
        return image_desc;
    }

    public void setImage_desc(String image_desc) {
        this.image_desc = image_desc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
