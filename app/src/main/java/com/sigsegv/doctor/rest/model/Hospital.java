package com.sigsegv.doctor.rest.model;

public class Hospital {

    private int id;
    private String title;
    private County county;
    private int rating;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public County getCounty() {
        return county;
    }

    public int getRating() {
        return rating;
    }
}
