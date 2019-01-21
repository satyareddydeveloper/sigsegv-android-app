package com.sigsegv.doctor.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.databinding.RowProvinceBinding;
import com.sigsegv.doctor.rest.model.Province;

import java.util.ArrayList;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder> {

    private final AdapterCallback adapterCallback;
    private final ArrayList<Province> provinces = new ArrayList<>();

    ProvinceAdapter(AdapterCallback adapterCallback) {
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public ProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ProvinceAdapter.ProvinceViewHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.row_province, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinceAdapter.ProvinceViewHolder holder, int i) {
        holder.bind(provinces.get(i));
    }

    void submitData(ArrayList<Province> provinces) {
        this.provinces.clear();
        this.provinces.addAll(provinces);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return provinces.size();
    }

    class ProvinceViewHolder extends RecyclerView.ViewHolder {

        private RowProvinceBinding binding;
        private boolean onBind = false;

        ProvinceViewHolder(RowProvinceBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            //Handle item clicks
            itemView.setOnClickListener(v -> {
                if (!onBind) adapterCallback.onProvinceSelected(provinces.get(getAdapterPosition()));
            });
        }

        void bind(Province province) {
            onBind = true;
            binding.setProvince(province);
            onBind = false;
        }
    }

    interface AdapterCallback {
        void onProvinceSelected(Province province);
    }
}
