package com.osepoo.angamizaactual;


public class ReturningVisitorUser {

    private int id;

    private String vehicle_plate;
    private String vin;
    private String model;
    private String vehicle_make;
    private String vehicle_desc;
    private String theft_date;
    private String theft_details;
    private String status;
    private String image;
    private String image_desc;

    private String date_logged;

    public ReturningVisitorUser(int id, String vehicle_plate,String vin, String model,
                                String vehicle_make, String vehicle_desc, String theft_date, String theft_details,
                                String status, String image, String image_desc, String date_logged) {
        this.id = id;

        this.vehicle_plate = vehicle_plate;
        this.vin = vin;
        this.model = model;
        this.vehicle_make = vehicle_make;
       // this.id_no = id_no;
        this.vehicle_desc = vehicle_desc;
        this.theft_date = theft_date;
        this.theft_details = theft_details;
        this.status = status;
        this.image = image;
        this.image_desc = image_desc;
        this.date_logged = date_logged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getDate_logged() {
        return date_logged;
    }

    public void setDate_logged(String date_logged) {
        this.date_logged = date_logged;
    }


    public String getVehicle_plate() {
        return vehicle_plate;
    }

    public void setVehicle_plate(String vehicle_plate) {
        this.vehicle_plate = vehicle_plate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicle_make() {
        return vehicle_make;
    }

    public void setVehicle_make(String vehicle_make) {
        this.vehicle_make = vehicle_make;
    }


    public String getVehicle_desc() {
        return vehicle_desc;
    }

    public void setVehicle_desc(String vehicle_desc) {
        this.vehicle_desc = vehicle_desc;
    }

    public String getTheft_date() {
        return theft_date;
    }

    public void setTheft_date(String theft_date) {
        this.theft_date = theft_date;
    }

    public String getTheft_details() {
        return theft_details;
    }

    public void setTheft_details(String theft_details) {
        this.theft_details = theft_details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_desc() {
        return image_desc;
    }

    public void setImage_desc(String image_desc) {
        this.image_desc = image_desc;
    }
}
