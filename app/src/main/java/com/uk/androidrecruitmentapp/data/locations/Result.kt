package com.uk.androidrecruitmentapp.data.locations

data class Result(
        var created: String,
        var dimension: String,
        var id: Int,
        var name: String,
        var residents: List<String>,
        var type: String,
        var url: String
)