package com.finalProject.devmatch.models;

public class SkillSet {

    private Language language;
    private DataStructuresAndAlgorithms dataStructuresAndAlgorithms;
    private Database database;
    private Cloud cloud;
    private Platforms platforms;

    public SkillSet(){
        this.language = new Language();
        this.dataStructuresAndAlgorithms = new DataStructuresAndAlgorithms();
        this.database = new Database();
        this.cloud = new Cloud();
        this.platforms = new Platforms();
    }

    public class Language {

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

        private Language(){

            this.java = false;
            this.python = false;
            this.cSharp = false;
            this.cplusplus = false;
            this.ruby = false;
            this.dotNet = false;
            this.javascript = false;
            this.sql = false;
            this.html = false;
            this.css = false;
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
    }

    public class DataStructuresAndAlgorithms {

        private boolean arrays;
        private boolean linkedlists;
        private boolean stacks;
        private boolean queues;
        private boolean trees;
        private boolean hashes;
        private boolean heaps;
        private boolean sets;

        private DataStructuresAndAlgorithms(){
            this.arrays = false;
            this.linkedlists = false;
            this.stacks = false;
            this.queues = false;
            this.trees = false;
            this.hashes = false;
            this.heaps = false;
            this.sets = false;
        }

        public boolean isArrays() {
            return arrays;
        }

        public void setArrays(boolean arrays) {
            this.arrays = arrays;
        }

        public boolean isLinkedlists() {
            return linkedlists;
        }

        public void setLinkedlists(boolean linkedlists) {
            this.linkedlists = linkedlists;
        }

        public boolean isStacks() {
            return stacks;
        }

        public void setStacks(boolean stacks) {
            this.stacks = stacks;
        }

        public boolean isQueues() {
            return queues;
        }

        public void setQueues(boolean queues) {
            this.queues = queues;
        }

        public boolean isTrees() {
            return trees;
        }

        public void setTrees(boolean trees) {
            this.trees = trees;
        }

        public boolean isHashes() {
            return hashes;
        }

        public void setHashes(boolean hashes) {
            this.hashes = hashes;
        }

        public boolean isHeaps() {
            return heaps;
        }

        public void setHeaps(boolean heaps) {
            this.heaps = heaps;
        }

        public boolean isSets() {
            return sets;
        }

        public void setSets(boolean sets) {
            this.sets = sets;
        }
    }

    public class Database {

        private boolean postgresql;
        private boolean mysql;
        private boolean mongoDB;
        private boolean dynamoDB;

        private Database(){
            this.postgresql = false;
            this.mysql = false;
            this.mongoDB = false;
            this.dynamoDB = false;
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
    }

    public class Cloud {

        private boolean AWS;
        private boolean heroku;
        private boolean firebase;
        private boolean azure;

        private Cloud(){
            this.AWS = false;
            this.heroku = false;
            this.firebase = false;
            this.azure = false;
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
    }

    public class Platforms {

        private boolean iOS;
        private boolean android;
        private boolean linux;
        private boolean web;
        private boolean react;

        private Platforms(){
            this.iOS = false;
            this.android = false;
            this.linux = false;
            this.web = false;
            this.react = false;
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
    }

    // GETTERS AND SETTERS FOR SKILLSET CLASS

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public DataStructuresAndAlgorithms getDataStructuresAndAlgorithms() {
        return dataStructuresAndAlgorithms;
    }

    public void setDataStructuresAndAlgorithms(DataStructuresAndAlgorithms dataStructuresAndAlgorithms) {
        this.dataStructuresAndAlgorithms = dataStructuresAndAlgorithms;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }

    public Platforms getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Platforms platforms) {
        this.platforms = platforms;
    }
}
