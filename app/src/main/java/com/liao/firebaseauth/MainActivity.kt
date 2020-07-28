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
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login1.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        var auth = FirebaseAuth.getInstance()

        user = auth.getCurrentUser()!!


        button_change_email.setOnClickListener {
            setContentView(R.layout.activity_login1)
            linear_1.visibility = View.GONE
            linear_2.visibility = View.GONE
            button_login.visibility = View.GONE
            button_register.visibility = View.GONE
            button_forget_password.visibility = View.VISIBLE

        }
        if (button_change_email.visibility == View.GONE) {
            button_forget_password.setOnClickListener {
                var email2 = edit_text_email.text.toString()
                user!!.updateEmail(email2)
            }
        }



        button_change_password2.setOnClickListener {
            setContentView(R.layout.activity_login1)
            linear_1.visibility = View.GONE
            linear_3.visibility = View.GONE
            button_login.visibility = View.GONE
            button_register.visibility = View.GONE
            button_forget_password.visibility = View.VISIBLE

        }
        if (button_change_password2.visibility == View.GONE) {
            button_forget_password.setOnClickListener {
                var password2 = edit_text_password.text.toString()
                user!!.updatePassword(password2)
            }
        }


        button_sent_reset_email.setOnClickListener {
            auth.sendPasswordResetEmail(user?.email!!)
            Toast.makeText(applicationContext, "success, check your mailbox", Toast.LENGTH_SHORT)
                .show()
        }

        button_delete_user.setOnClickListener {
            user!!.delete()
            Toast.makeText(applicationContext, "delete success", Toast.LENGTH_SHORT)
                .show()
            auth.signOut()
            startActivity(Intent(this,LoginActivity::class.java))
        }

        button_sign_out.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,LoginActivity::class.java))
        }


    }
}