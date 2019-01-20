package com.sigsegv.doctor.ui.search;

import android.arch.lifecycle.ViewModel;

import com.sigsegv.doctor.rest.RestRepository;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    @Inject
    SearchViewModel(RestRepository restRepository) {

    }
}
