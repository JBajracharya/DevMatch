package com.finalProject.devmatch.models;

import java.util.ArrayList;

public class Developer {

    private enum Type {

        Front_End,
        Back_end,
        Full_Stack,
    }
    private String name;
    private String github;
    private String email;
    private SkillSet skills;

    private ArrayList<Projects> currentProjects;
    private ArrayList<Projects> portfolio;

    public Developer(String name, String github, String email) {
        this.name = name;
        this.github = github;
        this.email = email;
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
}
