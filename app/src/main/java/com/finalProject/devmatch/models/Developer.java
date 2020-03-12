package com.finalProject.devmatch.models;

import java.util.ArrayList;

public class Developer {

    private String id;
    private String username;
    private String name;
    private String github;
    private String email;
    private SkillSet skills;
    private Type type;

    private ArrayList<Projects> currentProjects;
    private ArrayList<Projects> portfolio;

    public Developer(String id, String username, String name, String github, String email, SkillSet skills, Type type, ArrayList<Projects> currentProjects, ArrayList<Projects> portfolio) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.github = github;
        this.email = email;
        this.skills = skills;
        this.type = type;
        this.currentProjects = currentProjects;
        this.portfolio = portfolio;
    }

    public Developer(String id, String username, String name, String github, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.github = github;
        this.email = email;
    }


    private enum Type {

        Front_End,
        Back_end,
        Full_Stack,
    }

    public Developer(String name, String github, String email) {
        this.name = name;
        this.github = github;
        this.email = email;
    }
    public Developer(){

    }
    public void setType(String type){
        switch (type){
            case "Front End":
                this.type = Type.Front_End;
                break;
            case "Back End":
                this.type = Type.Back_end;
                break;
            case "Full Stack":
                this.type = Type.Full_Stack;
                break;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SkillSet getSkills() {
        return skills;
    }

    public void setSkills(SkillSet skills) {
        this.skills = skills;
    }

    public ArrayList<Projects> getCurrentProjects() {
        return currentProjects;
    }

    public void setCurrentProjects(ArrayList<Projects> currentProjects) {
        this.currentProjects = currentProjects;
    }

    public ArrayList<Projects> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(ArrayList<Projects> portfolio) {
        this.portfolio = portfolio;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
