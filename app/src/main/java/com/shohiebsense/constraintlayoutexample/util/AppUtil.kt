package com.shohiebsense.constraintlayoutexample.util

import java.text.SimpleDateFormat

object AppUtil {


    fun getDateFormFormat(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd")
    }

    fun getDateCodeFormat(): SimpleDateFormat {
        return SimpleDateFormat("yyMMdd")
    }

    fun convertIndonesianCode(phoneNumber: String): String {
        return if (phoneNumber.startsWith("+62")) phoneNumber.replaceFirst("\\+62".toRegex(), "0") else phoneNumber
    }

}
