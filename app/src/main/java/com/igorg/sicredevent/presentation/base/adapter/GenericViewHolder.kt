package com.igorg.sicredevent.presentation.base.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class GenericViewHolder<T>(
    view: ViewBinding
) : RecyclerView.ViewHolder(view.root) {

    abstract fun bind(data: T)
}