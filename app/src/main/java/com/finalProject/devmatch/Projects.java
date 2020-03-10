package com.finalProject.devmatch;


public class Projects {


    String name;
    String description;
    String position;
    String date;
    String link;


    public Projects(String name, String description, String position, String date, String link) {
        this.name = name;
        this.description = description;
        this.position = position;
        this.date = date;
        this.link = link;
    }

    public Projects(){

    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPosition() {
        return position;
    }

    public String getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLink(String link) {
        this.link = link;
    }
}