package com.example.mail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mail.R
import com.example.mail.model.Letter

class MailItemFragment(var item: Letter): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.mail_item, container, false)

        (layout.findViewById(R.id.subject) as TextView).text = item.subject
        (layout.findViewById(R.id.body) as TextView).text = item.body

        return layout
    }
}