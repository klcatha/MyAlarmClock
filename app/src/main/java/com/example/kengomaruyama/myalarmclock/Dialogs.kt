package com.example.kengomaruyama.myalarmclock

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import org.jetbrains.anko.toast

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