package com.example.kengomaruyama.myalarmclock

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import org.jetbrains.anko.toast

class SimpleAlertDialog : DialogFragment(){
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = context
        if(context == null)
            return super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(context).apply {
            setMessage("時間になりました")
            setPositiveButton("起きる"){dialog, which ->
                context.toast("起きるがクリックされました")
            }
            setNegativeButton("あと5分"){dialog, which ->
                context.toast("あと5分がクリックされました")
            }
        }
        return builder.create()
    }
}