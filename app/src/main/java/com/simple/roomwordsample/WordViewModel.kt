package com.simple.roomwordsample

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * 在ViewModel中不要持有Activity的引用。为什么要注意这一点呢？从上面的图我们看到，当Activity被recreate时，ViewModel对象并没有被销毁，如果Model持有Activity的引用时就可能会导致内存泄漏。那如果你要使用到Context对象怎么办呢，那就使用ViewModel的子类AndroidViewModel吧
 * http://shymanzhu.com/2017/12/28/Android%E6%9E%B6%E6%9E%84%E7%BB%84%E4%BB%B6%EF%BC%88%E4%B8%89%EF%BC%89%E2%80%94%E2%80%94ViewModel/
 * @author hych
 * @date 2018/11/21 10:29
 */
class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository = WordRepository(application)
    private var allWords: LiveData<List<Word>> = repository.getAllWords()

    fun getAllWords(): LiveData<List<Word>> {
        return allWords
    }

    fun insert(word: Word) {
        repository.insert(word)
    }
}