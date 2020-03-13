package com.finalProject.devmatch.models;


import android.os.Environment;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.internal.platform.Platform;

public class Projects {


    private String id;
    private String name;
    private String description;
    private String owner;
    private Language language;
    private Database dataBase;
    private Environment environment;
    private Platform platform;
    private String date;
    private String link;


    private ArrayList<String> devRequests;
    private ArrayList<String> developers;


    public Projects(String name, String description, String date, String link) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.link = link;
    }


    private enum Language {

        java,
        javascript,
        python,
        csharp,
        cplusplus,
        ruby,
        dotNet,
        sql,
        html,
        css,

    }


    public void setLanguage(String language){
        switch (language){
            case "Java":
                this.language =Language.java;
                break;
            case "JavaScript":
                this.language = Language.javascript;
                break;
            case "Python":
                this.language = Language.python;
                break;
            case "Csharp":
                this.language = Language.csharp;
                break;
            case "Cplusplus":
                this.language = Language.cplusplus;
                break;
            case "Ruby":
                this.language = Language.ruby;
                break;
            case "dotNet":
                this.language = Language.dotNet;
                break;
            case "sql":
                this.language = Language.sql;
                break;
            case "html":
                this.language = Language.html;
                break;
            case "css":
                this.language = Language.css;
                break;

        }

    }

    private enum Database {

        postgresql,
        mysql,
        mongoDB,
        dynamoDB,
    }

    public void setDatabase(String database){
        switch (database){
            case "Postgresql":
                this.dataBase =Database.postgresql;
                break;
            case "Mysql":
                this.dataBase =Database.mysql;
                break;
            case "MongoDB":
                this.dataBase =Database.mongoDB;
                break;
            case "DynamoDB":
                this.dataBase =Database.dynamoDB;
                break;

        }

    }
    private enum Environment {
        aws,
        heroku,
        firebase,
        azure,
    }

    public void setEnvironment(String environment){
        switch (environment){
            case "AWS":
                this.environment =Environment.aws;
                break;
            case "Heroku":
                this.environment =Environment.heroku;
                break;
            case "Firebase":
                this.environment =Environment.firebase;
                break;
            case "Azure":
                this.environment =Environment.azure;
                break;
        }

    }

    private enum Platform {
         iOS,
         android,
         linux,
        web,
        react,




    }

    public void setPlatform(String platform){
        switch (platform){
            case "IOS":
                this.platform =Platform.iOS;
                break;
            case "Android":
                this.platform =Platform.android;
                break;
            case "Linux":
                this.platform =Platform.linux;
                break;
            case "Web":
                this.platform =Platform.web;
                break;

            case "React":
                this.platform =Platform.react;
                break;


        }

    }

    public Projects(){

    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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


    public void setDate(String date) {
        this.date = date;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        switch (language) {
            case java:
                return "Java";
            case javascript:
                return "JavaScript";
            case python:
                return "Python";
            case csharp:
                return "C#";
            case cplusplus:
                return "C++";
            case ruby:
                return "Ruby";
            case dotNet:
                return ".NET";
            case sql:
                return "SQL";
            case html:
                return "HTML";
            case css:
                return "CSS";
        }
        return "None";
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public ArrayList<String> getDevelopers() {
        return developers;
    }

    public void setDevelopers(ArrayList<String> developers) {
        this.developers = developers;
    }

    public String getDataBase() {
        switch (dataBase) {
            case postgresql:
                return "Postgresql";
            case mysql:
                return "MySQL";
            case mongoDB:
                return "MongoDB";
            case dynamoDB:
                return "DynamoDB";
        }
        return "None";
    }

    public void setDataBase(Database dataBase) {
        this.dataBase = dataBase;
    }
    public String getEnvironment() {
        switch (environment) {
            case aws:
                return "AWS";
            case heroku:
                return "Heroku";
            case firebase:
                return "Firebase";
            case azure:
                return "Azure";
        }
        return "None";
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getPlatform() {
        switch (platform) {
            case iOS:
                return "iOS";
            case android:
                return "Android";
            case linux:
                return "Linux";
            case web:
                return "Web";
            case react:
                return "React";
        }
        return "None";
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getDevRequests() {
        return devRequests;
    }

    public void setDevRequests(ArrayList<String> devRequests) {
        this.devRequests = devRequests;
    }

}