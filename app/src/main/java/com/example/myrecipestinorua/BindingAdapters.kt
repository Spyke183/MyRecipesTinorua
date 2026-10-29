package com.example.myrecipestinorua

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        Picasso.get().cancelRequest(this)
        setImageResource(R.drawable.question_mark)
        return
    }
    // Placeholder drawables were downloaded from https://fonts.google.com/icons
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.hourglass_empty)
        .error(R.drawable.question_mark)
        .into(this)
}
