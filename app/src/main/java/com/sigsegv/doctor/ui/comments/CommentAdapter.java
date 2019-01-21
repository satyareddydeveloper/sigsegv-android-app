package com.sigsegv.doctor.ui.comments;

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

public class CommentAdapter extends PagedListAdapter<Comment, CommentAdapter.CommentViewHolder> {

    CommentAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CommentViewHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.row_comment, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int i) {
        holder.bind(getItem(i));
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        private final RowCommentBinding binding;

        CommentViewHolder(RowCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Comment comment) {
            binding.setComment(comment);
            binding.rating.setMax(5);
            binding.rating.setRating(comment.getRating());
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
