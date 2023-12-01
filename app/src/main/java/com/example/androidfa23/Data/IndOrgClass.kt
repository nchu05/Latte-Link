package com.example.androidfa23.Data

import com.squareup.moshi.Json

data class IndOrgClass(


    var id: Int,
    var name: String,
    var pfp: String?,
    var banner: String?,
    var bio: String?,
    var users: List<PersonClass>,
    var tags: List<String>?,

)
