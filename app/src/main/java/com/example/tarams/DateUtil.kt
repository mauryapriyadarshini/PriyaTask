package com.example.tarams

import java.text.SimpleDateFormat

private const val INPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val OUTPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm"

class DateUtil {
    fun dateFormat(date:String?) : String {
        val simpleDateFormatInput = SimpleDateFormat(INPUT_DATE_FORMAT)
        val simpleDateFormatOutput = SimpleDateFormat(OUTPUT_DATE_FORMAT)
        val dateFormatObject = simpleDateFormatInput.parse(date)
        return simpleDateFormatOutput.format(dateFormatObject).toString()
    }
}