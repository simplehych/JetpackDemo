package com.simple.roomwordsample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * @author hych
 * @date 2018/11/21 09:34
 */

@Dao
interface WordDao {

    @Insert
    fun insert(word: Word)

    @Query("delete from word_table")
    fun deleteAll()

    @Query("select * from word_table")
    fun getAllWords(): LiveData<List<Word>>
}