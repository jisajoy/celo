package com.interview.challenge.celouser.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.interview.challenge.celouser.R
import com.interview.challenge.celouser.model.DomainModel

@BindingAdapter("userImage")
fun bindImage(imageView: ImageView, imgUrl: String) {

    imgUrl?.let {

        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)

    }
}