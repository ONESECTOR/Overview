package com.sector.overview.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sector.overview.databinding.SuccessDialogBinding
import com.sector.ui.utils.setLayoutWidth

class SuccessDialog(
    private val onCloseDialog: () -> Unit
): DialogFragment() {

    private var binding: SuccessDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SuccessDialogBinding.inflate(layoutInflater)
        return binding?.rootView
    }

    override fun onStart() {
        super.onStart()

        dialog?.setLayoutWidth(width = 90)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnGoBackToMovie?.setOnClickListener {
            dismiss()
            onCloseDialog.invoke()
        }
    }

    companion object {
        const val SUCCESS_DIALOG_TAG = "SUCCESS_DIALOG_TAG"
    }
}