package com.example.imdb.shared

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.imdb.BuildConfig
import com.example.imdb.R

@BindingAdapter("setDouble")
fun setDouble(textView: TextView, number: Double) {

    textView.text = number.toString()
}

@BindingAdapter("isFavorite")
fun isFavorite(image: ImageView, favorite: Boolean) {

    val resource = if (favorite) {
        R.drawable.ic_baseline_favorite_24
    } else {
        R.drawable.ic_baseline_favorite_disabled_24
    }

    Glide.with(image)
        .load(resource)
        .into(image)
}

@BindingAdapter("setMoviePoster")
fun setMoviePoster(image: ImageView, imagePath: String?) {

    val poster = "${BuildConfig.API_PHOTOS}$imagePath"
    GlideApp.with(image)
        .load(poster)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .skipMemoryCache(false)
        .centerCrop()
        .into(image)
}

@BindingAdapter("setProgressVisibility")
fun setProgressVisibility(progress: ProgressBar, visibility: Boolean) {

    if (visibility)
        progress.visibility = View.VISIBLE
    else
        progress.visibility = View.GONE
}

@BindingAdapter("setVisibility")
fun setVisibility(view: View, visible: Boolean) {

    if (visible)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("setNegativeVisibility")
fun setNegativeVisibility(view: View, visible: Boolean) {

    if (!visible)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}