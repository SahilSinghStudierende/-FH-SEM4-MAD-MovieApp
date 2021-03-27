package com.example.movieapp.util

import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("ratingValue")
fun setRatingValue(ratingBar: RatingBar, rating: Float) {
    if (rating in 0.0..5.0)
        ratingBar.rating = rating
}

@BindingAdapter("android:text")
fun setText(textView: TextView, value: List<String>) {
    textView.text = value.joinToString(", ")
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
