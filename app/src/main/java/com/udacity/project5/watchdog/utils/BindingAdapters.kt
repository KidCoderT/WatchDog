package com.udacity.project5.watchdog.utils

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.widget.NumberPicker
import androidx.databinding.BindingAdapter

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