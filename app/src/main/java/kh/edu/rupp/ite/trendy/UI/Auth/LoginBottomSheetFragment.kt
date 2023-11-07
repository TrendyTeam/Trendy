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
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
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
import kh.edu.rupp.ite.trendy.UI.Activity.SplashScreenActivity
import kh.edu.rupp.ite.trendy.Util.hideKeyboard
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthListener
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthViewModel
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthViewModelFactory
import kh.edu.rupp.ite.trendy.databinding.ActivityLoginBinding


class LoginBottomSheetFragment(private val context: Context, private val activity:Activity):BottomSheetDialogFragment(), UserAuthListener {
    private var btnClose : ImageView?=null
    private var btnRegister: Button? = null
    private var btnLogin: Button? = null
    private var loadingProgress: LottieAnimationView? = null
    private var edtPhone: TextInputEditText? = null
    private var edtPassword: TextInputEditText? = null
    private var viewModel:UserAuthViewModel? =null
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        val api = MyApi(networkConnectionInterceptor)
        val sharedPreferences = MySharedPreferences(requireContext())
        val userRepository = UserRepository(api, sharedPreferences)
        val factory = UserAuthViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, factory).get(UserAuthViewModel::class.java)
        viewModel!!.authListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_login, container, false)
        btnClose = view.findViewById(R.id.btn_close)
        btnRegister = view.findViewById(R.id.btn_register)
        edtPhone = view.findViewById(R.id.phoneTxtEdt)
        edtPassword = view.findViewById(R.id.passwordTxtEdt)
        btnLogin = view.findViewById(R.id.button_login)
        loadingProgress = view.findViewById(R.id.loading)
        btnRegister?.setOnClickListener {
            val bottomSheet = SignUpBottomSheetFragment(requireContext(), activity)
            bottomSheet.show(requireActivity().supportFragmentManager, "bottom_sheet_signup_fragment")
        }
        btnClose?.setOnClickListener {
            dismiss()
        }



        btnLogin?.setOnClickListener {
            hideKeyboard(view, activity)
            val phone = edtPhone?.text.toString()
            val password = edtPassword?.text.toString()
            viewModel?.userLogIn(phone,password)


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
        loadingProgress!!.visibility = View.VISIBLE
    }

    override fun onSuccessAuth(user: UserLogInResponseModel.User) {
        Toast.makeText(context, "${user.username} is login ....", Toast.LENGTH_SHORT).show()
        SplashScreenActivity.lunch(requireContext())
        activity.finish()
        loadingProgress!!.visibility = View.GONE
    }

    override fun onFailAuth(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        loadingProgress!!.visibility = View.GONE
    }

    override fun onSignUpSuccess(model: UserSignUpModel) {
        Log.d("TAG", "onSignUpSuccess: ")
    }


}