package com.example.kengomaruyama.myalarmclock

import android.app.*
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.DatePicker
import android.widget.TimePicker
import org.jetbrains.anko.toast
import java.time.Month
import java.time.Year
import java.util.*
import kotlin.math.min

class SimpleAlertDialog : DialogFragment(){
    @RequiresApi(Build.VERSION_CODES.M)

    interface OnClickListener {
        fun onPositiveClick()
        fun onNegativeClick()
    }

    private lateinit var listener: OnClickListener

    override fun onAttach(context: Context?){
        super.onAttach(context)
        if (context is SimpleAlertDialog.OnClickListener) {
            listener = context
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity).apply {
            setMessage("時間になりました")
            setPositiveButton("起きる"){dialog, which ->
                listener.onPositiveClick()
            }
            setNegativeButton("あと5分"){dialog, which ->
                listener.onNegativeClick()
            }
        }
        return builder.create()
    }
}

class DatePickerFragment : DialogFragment(),
        DatePickerDialog.OnDateSetListener {

    interface OnDateSelectedListener {
        fun onSelected(year: Int, month: Int, date:Int)
    }

    private lateinit var listener: OnDateSelectedListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnDateSelectedListener){
            listener = context
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val date = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(context, this, year,month,date)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, date: Int) {
        listener.onSelected(year,month,date)
    }

}

class TimePickerFragment : DialogFragment(),
        TimePickerDialog.OnTimeSetListener {

    interface OnTimeSelectedLister {
        fun onSelected(hourOfDay: Int, minute: Int)
    }

    private lateinit var listener: OnTimeSelectedLister

    override fun onAttach(context: Context?){
        super.onAttach(context)
        if (context is TimePickerFragment.OnTimeSelectedLister){
            listener = context
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(context, this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        listener.onSelected(hourOfDay, minute)
    }
}