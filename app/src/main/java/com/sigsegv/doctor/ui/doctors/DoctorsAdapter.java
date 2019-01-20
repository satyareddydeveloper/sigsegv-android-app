package com.sigsegv.doctor.ui.doctors;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.databinding.RowDoctorBinding;
import com.sigsegv.doctor.rest.model.Doctor;

public class DoctorsAdapter extends PagedListAdapter<Doctor, DoctorsAdapter.DoctorViewHolder> {

    private final AdapterCallback adapterCallback;

    DoctorsAdapter(AdapterCallback adapterCallback) {
        super(DIFF_CALLBACK);
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DoctorViewHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.row_doctor, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int i) {
        holder.bind(getItem(i));
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder {

        private final RowDoctorBinding binding;

        DoctorViewHolder(RowDoctorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            //Handle item clicks
            itemView.setOnClickListener(v -> adapterCallback.onDoctorSelected(getItem(getAdapterPosition())));
        }

        void bind(Doctor doctor) {
            binding.setDoctor(doctor);
        }
    }

    private static DiffUtil.ItemCallback<Doctor> DIFF_CALLBACK = new DiffUtil.ItemCallback<Doctor>() {

        @Override
        public boolean areItemsTheSame(@NonNull Doctor oldItem, @NonNull Doctor newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Doctor oldItem, @NonNull Doctor newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };

    interface AdapterCallback {
        void onDoctorSelected(Doctor doctor);
    }
}
