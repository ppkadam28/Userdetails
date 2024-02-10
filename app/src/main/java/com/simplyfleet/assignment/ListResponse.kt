package com.simplyfleet.assignment

import com.google.gson.annotations.SerializedName

class ListResponse(

@SerializedName("Search")
val DetailsLists: List<Search>?
)

data class Search (
    @SerializedName("Title")
    val Title: String,
    @SerializedName("Year")
    val Year: String,
    @SerializedName("Type")
    val Type: String,
    @SerializedName("Poster")
    val Poster: String,

)
