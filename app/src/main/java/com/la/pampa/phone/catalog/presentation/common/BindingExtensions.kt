package com.la.pampa.phone.catalog.presentation.common

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}
