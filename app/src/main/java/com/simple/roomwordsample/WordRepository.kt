package com.simple.roomwordsample

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import androidx.lifecycle.LiveData
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * @author hych
 * @date 2018/11/21 10:16
 */
class WordRepository(context: Context) {

    private var wordDao: WordDao = AppRoomDatabase.getInstance(context).wordDao()
    private var allWords: LiveData<List<Word>> = wordDao.getAllWords()
    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                val json = msg.obj as String
                val jsonObject = JSONObject(json)
                val datas = jsonObject["data"] as JSONArray

                for (i in 0..(datas.length() - 1)) {
                    val obj = datas[i] as JSONObject
                    insert(Word(obj["name"].toString()))
                }
            }
        }
    }

    fun getAllWords(): LiveData<List<Word>> {
        val words = wordDao.getAllWords()
        HttpThread("http://wanandroid.com/wxarticle/chapters/json").start()
        return words
    }

    fun insert(word: Word) {
        InsertAsyncTask(wordDao).execute(word)
    }

    inner class InsertAsyncTask(var dao: WordDao) : AsyncTask<Word, Unit, Unit>() {
        override fun doInBackground(vararg params: Word) {
            dao.insert(params[0])
        }
    }

    inner class HttpThread(val path: String) : Thread() {
        override fun run() {
            super.run()
            val url = URL(path)
            val httpURLConnection = url.openConnection()
            httpURLConnection.connectTimeout = 5 * 1000
            httpURLConnection.readTimeout = 5 * 1000
            httpURLConnection.doOutput = true
            httpURLConnection.doInput = true

            httpURLConnection.useCaches = false
            httpURLConnection.connect()

            val sb = StringBuilder()
            val inputStream = httpURLConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            bufferedReader.forEachLine {
                sb.append(it)
            }

            val message = Message.obtain()
            message.obj = sb.toString()
            message.what = 1
            handler.sendMessage(message)

            inputStream.close()
            bufferedReader.close()
        }
    }

}