package kh.edu.rupp.ite.trendy.UI.Auth

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kh.edu.rupp.ite.trendy.R

class LoginBottomSheetFragment(private val context: Context):BottomSheetDialogFragment() {
    private var btnClose : ImageView?=null
    private var btnRegister: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_login, container, false)
        btnClose = view.findViewById(R.id.btn_close)
        btnRegister = view.findViewById(R.id.btn_register)
        btnRegister?.setOnClickListener {
            val bottomSheet = SignUpBottomSheetFragment(requireContext())
            bottomSheet.show(requireActivity().supportFragmentManager, "bottom_sheet_signup_fragment")
        }
        btnClose?.setOnClickListener {
            dismiss()
        }

        return view

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener{ dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
            }
        }

        return dialog
    }
}