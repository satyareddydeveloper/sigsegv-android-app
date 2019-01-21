package com.sigsegv.doctor.ui.comments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseFragment;
import com.sigsegv.doctor.databinding.FragmentCommentsBinding;
import com.sigsegv.doctor.ui.doctor.DoctorViewModel;
import com.sigsegv.doctor.ui.main.MainActivity;

import javax.inject.Inject;

public class CommentsFragment extends BaseFragment<FragmentCommentsBinding, MainActivity> {

    //Inject Dagger 2 provided classes
    @Inject ViewModelProvider.Factory viewModelFactory;

    private DoctorViewModel viewModel;
    private final CommentAdapter adapter = new CommentAdapter();

    public static CommentsFragment newInstance() {
        return  new CommentsFragment();
    }

    @Override //Send layout id to super class
    protected int layoutRes() {
        return R.layout.fragment_comments;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DoctorViewModel.class);

        //Initialize recyclerView
        getBinding().rvComments.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        getBinding().rvComments.setAdapter(adapter);

        //Handle refresh action
        getBinding().swipeRefreshLayout.setOnRefreshListener(() -> viewModel.fetchComments());

        //Start observing liveData
        viewModel.getComments().observe(this, comments -> {
            if (comments != null)
                getBinding().swipeRefreshLayout.setRefreshing(false);
            adapter.submitList(comments);
        });
    }
}
