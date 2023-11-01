package kh.edu.rupp.ite.trendy.Util

import java.io.IOException

class ApiException (message: String) : IOException(message)
class NoInternetException(message: String): IOException(message)