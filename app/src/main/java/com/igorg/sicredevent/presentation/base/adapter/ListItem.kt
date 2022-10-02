package com.igorg.sicredevent.presentation.base.adapter

interface ListItem {
    val key: Long

    fun areItemsTheSame(other: ListItem) = this.key == other.key

    fun areContentsTheSame(other: ListItem) = this == other
}
