package com.example.androidfa23.Data

import java.sql.Timestamp
import java.util.Date

data class RequestClass(
    var id: Int,
    var requester: PersonClass,
    var receiver: PersonClass,
    var dateTime: String,
    var location: String,
    var message: String
)
