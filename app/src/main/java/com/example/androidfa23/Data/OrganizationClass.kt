package com.example.androidfa23.Data

import com.squareup.moshi.Json

data class OrganizationClass(
    var id: Int,
    var name: String,
    @Json(name = "org pfp")
    var org_pfp: String,
    //var login_email: String?,
    //var login_password: String?,
    //var pfp: String?,
    //var banner: String?,
    // var bio: String?,
    //var users: List<PersonClass>?,
    //var tags: List<String>?,

)
