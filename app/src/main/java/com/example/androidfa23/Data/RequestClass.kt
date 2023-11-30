package com.example.androidfa23.Data

import android.app.Person
import android.content.BroadcastReceiver
import android.icu.util.Calendar.WeekData

data class RequestClass(
    var id: Int,
    var start_time: Int,
    var month: Int,
    var day: Int,
    var year: Int,
    var weekday: Int,
    var message: String,
    var accepted: Boolean?,
    var sender: PersonClass,
    var receiver: PersonClass
)
