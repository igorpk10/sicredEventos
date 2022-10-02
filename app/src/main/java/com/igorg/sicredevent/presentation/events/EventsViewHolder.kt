package com.igorg.sicredevent.presentation.events

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.igorg.sicredevent.R
import com.igorg.sicredevent.databinding.ItemEventBinding
import com.igorg.sicredevent.domain.model.Event
import com.igorg.sicredevent.presentation.base.adapter.GenericViewHolder

class EventsViewHolder(
    private val binding: ItemEventBinding,
    val callback: (event: Event) -> Unit
) : GenericViewHolder<Event>(binding) {
    override fun bind(data: Event) {
        Glide.with(binding.image)
            .load(data.imageURL)
            .fitCenter()
            .timeout(2000)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(binding.image)

        binding.title.text = data.title
        binding.date.text = binding.date.context.getString(R.string.event_date, data.date)

        binding.container.setOnClickListener {
            callback(data)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            callback: (event: Event) -> Unit
        ): EventsViewHolder {
            val itemBinding = ItemEventBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return EventsViewHolder(itemBinding, callback)
        }
    }
}