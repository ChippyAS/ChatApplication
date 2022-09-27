package com.example.chatapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.util.*

class MessageAdapter(val context: Context, val messagelist:ArrayList<message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1){
            val view : View = LayoutInflater.from(context).inflate(R.layout.receive_layout,parent,false)
            return ReceiveViewHolder(view)
        }
        else{
            val view : View = LayoutInflater.from(context).inflate(R.layout.send_layout,parent,false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        ///get the currentmessage from the list
        var currentmessage = messagelist[position]

        if (holder.javaclass == SentViewHolder::class.java){
            // do stuff for sent view holder
            val viewHolder = holder as SentViewHolder
            holder.sentmessage.text = currentmessage.mssg
        }else
        {
            //do stuff for receive view holder
            val viewHolder  = holder as ReceiveViewHolder
            holder.receivemessage.text = currentmessage.mssg
        }
    }

    override fun getItemViewType(position: Int): Int {

        var currentmessage = messagelist[position]

        return if (FirebaseAuth.getInstance().currentUser?.uid?.equals(currentmessage.senderId) == true) {
            return ITEM_SENT
        } else {
            ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {

        return  messagelist.size
    }

    class SentViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){

        var sentmessage = itemview.findViewById<TextView>(R.id.sendmessage)
    }
    class ReceiveViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val receivemessage = itemview.findViewById<TextView>(R.id.receivemessage)

    }
}