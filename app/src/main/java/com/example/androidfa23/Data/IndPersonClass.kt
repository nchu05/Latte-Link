package com.example.androidfa23.Data

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
    var public_email: String,
    val status: Boolean,
    var organizations: List<OrganizationClass>,
    var tags: List<String>,
    var busy_times : List<String>,
    var free_times: List<String>,
    var preferred_locations: List<String>,
    var org_requests : List<OrganizationClass>,
    var chat_requests_sent : List<RequestClass>,
    var chat_requests_received : List<RequestClass>
)
