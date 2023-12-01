package com.example.androidfa23.Data

import com.squareup.moshi.Json

data class IndPersonClass(

    var id: Int,
    //var login_email: String,
    //var login_password: String,
    var name: String,
    var year: Int,
    var major: String,
    var pfp: String,
    var banner: String,
    var bio: String,
    var instagram: String,
    var linkedin: String,
    @Json(name = "public email")
    var public_email: String,
    val status: Boolean,
    var organizations: List<OrganizationClass>,
    var tags: List<String>,
    @Json(name = "busy times")
    var busy_times : List<String>,
    @Json(name = "free times")
    var free_times: List<String>,
    @Json(name = "preferred locations")
    var preferred_locations: List<String>,
    @Json(name = "org requests")
    var org_requests : List<OrganizationClass>,
    @Json(name = "chat requests sent")
    var chat_requests_sent : List<RequestClass>,
    @Json(name = "chat requests received")
    var chat_requests_received : List<RequestClass>
)
