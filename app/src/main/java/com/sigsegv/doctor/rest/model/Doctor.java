package com.sigsegv.doctor.rest.model;

public class Doctor {

    private int id;
    private String name;
    private Clinic clinic;
    private Hospital hospital;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public Hospital getHospital() {
        return hospital;
    }
}
