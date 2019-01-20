package com.sigsegv.doctor.rest.model;

public class Comment {

    private int id;
    private String owner;
    private String comment;
    private Doctor doctor;

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getComment() {
        return comment;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
