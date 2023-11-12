package kh.edu.rupp.ite.trendy.UI.Auth

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import kh.edu.rupp.ite.trendy.Model.DataBase.MySharedPreferences
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLogInResponseModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserSignUpModel
import kh.edu.rupp.ite.trendy.Model.Repository.User.UserRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.Util.hideKeyboard
import kh.edu.rupp.ite.trendy.Util.toastHelper
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthListener
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthViewModel
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthViewModelFactory
import kotlinx.android.synthetic.main.activity_register.passwordTxtEdt
import kotlinx.android.synthetic.main.activity_register.phoneTxtEdt
import kotlinx.android.synthetic.main.activity_register.usernameEdt

class SignUpBottomSheetFragment(private val context: Context, private val activity: Activity):BottomSheetDialogFragment(), UserAuthListener {
    private var btnClose : ImageView?=null
    private var btnSignUp: Button? = null
    private var edtUsername: TextInputEditText? = null
    private var edtPhone: TextInputEditText? = null
    private var edtPassword: TextInputEditText? = null
    private var checkBoxMale : CheckBox? = null
    private var checkBoxFemale: CheckBox? = null
    private var viewModel : UserAuthViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        val api = MyApi(networkConnectionInterceptor, requireContext())
        val sharedPreferences = MySharedPreferences(requireContext())
        val userRepository = UserRepository(api, sharedPreferences)
        val factory = UserAuthViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, factory).get(UserAuthViewModel::class.java)
        viewModel?.authListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.activity_register, container, false)
        btnClose = view.findViewById(R.id.btn_register_close)
        btnSignUp = view.findViewById(R.id.button_sign_up)
        edtUsername = view.findViewById(R.id.usernameEdt)
        edtPhone = view.findViewById(R.id.phoneTxtEdt)
        edtPassword = view.findViewById(R.id.passwordTxtEdt)
        checkBoxMale = view.findViewById(R.id.gender_male)
        checkBoxFemale = view.findViewById(R.id.gender_female)
        var gender: Int? = null


        btnClose?.setOnClickListener {
            dismiss()
        }

        btnSignUp?.setOnClickListener {

        }

        edtPassword?.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(v, activity)
            }
        }

        edtPhone?.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(v, activity)
            }
        }

        usernameEdt?.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(v, activity)
            }
        }

        checkBoxMale?.setOnClickListener{
            if (checkBoxMale?.isChecked == true){
                gender = 1
                checkBoxMale?.error = null
                checkBoxFemale?.error = null
                checkBoxFemale?.isChecked = false
            }
            else{
                gender = 0
            }
        }

        checkBoxFemale?.setOnClickListener {
            if (checkBoxFemale?.isChecked == true){
                gender = 2

                checkBoxMale?.error = null
                checkBoxFemale?.error = null
                checkBoxMale?.isChecked =false
            }
            else{
                gender = 0
            }
        }

        btnSignUp?.setOnClickListener {

            if (checkBoxMale?.isChecked == true || checkBoxFemale?.isChecked == true ){
                Log.d("TEST", "gender = $gender")
                val username = usernameEdt.text.toString()
                val phone = phoneTxtEdt.text.toString()
                val password = passwordTxtEdt.text.toString()
                viewModel?.userSignUp(username,phone,password, gender!!)



            }else{
                checkBoxMale?.error = "* Require"
                checkBoxFemale?.error = "* Require"
            }
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

    override fun onStartAuth() {

    }

    override fun onSuccessAuth(user: UserLogInResponseModel.User) {

    }

    override fun onFailAuth(message: String) {
        context.toastHelper(message)
    }

    override fun onSignUpSuccess(model: UserSignUpModel) {
        context.toastHelper(model.message.toString())
        dismiss()
    }
}