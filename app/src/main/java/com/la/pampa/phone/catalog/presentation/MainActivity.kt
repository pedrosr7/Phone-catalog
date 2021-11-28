package com.la.pampa.phone.catalog.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.la.pampa.phone.catalog.R
import com.la.pampa.phone.catalog.presentation.form.FormDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val viewModel by viewModel<PhoneViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getPhones()

        val dialog = FormDialogFragment()
        dialog.show(supportFragmentManager, "")
    }

}