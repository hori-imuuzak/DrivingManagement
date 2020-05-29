package app.imuuzak.driving_management.ui.binding_adapters

import android.widget.NumberPicker
import androidx.databinding.BindingAdapter

object NumberPickerBindingAdapter {
    @BindingAdapter("minValue")
    @JvmStatic
    fun setMinValue(picker: NumberPicker, value: Int) {
        picker.minValue = value
    }

    @BindingAdapter("maxValue")
    @JvmStatic
    fun setMaxValue(picker: NumberPicker, value: Int) {
        picker.maxValue = value
    }

    @BindingAdapter("value")
    @JvmStatic
    fun setValue(picker: NumberPicker, value: Int) {
        picker.value = value
    }

    @BindingAdapter("onChange")
    @JvmStatic
    fun onChange(picker: NumberPicker, listener: NumberPicker.OnValueChangeListener) {
        picker.setOnValueChangedListener(listener)
    }
}