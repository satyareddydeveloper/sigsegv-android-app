package com.sigsegv.doctor.rest.model;

public class Comment {

    private int id;
    private User owner;
    private String comment;
    private Doctor doctor;
    private int rating;

    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getComment() {
        return comment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public int getRating() {
        return rating;
    }
}
