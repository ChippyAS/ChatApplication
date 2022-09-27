package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var messagebox: EditText
    private lateinit var sendbutton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messagelist:ArrayList<message>
    private lateinit var mDbRef:DatabaseReference

    ////////////////////////////////////////////////////////////
    ///this are used to create unique room for sender and receiver, so the
    ///message is private so that it is not reflected in all users
    var receiveroom: String?  = null
    var senderroom: String?  = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("UID")
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        senderroom = receiverUid + senderUid
        receiveroom = senderUid + receiverUid

        messageRecyclerView = findViewById(R.id.charrecyclerview)
        messagebox = findViewById(R.id.messagebox)
        sendbutton = findViewById(R.id.sendbutton)

        supportActionBar?.title = name

        messagelist = ArrayList()
        messageAdapter = MessageAdapter(this,messagelist)

        //adding message to database
        sendbutton.setOnClickListener {
            val message = messagebox.text.toString()
            val messageobject = message(message,senderUid)

        }





    }
}