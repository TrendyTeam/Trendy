package kh.edu.rupp.ite.trendy.Util

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.text.DecimalFormat
import java.text.NumberFormat


fun Context.toastHelper(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
fun logCus(message: String){
    Log.i("LOG_CUS", message)
}
fun hideKeyboard(view: View, activity:Activity) {
    val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager!!.hideSoftInputFromWindow(view.windowToken, 0)
}

fun totalPriceFormat(price: Double): String {
    val numberFormat: NumberFormat = DecimalFormat("#,##0.00")
    numberFormat.maximumFractionDigits = 2
    return numberFormat.format(price)
}

fun calculateDiscount(dis: Double, unitPrice: Double): Double {
    return unitPrice * (100 - dis) / 100
}
