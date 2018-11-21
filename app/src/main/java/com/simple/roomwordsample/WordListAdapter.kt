package com.simple.roomwordsample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author hych
 * @date 2018/11/21 14:36
 */
class WordListAdapter(context: Context) : RecyclerView.Adapter<WordViewHolder>() {

    private val inflate: LayoutInflater = LayoutInflater.from(context)
    private var words: List<Word>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflate.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (words != null) words!!.size else 0
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (words != null) {
            val current = words!![position]
            holder.wordItemView.text = current.word
        } else {
            holder.wordItemView.text = "No Word"
        }
    }

    fun setWords(words: List<Word>?) {
        this.words = words
        notifyDataSetChanged()
    }
}

class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val wordItemView: TextView = itemView.findViewById(R.id.textView)

}