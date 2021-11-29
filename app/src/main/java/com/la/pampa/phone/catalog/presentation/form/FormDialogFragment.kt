package com.la.pampa.phone.catalog.presentation.form

import android.app.Activity
import android.app.Dialog
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Base64
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.la.pampa.phone.catalog.R
import com.la.pampa.phone.catalog.core.extensions.observe
import com.la.pampa.phone.catalog.databinding.FormFragmentBinding
import com.la.pampa.phone.catalog.presentation.FormPhoneViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.util.Base64.getEncoder

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


        binding.phoneImageView.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            getGalleryImage.launch(gallery)
        }
        binding.addButton.setOnClickListener {
            viewModel.savePhone()
        }


        observe(viewModel.dismiss) { dismiss()}

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

    private val getGalleryImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {

        when(it.resultCode){
            Activity.RESULT_OK -> {
                val imageUri = it.data?.data ?: return@registerForActivityResult

                binding.phoneImageView.setImageURI(imageUri)
                try {
                    val contentResolver: ContentResolver = requireContext().contentResolver

                    imageUri.let { returnUri ->
                        contentResolver.query(returnUri, null, null, null, null)
                    }?.use { cursor ->
                        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        cursor.moveToFirst()
                        viewModel.photoName = cursor.getString(nameIndex)
                    }

                    val result = imageUri.let { returnUri ->
                        contentResolver.openInputStream(returnUri)
                            .use { stream -> stream?.readBytes() }
                    }

                    viewModel.photoBase64 = Base64.encodeToString(result, Base64.NO_WRAP)


                    val inputStream = imageUri.let { returnUri ->
                        contentResolver.openInputStream(returnUri)
                    }

                    if (inputStream != null) {
                        viewModel.photoUri = saveFile(inputStream).toString()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }

    private fun saveFile(inputStream: InputStream): Uri {
        val imageFile = File(
            requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
            viewModel.photoName ?: "imageRandom"
        )
        val outputStream: OutputStream?

        val fileReader = ByteArray(4096)
        if(!imageFile.exists()){
            imageFile.createNewFile()
        }
        outputStream = FileOutputStream(imageFile)
        var read: Int
        while (inputStream.read(fileReader).also { read = it } != -1) {
            outputStream.write(fileReader, 0, read)
        }
        outputStream.flush()

       return FileProvider.getUriForFile(
            requireContext(),
            requireContext().packageName + ".provider",
           imageFile
        )
    }
}