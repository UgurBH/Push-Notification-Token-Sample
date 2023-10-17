package com.kot.honts.token

import android.os.Build
import android.os.Looper
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class NotificationMessageService : FirebaseMessagingService() {
    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(TAG, "onMessageReceived: notification received")
        val messageTitle = message.notification!!.title
        val messageBody = message.notification!!.body
        Log.d(TAG, "onMessageReceived: message title is $messageTitle")
        Log.d(TAG, "onMessageReceived: message body is $messageBody")
        Handler(Looper.getMainLooper()).post(Runnable {
            Toast.makeText(
                this@NotificationMessageService,
                "Token received $messageTitle",
                Toast.LENGTH_LONG
            ).show()
        })

        super.onMessageReceived(message)
    }

    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
        Log.d(TAG, "onMessageSent: message sent!!!")
    }

    companion object {
        private const val TAG = "PushNotification-MessageServ"
    }
}