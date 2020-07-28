package com.liao.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login1.*


class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login1)

        init()
    }

    private fun init() {
        button_register.visibility = View.GONE
        button_forget_password.visibility = View.GONE
        text_view_2.visibility = View.GONE
        var auth = FirebaseAuth.getInstance()
        if(auth.currentUser!= null){var myIntent=Intent(applicationContext, MainActivity::class.java)
            startActivity(myIntent)}

        button_login.setOnClickListener {
            var email = edit_text_email.text.toString()
            var password = edit_text_password.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "logged in", Toast.LENGTH_SHORT)
                                .show()
                            var myIntent=Intent(applicationContext, MainActivity::class.java)
                            startActivity(myIntent)
                        } else {
                            Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
        }

        button_register.setOnClickListener {
            var email = edit_text_email.text.toString()
            var password = edit_text_password.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "registered", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
        }

        button_forget_password.setOnClickListener {
            var email = edit_text_email.text.toString()
            linear_2.visibility = View.GONE
            auth.sendPasswordResetEmail(email)
            Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()

        }



        text_view_1.setOnClickListener {
            text_view_1.visibility = View.GONE
            text_view_2.visibility = View.VISIBLE
            button_login.visibility = View.GONE
            button_register.visibility = View.VISIBLE
            button_forget_password.visibility = View.GONE
            linear_2.visibility = View.VISIBLE


        }
        text_view_2.setOnClickListener {
            text_view_1.visibility = View.VISIBLE
            text_view_2.visibility = View.GONE
            button_login.visibility = View.VISIBLE
            button_register.visibility = View.GONE
            button_forget_password.visibility = View.GONE
            linear_2.visibility = View.VISIBLE



        }

        text_view_forget_password.setOnClickListener {
            button_login.visibility = View.GONE
            button_register.visibility = View.GONE
            button_forget_password.visibility = View.VISIBLE
            linear_2.visibility = View.GONE



        }
    }
}