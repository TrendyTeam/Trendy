package kh.edu.rupp.ite.trendy.Custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.github.hariprasanths.bounceview.BounceView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kh.edu.rupp.ite.trendy.R
import java.util.Objects


class DialogX(private val context: Context) {
    private var tvTitle: TextView? = null
    private var tvDesc: TextView? = null
    private var btnConfirm: TextView? = null
    private var btnCancel: TextView? = null
    private var dialog: Dialog? = null
    private var imageIcon: LottieAnimationView? = null
//    private var progressBar: LottieAnimationView? = null
    private var lnButton: LinearLayout? = null
    private var title: String? = null
    private var description: String? = null

    init {
        try {
            initialize()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun initialize() {
        dialog = Dialog(context)
        dialog!!.requestWindowFeature(Window.FEATURE_ACTION_BAR) // before
        dialog!!.setContentView(R.layout.dialog_x)
        Objects.requireNonNull(dialog!!.window)
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(true)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(Objects.requireNonNull(dialog!!.window)!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = lp
        BounceView.addAnimTo(dialog)
//        progressBar = dialog!!.findViewById<LottieAnimationView>(R.id.animationView)
        imageIcon = dialog!!.findViewById<LottieAnimationView>(R.id.animationView)
        tvTitle = dialog!!.findViewById<TextView>(R.id.tv_title)
        tvDesc = dialog!!.findViewById<TextView>(R.id.tv_desc)
        btnConfirm = dialog!!.findViewById<TextView>(R.id.btn_confirm)
        btnCancel = dialog!!.findViewById<TextView>(R.id.btn_cancel)
        lnButton = dialog!!.findViewById<LinearLayout>(R.id.ln_button_2)
        tvTitle?.text = ""
        tvDesc?.text = ""
        btnCancel?.setOnClickListener(View.OnClickListener { v: View? -> dialog!!.dismiss() })
    }

    fun setTitle(title: String?) {
        this.title = title
        tvTitle!!.text = title
    }

    fun setMessage(message: String?) {
        description = message
        tvDesc!!.text = message
    }

    fun showX() {
        if (dialog!!.isShowing) {
            dialog!!.show()
        }
    }

    fun showSimple(title: String?, description: String?) {
//        progressBar!!.visibility = View.GONE
        imageIcon!!.visibility = View.GONE
        lnButton!!.visibility = View.GONE
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.VISIBLE
        tvTitle!!.text = title
        tvDesc!!.text = description
        if (!dialog!!.isShowing) {
            dialog!!.show()
        }
    }

    fun showQuestion(title: String?, description: String?, onCallback: () -> Unit) {
//        progressBar!!.visibility = View.GONE
//        imageIcon.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_help_outline_24));
//        imageIcon.getDrawable().setTint(context.getResources().getColor(R.color.grey_80));
        lnButton!!.visibility = View.VISIBLE
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.VISIBLE
        tvTitle!!.text = title
        tvDesc!!.text = description
        btnConfirm!!.setOnClickListener { v: View? ->
            onCallback.invoke()
            dismissX()
        }
        if (!dialog!!.isShowing) {
            dialog!!.show()
        }
    }

    fun dismissX() {
        dialog!!.dismiss()
    }

    fun showErrorV2(title: String?, description: String?): Dialog? {
//        progressBar!!.visibility = View.GONE
        imageIcon!!.setAnimation("error.json")
        imageIcon!!.visibility = View.VISIBLE
        imageIcon!!.loop(false)
        lnButton!!.visibility = View.GONE
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.VISIBLE
        tvTitle!!.text = title
        tvDesc!!.text = description
        return dialog
    }

    fun showError(title: String?, description: String?) {
//        progressBar!!.visibility = View.GONE
        imageIcon!!.setAnimation("error.json")
        imageIcon!!.visibility = View.VISIBLE
        imageIcon!!.loop(false)
        lnButton!!.visibility = View.GONE
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.VISIBLE
        tvTitle!!.text = title
        tvDesc!!.text = description
        if (!dialog!!.isShowing) {
            dialog!!.show()
        }
    }

    fun showWarning(
        title: String?,
        description: String?,
        btnYes: String?,
        btnNo: String?,
        onCallback: OnCallback
    ) {
//        progressBar!!.visibility = View.GONE

//        imageIcon.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_warning_24));
//        imageIcon.getDrawable().setTint(context.getResources().getColor(R.color.yellow_700));
        //  imageIcon.setAnimationFromUrl("https://assets3.lottiefiles.com/packages/lf20_dVJMow.json");
        imageIcon!!.setAnimation("warning.json")
        imageIcon!!.visibility = View.VISIBLE
        lnButton!!.visibility = View.VISIBLE
        btnConfirm!!.text = btnYes
        btnCancel!!.text = btnNo
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.VISIBLE
        tvTitle!!.text = title
        tvDesc!!.text = description
        if (dialog!!.isShowing) {
            dialog!!.dismiss()
        }
        btnConfirm!!.setOnClickListener { v: View? ->
            onCallback.onConfirmClick()
            dismissX()
        }
        dialog!!.show()
    }

    fun showWarningMsg(title: String?, description: String?) {
//        progressBar!!.visibility = View.GONE

//        imageIcon.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_warning_24));
//        imageIcon.getDrawable().setTint(context.getResources().getColor(R.color.yellow_700));
        //   imageIcon.setAnimationFromUrl("https://assets3.lottiefiles.com/packages/lf20_dVJMow.json");
        imageIcon!!.setAnimation("warning.json")
        imageIcon!!.visibility = View.VISIBLE
        lnButton!!.visibility = View.VISIBLE
        btnCancel!!.visibility = View.INVISIBLE
        btnConfirm!!.visibility = View.VISIBLE
        btnConfirm!!.text = "OK"
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.VISIBLE
        tvTitle!!.text = title
        tvDesc!!.text = description
        if (dialog!!.isShowing) {
            dialog!!.dismiss()
        }
        btnConfirm!!.setOnClickListener { v: View? -> dismissX() }
        dialog!!.show()
    }

    fun showSuccess(title: String?, description: String?) {
//        progressBar!!.visibility = View.GONE
        imageIcon!!.setAnimation("success.json")
        // imageIcon.setAnimationFromUrl("https://assets7.lottiefiles.com/packages/lf20_rQvUxg.json");
        imageIcon!!.visibility = View.VISIBLE
        //        imageIcon.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_done_outline_24));
//        imageIcon.getDrawable().setTint(context.getResources().getColor(R.color.green_800));
        imageIcon!!.loop(false)
        lnButton!!.visibility = View.GONE
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.VISIBLE
        tvTitle!!.text = title
        tvDesc!!.text = description
        if (!dialog!!.isShowing) {
            dialog!!.show()
        }
    }

    fun showSuccessonConfirmClick(title: String?, description: String?, onCallback: OnCallback) {
//        progressBar!!.visibility = View.GONE
        imageIcon!!.setAnimation("success.json")
        // imageIcon.setAnimationFromUrl("https://assets7.lottiefiles.com/packages/lf20_rQvUxg.json");
        imageIcon!!.visibility = View.VISIBLE
        //        imageIcon.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_done_outline_24));
//        imageIcon.getDrawable().setTint(context.getResources().getColor(R.color.green_800));
        imageIcon!!.loop(false)
        lnButton!!.visibility = View.GONE
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.VISIBLE
        tvTitle!!.text = title
        tvDesc!!.text = description
        btnConfirm!!.setOnClickListener { v: View? ->
            onCallback.onConfirmClick()
            dismissX()
        }
        if (!dialog!!.isShowing) {
            dialog!!.show()
        }
    }

    fun showProgress() {
//        progressBar!!.visibility = View.VISIBLE
        imageIcon!!.visibility = View.GONE
        lnButton!!.visibility = View.GONE
        tvDesc!!.visibility = View.GONE
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.GONE
        tvDesc!!.text = "Please wait"
        if (!dialog!!.isShowing) {
            dialog!!.show()
        }
    }

    fun showProgressX(title: String?) {
//        progressBar!!.visibility = View.VISIBLE
        imageIcon!!.visibility = View.GONE
        lnButton!!.visibility = View.GONE
        tvDesc!!.visibility = View.GONE
        tvDesc!!.visibility = View.VISIBLE
        tvTitle!!.visibility = View.GONE
        tvDesc!!.text = title
        dialog!!.show()
    }

    interface OnCallback {
        fun onConfirmClick()
    }

    // implement material dialog
    fun onErrorMaterDialog(
        context: Context,
        title: String?,
        message: String?
    ): MaterialAlertDialogBuilder {
        return MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setNegativeButton(
                context.getString(R.string.cancel)
            ) { dialog, which -> dialog.dismiss() }
            .setMessage(message)
    }

    fun dialogWithMessage(title: String?, message: String?): MaterialAlertDialogBuilder {
        return MaterialAlertDialogBuilder(context).setTitle(title).setMessage(message)
    }


    // show year display only

}