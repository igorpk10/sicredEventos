package com.igorg.sicredevent.presentation.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

inline fun <T : ListItem, VH : GenericViewHolder<T>> getGenericAdapterOf(
    crossinline createViewHolder: (ViewGroup) -> VH
): ListAdapter<T, VH> {

    val diff = GenericDiffCallback<T>()

    return object : ListAdapter<T, VH>(diff) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return createViewHolder.invoke(parent)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(getItem(position))
        }
    }
}