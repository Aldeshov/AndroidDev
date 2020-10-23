package com.example.mail.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mail.R
import com.example.mail.fragment.MailItemFragment
import com.example.mail.model.*

class MailListAdapter(var user: User, var context: Context?): RecyclerView.Adapter<MailListAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mail_item_short, parent, false)

        return ViewHolder(user, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Letter = user.mail.mail[position]
        holder.subject.text = item.subject
        val con = item.body.take(25) + "..."
        holder.body.text = con
        if (item.is_read) {
            holder.subject.setTypeface(holder.subject.typeface, Typeface.NORMAL)
            holder.subject.setBackgroundColor(Color.parseColor("#eaeaea"))
            holder.body.setTypeface(holder.subject.typeface, Typeface.NORMAL)
        }
        else {
            holder.subject.setTypeface(holder.subject.typeface, Typeface.BOLD)
            holder.subject.setBackgroundColor(Color.parseColor("#c0c0c0"))
            holder.body.setTypeface(holder.subject.typeface, Typeface.BOLD)
        }
        holder.id = item.id
    }

    override fun getItemCount(): Int {
        return user.mail.mail.size
    }

    class ViewHolder(var user: User, view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        lateinit var id: String
        private var content: ConstraintLayout = view.findViewById(R.id.content)
        var subject: TextView = view.findViewById(R.id.subject)
        var body: TextView = view.findViewById(R.id.body)

        init {
            content.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val activity = v?.context
            val item = user.mail.mail.find { i -> i.id == this.id }
            item!!.is_read = true
            (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main, MailItemFragment(item))
                .addToBackStack("secondary")
                .commit()
        }
    }
}