package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DatePickerHandler {
    fun showDatePicker(context: Context, selectDate: EditText) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(context, { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(calendar.time)
            selectDate.setText(formattedDate)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePickerDialog.show()
    }
}