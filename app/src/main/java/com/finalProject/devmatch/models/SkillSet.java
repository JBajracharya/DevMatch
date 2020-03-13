package com.finalProject.devmatch.models;

public class SkillSet {

        private String id;
        private boolean java;
        private boolean python;
        private boolean cSharp;
        private boolean cplusplus;
        private boolean ruby;
        private boolean dotNet;
        private boolean javascript;
        private boolean sql;
        private boolean html;
        private boolean css;
        private boolean postgresql;
        private boolean mysql;
        private boolean mongoDB;
        private boolean dynamoDB;
        private boolean AWS;
        private boolean heroku;
        private boolean firebase;
        private boolean azure;
        private boolean iOS;
        private boolean android;
        private boolean linux;
        private boolean web;
        private boolean react;

        public SkillSet(){

        }

    public SkillSet(String id, boolean java, boolean python, boolean cSharp, boolean cplusplus, boolean ruby, boolean dotNet, boolean javascript, boolean sql, boolean html, boolean css, boolean postgresql, boolean mysql, boolean mongoDB, boolean dynamoDB, boolean AWS, boolean heroku, boolean firebase, boolean azure, boolean iOS, boolean android, boolean linux, boolean web, boolean react) {
        this.id = id;
        this.java = java;
        this.python = python;
        this.cSharp = cSharp;
        this.cplusplus = cplusplus;
        this.ruby = ruby;
        this.dotNet = dotNet;
        this.javascript = javascript;
        this.sql = sql;
        this.html = html;
        this.css = css;
        this.postgresql = postgresql;
        this.mysql = mysql;
        this.mongoDB = mongoDB;
        this.dynamoDB = dynamoDB;
        this.AWS = AWS;
        this.heroku = heroku;
        this.firebase = firebase;
        this.azure = azure;
        this.iOS = iOS;
        this.android = android;
        this.linux = linux;
        this.web = web;
        this.react = react;
    }
    public boolean isJava() {
        return java;
    }

    public void setJava(boolean java) {
        this.java = java;
    }

    public boolean isPython() {
        return python;
    }

    public void setPython(boolean python) {
        this.python = python;
    }

    public boolean iscSharp() {
        return cSharp;
    }

    public void setcSharp(boolean cSharp) {
        this.cSharp = cSharp;
    }

    public boolean isCplusplus() {
        return cplusplus;
    }

    public void setCplusplus(boolean cplusplus) {
        this.cplusplus = cplusplus;
    }

    public boolean isRuby() {
        return ruby;
    }

    public void setRuby(boolean ruby) {
        this.ruby = ruby;
    }

    public boolean isDotNet() {
        return dotNet;
    }

    public void setDotNet(boolean dotNet) {
        this.dotNet = dotNet;
    }

    public boolean isJavascript() {
        return javascript;
    }

    public void setJavascript(boolean javascript) {
        this.javascript = javascript;
    }

    public boolean isSql() {
        return sql;
    }

    public void setSql(boolean sql) {
        this.sql = sql;
    }

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public boolean isCss() {
        return css;
    }

    public void setCss(boolean css) {
        this.css = css;
    }

    public boolean isPostgresql() {
        return postgresql;
    }

    public void setPostgresql(boolean postgresql) {
        this.postgresql = postgresql;
    }

    public boolean isMysql() {
        return mysql;
    }

    public void setMysql(boolean mysql) {
        this.mysql = mysql;
    }

    public boolean isMongoDB() {
        return mongoDB;
    }

    public void setMongoDB(boolean mongoDB) {
        this.mongoDB = mongoDB;
    }

    public boolean isDynamoDB() {
        return dynamoDB;
    }

    public void setDynamoDB(boolean dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    public boolean isAWS() {
        return AWS;
    }

    public void setAWS(boolean AWS) {
        this.AWS = AWS;
    }

    public boolean isHeroku() {
        return heroku;
    }

    public void setHeroku(boolean heroku) {
        this.heroku = heroku;
    }

    public boolean isFirebase() {
        return firebase;
    }

    public void setFirebase(boolean firebase) {
        this.firebase = firebase;
    }

    public boolean isAzure() {
        return azure;
    }

    public void setAzure(boolean azure) {
        this.azure = azure;
    }

    public boolean isiOS() {
        return iOS;
    }

    public void setiOS(boolean iOS) {
        this.iOS = iOS;
    }

    public boolean isAndroid() {
        return android;
    }

    public void setAndroid(boolean android) {
        this.android = android;
    }

    public boolean isLinux() {
        return linux;
    }

    public void setLinux(boolean linux) {
        this.linux = linux;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public boolean isReact() {
        return react;
    }

    public void setReact(boolean react) {
        this.react = react;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
