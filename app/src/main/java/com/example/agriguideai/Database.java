package com.example.agriguideai;
public class Database {
    String name, soilprep, sowing, irrigation, fertilization, harvesting, img;

    Database(){

    }
    Database(String name,String img){
        this.name = name;
        this.img = img;
    }
    public Database(String name, String soilprep, String sowing, String irrigation, String fertilization, String harvesting, String img) {
        this.name = name;
        this.soilprep = soilprep;
        this.sowing = sowing;
        this.irrigation = irrigation;
        this.fertilization = fertilization;
        this.harvesting = harvesting;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoilprep() {
        return soilprep;
    }

    public void setSoilprep(String soilprep) {
        this.soilprep = soilprep;
    }

    public String getSowing() {
        return sowing;
    }

    public void setSowing(String sowing) {
        this.sowing = sowing;
    }

    public String getIrrigation() {
        return irrigation;
    }

    public void setIrrigation(String irrigation) {
        this.irrigation = irrigation;
    }

    public String getFertilization() {
        return fertilization;
    }

    public void setFertilization(String fertilization) {
        this.fertilization = fertilization;
    }

    public String getHarvesting() {
        return harvesting;
    }

    public void setHarvesting(String harvesting) {
        this.harvesting = harvesting;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}