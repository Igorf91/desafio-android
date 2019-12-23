package br.com.igorf91.desafio.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(imageLocation: String, view: View) {
    Glide
        .with(view)
        .load(imageLocation)
        .placeholder(getLoaderPlaceholder(view.context))
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun getLoaderPlaceholder(context: Context) = CircularProgressDrawable(context)
    .also {
        it.strokeWidth = 5f
        it.centerRadius = 30f
        it.start()
    }
