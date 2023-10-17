package com.kot.honts.token

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    private val TAG = "PushNotification-MainActivity"
    var tokenTextValue: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tokenTextValue= findViewById(R.id.tokenTextView);

//        FirebaseApp.initializeApp(this)

        FirebaseMessaging.getInstance().getToken()
            .addOnCompleteListener(object : OnCompleteListener<String?> {
                override fun onComplete(task: Task<String?>) {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException())
                        Toast.makeText(this@MainActivity, "Token Failed", Toast.LENGTH_SHORT).show()
                        return
                    }

                    // Get new FCM registration token
                    val token: String? = task.getResult()

                    // Log
                    Log.d(TAG, "onComplete: token is $token")
                    tokenTextValue?.setText("Token value is \n\n$token")
                    Toast.makeText(this@MainActivity, "Token Successful", Toast.LENGTH_SHORT).show()
                }
            })


    }
}