package com.simple.roomwordsample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 创建实体类
 *
 * @author hych
 * @date 2018/11/21 09:28
 */

@Entity(tableName = "word_table")
data class Word(
    @ColumnInfo(name = "word")
    val word: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}