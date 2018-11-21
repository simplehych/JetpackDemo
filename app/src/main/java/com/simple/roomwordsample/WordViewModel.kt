package com.simple.roomwordsample

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * @author hych
 * @date 2018/11/21 10:29
 */
class WordViewModel(context: Context) : ViewModel() {

    private val repository: WordRepository = WordRepository(context)
    private var allWords: LiveData<List<Word>> = repository.getAllWords()

    fun getAllWords(): LiveData<List<Word>> {
        return allWords
    }

    fun insert(word: Word) {
        repository.insert(word)
    }
}