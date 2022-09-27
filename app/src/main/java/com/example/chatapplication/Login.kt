package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var editemail: EditText
    private lateinit var editpass: EditText
    private lateinit var btnsign: Button
    private lateinit var btnlogin: Button

    ///firebase auth
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        editemail = findViewById(R.id.editText1)
        editpass = findViewById(R.id.editText2)
        btnsign = findViewById(R.id.button2)
        btnlogin = findViewById(R.id.button1)

        mAuth = FirebaseAuth.getInstance()//initilaize

        //to hide actionbar
        supportActionBar?.hide()


        btnsign.setOnClickListener {
           val intent: Intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email = editemail.text.toString()
            val pass = editpass.text.toString()

           login(email,pass)

        }


    }

    private fun login(email: String, pass: String) {
        //logic for loginuser
        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                        Log.d("loginwee","User doesnot exist")
                    Toast.makeText(this,"User Doesnot Exist", Toast.LENGTH_SHORT).show()

                }
            }


    }
}