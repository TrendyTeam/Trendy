package kh.edu.rupp.ite.trendy.Util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }


//    fun<T: Any> ioThanMain(work: suspend (() ->T?), callback: suspend (() -> Unit)) =
//        val data = CoroutineScope(Dispatchers.Main).launch {
//
//        }

}