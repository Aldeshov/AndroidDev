package com.example.afinal.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.afinal.R
import com.example.afinal.models.Article
import com.squareup.picasso.Picasso


class NewsDetailed: Fragment() {
    lateinit var  item: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            item = bundle.getParcelable("news")!! // Key
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.news_item_detailed, container, false)

        Picasso.get().load(item.urlToImage.toString())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into((layout.findViewById(R.id.image) as ImageView))

        val underline: String = item.url.toString()
        val content = SpannableString(underline)
        content.setSpan(UnderlineSpan(), 0, underline.length, 0)
        (layout.findViewById(R.id.url) as TextView).text = content

        (layout.findViewById(R.id.author) as TextView).text = item.author

        (layout.findViewById(R.id.title) as TextView).text = item.title
        (layout.findViewById(R.id.description) as TextView).text = item.description
        (layout.findViewById(R.id.content) as TextView).text = item.content

        (layout.findViewById(R.id.publishedAt) as TextView).text = item.publishedAt
        return layout
    }
}