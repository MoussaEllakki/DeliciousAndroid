package AllClasses

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class Message {

    var tableBusy =
        "This table is not available. Would you like to use it anyway? This will clear all orders"
    var sendOrderConfimation = "Are you sure you want to send order?"

    fun sendMsg(msg: String, act: Activity) {
        val builder = AlertDialog.Builder(act)
        builder.setTitle("Message")
        builder.setMessage(msg)
        builder.setPositiveButton("OK", { dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }
}


