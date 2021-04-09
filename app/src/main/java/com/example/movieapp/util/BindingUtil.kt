package com.example.movieapp.util

import android.text.format.DateUtils
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.movieapp.R


@BindingAdapter("ratingValue")
fun setRatingValue(ratingBar: RatingBar, rating: Float) {
    if (rating in 0.0..5.0)
        ratingBar.rating = rating
}

@BindingAdapter("android:text")
fun setText(textView: TextView, value: List<String>) {
    textView.text = value.joinToString(", ")
}

@BindingAdapter("src")
fun setImageDrawable(view: ImageView, @DrawableRes drawableId: Int) {
    view.setImageResource(drawableId)
}

@BindingAdapter("setTime")
fun setTimer(view: TextView, value: Long) {
    view.text = DateUtils.formatElapsedTime(value)
    if(value < 10L)
        view.setTextColor(ContextCompat.getColor(view.context, R.color.red))
    else
        view.setTextColor(ContextCompat.getColor(view.context, R.color.white))
}

// or this way:
//
//object BindingUtil {
//    @JvmStatic
//    @BindingAdapter("ratingValue")
//    fun setRatingValue(ratingBar: RatingBar, rating: Float) {
//        if (rating in 0.0..5.0)
//            ratingBar.rating = rating
//    }
//
//    @JvmStatic
//    @BindingAdapter("android:text")
//    fun setText(textView: TextView, value: List<String>) {
//        textView.text = value.joinToString(", ")
//    }
//}
