package com.udacity.project5.watchdog.utils

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.udacity.project5.watchdog.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

// extension function to set number picker divider color for api level up to 28
@BindingAdapter("setDividerColor")
fun setDividerColor(numberPicker: NumberPicker, color: Int) {
    val pickerFields = NumberPicker::class.java.declaredFields
    for (pf in pickerFields) {
        if (pf.name == "mSelectionDivider") {
            pf.isAccessible = true
            try {
                val colorDrawable = ColorDrawable(color)
                pf[numberPicker] = colorDrawable
            } catch (e: java.lang.IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: Resources.NotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
            break
        }
    }
}

@BindingAdapter("parseDate")
fun parseDate(textView: TextView, formattedDateTime: String) {
    val actualLocalDateTime = LocalDateTime.parse(formattedDateTime)
    textView.text = actualLocalDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
