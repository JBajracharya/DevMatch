package com.finalProject.devmatch.firebase;

public class IntraDevMessaging {
    private String id;
    private String text;
    private String from;
    private String to;
    private String photoUrl;
    private String imageUrl;

    public IntraDevMessaging() {
    }

    public IntraDevMessaging(String text, String from, String to, String photoUrl, String imageUrl) {
        this.text = text;
        this.from = from;
        this.to = to;
        this.photoUrl = photoUrl;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

