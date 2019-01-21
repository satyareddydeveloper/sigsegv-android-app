package com.sigsegv.doctor.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.rest.model.Province;

import java.util.ArrayList;

public class ProvinceSelectDialog extends BottomSheetDialogFragment implements ProvinceAdapter.AdapterCallback {

    private DialogCallback dialogCallback;
    private final ProvinceAdapter provinceAdapter = new ProvinceAdapter(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_province_picker, container, false);
        final RecyclerView rvProvince = view.findViewById(R.id.rvProvince);

        rvProvince.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProvince.setAdapter(provinceAdapter);

        return view;
    }

    @Override
    public void onProvinceSelected(Province province) {
        dismiss();
        if (dialogCallback != null)
            dialogCallback.onProvinceSelected(province);
    }

    public void submitData(ArrayList<Province> provinces) {
        provinceAdapter.submitData(provinces);
    }

    public void setDialogCallback(DialogCallback dialogCallback) {
        this.dialogCallback = dialogCallback;
    }

    interface DialogCallback {
        void onProvinceSelected(Province province);
    }
}