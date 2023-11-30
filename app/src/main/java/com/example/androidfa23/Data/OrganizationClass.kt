package com.example.androidfa23.Data

data class OrganizationClass(
    var id: Int,
    var login_email: String,
    var login_password: String,
    var name: String,
    var pfp: String,
    var banner: String,
    var bio: String,
    var users: List<PersonClass>,
    var tags: List<String>,

)
