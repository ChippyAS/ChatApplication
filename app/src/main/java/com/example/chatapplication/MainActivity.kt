package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var userrecyclerview: RecyclerView
    private lateinit var userlist : ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDBRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userlist = ArrayList()
        adapter = UserAdapter(this,userlist)
        // add the users in arraylist to show in recyclerview , for that get the list  from database
        mAuth = FirebaseAuth.getInstance()
        mDBRef = FirebaseDatabase.getInstance().getReference()

        //recycler view settings
        userrecyclerview = findViewById(R.id.user_recyclerview)
        userrecyclerview.layoutManager = LinearLayoutManager(this)
        userrecyclerview.adapter = adapter

        mDBRef.child("user").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //snapshot is used to get data from database
                userlist.clear()//before for loop runs clear the previous list
                for (postsnapshot in snapshot.children) {
                    val currentUser:User = postsnapshot.getValue(User::class.java) as User

                    // next the one user who is signed in  should not be shown in the homepage
                    if(mAuth.currentUser!!.uid != currentUser.id )
                    {
                    userlist.add(currentUser!! as User)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        } )


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout)
        {
            // here firebaseauth gets signout
            mAuth.signOut()
            val intent = Intent(this,Login::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}