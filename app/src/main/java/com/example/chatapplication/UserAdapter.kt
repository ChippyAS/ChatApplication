package com.example.chatapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class UserAdapter(val context: Context, val userlist: ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
      val view : View = LayoutInflater.from(context).inflate(R.layout.userlayout,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentuser = userlist[position]
        holder.name.text = currentuser.name

       holder.itemView.setOnClickListener {
           val intent = Intent(context,ChatActivity::class.java)
           intent.putExtra("name",currentuser.name)
           intent.putExtra("UID",FirebaseAuth.getInstance().currentUser?.uid)
           context.startActivity(intent)
       }
    }

    override fun getItemCount(): Int {

        return userlist.size
    }

    //viewholder
    class UserViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val name = itemView.findViewById<TextView>(R.id.text_name)
    }
}