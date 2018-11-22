package com.simple.roomwordsample

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : FragmentActivity() {
    private lateinit var wordViewModel: WordViewModel
    private var num: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.adapter = WordListAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.getAllWords().observe(this, Observer<List<Word>> { words ->
            (recyclerview.adapter as WordListAdapter).setWords(words)
        })

        fab.setOnClickListener {
            wordViewModel.getAllWords()
            wordViewModel.insert(Word("click ${num++}"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}


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