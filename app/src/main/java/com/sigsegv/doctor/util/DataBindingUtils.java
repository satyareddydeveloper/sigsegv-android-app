package com.sigsegv.doctor.util;

import android.databinding.BindingAdapter;
import android.view.View;

public class DataBindingUtils {

    @BindingAdapter("android:visible")
    public static void setVisibility(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }
}
