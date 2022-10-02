package com.igorg.sicredevent.data.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.*

data class EventResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: BigDecimal,
    @SerializedName("latitude")
    val latitude: BigDecimal,
    @SerializedName("longitude")
    val longitude: BigDecimal,
    @SerializedName("image")
    val imageURL: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    val date: Long,
    @SerializedName("people")
    val people: List<String>
)
