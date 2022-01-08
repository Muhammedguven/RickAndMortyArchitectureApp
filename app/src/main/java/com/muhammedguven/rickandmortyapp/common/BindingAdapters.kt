package com.muhammedguven.rickandmortyapp.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this)
            .load(it)
            .into(this)
    }
}

@BindingAdapter("app:isVisible")
fun visibleIfTrue(view: View, loading: Boolean) {
    view.visibility = if (!loading) View.GONE else View.VISIBLE
}
