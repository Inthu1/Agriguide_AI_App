package com.example.agriguideai.Agriconnect;

public class HelperClass {

    String name, user, username, password;
    String fname, phoneno, plant_name, job_type, location;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setEmail(String email) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String name, String user, String username, String password) {
        this.name = name;
        this.user = user;
        this.username = username;
        this.password = password;
    }

    public HelperClass(String fname, String phoneno, String plant_name, String job_type, String location) {
        this.fname = fname;
        this.phoneno = phoneno;
        this.plant_name = plant_name;
        this.job_type = job_type;
        this.location = location;
    }

    public HelperClass() {
    }
}