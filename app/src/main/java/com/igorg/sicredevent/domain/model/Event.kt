package com.igorg.sicredevent.domain.model

import com.igorg.sicredevent.presentation.base.adapter.ListItem

data class Event(
    val id: Long,
    val title: String,
    val imageURL: String,
    val description: String,
    val date: String,
    val price: String,
    override val key: Long = id,
) : ListItem

fun Event.getEventDescriptionForShare() = "${this.title}-${this.date}"