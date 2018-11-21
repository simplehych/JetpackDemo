package com.simple.roomwordsample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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

        wordViewModel = ViewModelProviders.of(this, WordViewModelFactory(this)).get(WordViewModel::class.java)
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
