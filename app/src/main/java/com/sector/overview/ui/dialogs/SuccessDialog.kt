package com.sector.overview.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sector.overview.databinding.SuccessDialogBinding
import com.sector.ui.utils.setLayoutWidth

class SuccessDialog(
    private val onCloseDialog: () -> Unit
): DialogFragment() {

    private val viewBinding: SuccessDialogBinding by viewBinding(SuccessDialogBinding::bind)

    override fun onStart() {
        super.onStart()

        dialog?.setLayoutWidth(width = 90)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnGoBackToMovie.setOnClickListener {
            dismiss()
            onCloseDialog.invoke()
        }
    }

    companion object {
        const val SUCCESS_DIALOG_TAG = "SUCCESS_DIALOG_TAG"
    }
}