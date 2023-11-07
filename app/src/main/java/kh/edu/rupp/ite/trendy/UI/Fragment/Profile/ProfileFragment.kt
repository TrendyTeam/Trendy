package kh.edu.rupp.ite.trendy.UI.Fragment.Profile


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kh.edu.rupp.ite.trendy.Custom.DialogX
import kh.edu.rupp.ite.trendy.Model.DataBase.MySharedPreferences
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLogInResponseModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserSignUpModel
import kh.edu.rupp.ite.trendy.Model.Repository.User.UserRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Activity.SplashScreenActivity
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthListener
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthViewModel
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthViewModelFactory

class ProfileFragment (private val context: Context, private val activity: Activity):BottomSheetDialogFragment(), UserAuthListener {

    private var viewModel : UserAuthViewModel? = null
    private var btnLogOut :Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        val api = MyApi(networkConnectionInterceptor)
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
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        btnLogOut = view.findViewById(R.id.button_logOut)

        btnLogOut?.setOnClickListener {
            DialogX(requireContext()).showQuestion(
                requireContext().getString(R.string.are_you_sure),
                requireContext().getString(R.string.log_out_qs)
            ){
                viewModel?.userClearToken()
                dismiss()
                SplashScreenActivity.lunch(requireContext())
                activity.finish()
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
        TODO("Not yet implemented")
    }

    override fun onSuccessAuth(user: UserLogInResponseModel.User) {
        TODO("Not yet implemented")
    }

    override fun onFailAuth(message: String) {
        TODO("Not yet implemented")
    }

    override fun onSignUpSuccess(model: UserSignUpModel) {
        TODO("Not yet implemented")
    }
}