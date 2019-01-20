package com.sigsegv.doctor.ui.doctor;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.databinding.RowCommentBinding;
import com.sigsegv.doctor.rest.model.Comment;

public class CommentAdapter extends PagedListAdapter<Comment, CommentAdapter.DoctorViewHolder> {

    CommentAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DoctorViewHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.row_comment, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int i) {
        holder.bind(getItem(i));
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder {

        private final RowCommentBinding binding;

        DoctorViewHolder(RowCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Comment comment) {
            binding.setComment(comment);
        }
    }

    private static DiffUtil.ItemCallback<Comment> DIFF_CALLBACK = new DiffUtil.ItemCallback<Comment>() {

        @Override
        public boolean areItemsTheSame(@NonNull Comment oldItem, @NonNull Comment newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Comment oldItem, @NonNull Comment newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };
}
