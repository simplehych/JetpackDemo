package com.simple.roomwordsample

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData

/**
 * @author hych
 * @date 2018/11/21 10:16
 */
class WordRepository(context: Context) {

    private var wordDao: WordDao = AppRoomDatabase.getInstance(context).wordDao()
    private var allWords: LiveData<List<Word>> = wordDao.getAllWords()

    fun getAllWords(): LiveData<List<Word>> {
        return wordDao.getAllWords()
    }

    fun insert(word: Word) {
        InsertAsyncTask(wordDao).execute(word)
    }

    class InsertAsyncTask(var dao: WordDao) : AsyncTask<Word, Unit, Unit>() {
        override fun doInBackground(vararg params: Word) {
            dao.insert(params[0])
        }
    }

}