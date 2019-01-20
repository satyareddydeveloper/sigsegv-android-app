package com.sigsegv.doctor.rest.model;

import java.util.List;

public class PagedResponse<T> {

    private int count;
    private List<T> results;

    public int getCount() {
        return count;
    }

    public List<T> getResults() {
        return results;
    }
}
