package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var editemail: EditText
    private lateinit var editpass: EditText
    private lateinit var editname: EditText
    private lateinit var btnsignup: Button
    ///firebase auth
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editemail = findViewById(R.id.editTextTextPersonName2)
        editpass = findViewById(R.id.editTextTextPersonName3)
        editname = findViewById(R.id.editTextTextPersonName)
        btnsignup = findViewById(R.id.button2)


        mAuth = FirebaseAuth.getInstance()//initilaize


        //to hide actionbar
        supportActionBar?.hide()


        btnsignup.setOnClickListener {

            val name = editname.text.toString()
            val email = editemail.text.toString()
            val pass = editpass.text.toString()
            
            signUp(name,email,pass)
        }

    }

    private fun signUp(name: String, email: String, pass: String) {
        //logic for creating user
        mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                        addUserToDatabase(name,email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this,MainActivity::class.java)
                    //the finish() method is called and the activity destroys and returns to the home screen.
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Some Error Occured",Toast.LENGTH_SHORT).show()

                }
            }



    }

    private fun addUserToDatabase(name: String, email: String, uid: String?) {

        mDbRef = FirebaseDatabase.getInstance().getReference()

        // child will add node to databse and pass the path will be uid, so should have uniquenode
        mDbRef.child("user").child(uid.toString()).setValue(User(name,email,uid))
    }
}