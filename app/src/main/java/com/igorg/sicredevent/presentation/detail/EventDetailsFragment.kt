package com.igorg.sicredevent.presentation.detail

import android.graphics.drawable.Drawable
import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.igorg.sicredevent.R
import com.igorg.sicredevent.databinding.FragmentEventDetailsBinding
import com.igorg.sicredevent.domain.model.Event
import com.igorg.sicredevent.domain.model.getEventDescriptionForShare
import com.igorg.sicredevent.presentation.checkin.CheckinBottomSheeet


class EventDetailsFragment : Fragment() {

    companion object {
        private const val TYPE_TEXT = "text/plain"
    }

    private var _binding: FragmentEventDetailsBinding? = null
    private val binding: FragmentEventDetailsBinding get() = _binding!!

    private val args by navArgs<EventDetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentEventDetailsBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailViewArgs = args.eventDetailViewArgs.event
        bindImage(detailViewArgs.imageURL)
        bindDate(detailViewArgs.date)
        bindDescription(detailViewArgs.description)
        bindButtons(detailViewArgs)
        bindValue(detailViewArgs.price)
    }

    private fun bindImage(imageURL: String) {
        Glide.with(binding.image)
            .load(imageURL)
            .fitCenter()
            .timeout(2000)
            .placeholder(R.drawable.ic_loading)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.image.setImageResource(R.drawable.ic_error)
                    binding.image.scaleType = ImageView.ScaleType.FIT_CENTER
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let {
                        binding.image.setImageDrawable(it)
                        binding.image.scaleType = ImageView.ScaleType.FIT_XY
                    } ?: run {
                        binding.image.setImageResource(R.drawable.ic_error)
                        binding.image.scaleType = ImageView.ScaleType.FIT_CENTER
                    }
                    return true
                }

            })
            .into(binding.image)
    }

    private fun bindDate(date: String) {
        binding.date.text = getString(R.string.event_date, date)
    }

    private fun bindDescription(description: String) {
        binding.description.text = description

        //Adiciona o tipo justificado para api 29
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.description.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }
    }

    private fun bindButtons(event: Event) {
        binding.fabShare.setOnClickListener {
            ShareCompat.IntentBuilder(requireContext())
                .setText(event.getEventDescriptionForShare())
                .setType(TYPE_TEXT)
                .setChooserTitle(R.string.share_event)
                .startChooser()
        }

        binding.fabCheck.setOnClickListener {
            CheckinBottomSheeet(event.id).show(
                parentFragmentManager,
                EventDetailsFragment::class.java.toString()
            )
        }
    }

    private fun bindValue(price: String) {
        binding.value.text = getString(R.string.value, price)
    }
}
