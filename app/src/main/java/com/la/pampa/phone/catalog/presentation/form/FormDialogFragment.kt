package com.la.pampa.phone.catalog.presentation.form

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.la.pampa.phone.catalog.R
import com.la.pampa.phone.catalog.core.extensions.observe
import com.la.pampa.phone.catalog.databinding.FormFragmentBinding
import com.la.pampa.phone.catalog.presentation.FormPhoneViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormDialogFragment: BottomSheetDialogFragment() {

    private val viewModel by viewModel<FormPhoneViewModel>()

    private lateinit var binding: FormFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.form_fragment,
            container,
            false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { layout ->
                layout.setBackgroundColor(Color.TRANSPARENT)
                val behaviour = BottomSheetBehavior.from(layout)
                setupFullHeight(layout)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            viewModel.savePhone()
        }

        observe(viewModel.showLoading) {

        }

        observe(viewModel.showError) {

        }
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }
}