package com.simple.roomwordsample

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * @author hych
 * @date 2018/11/21 09:52
 */

@Database(entities = [Word::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var instance: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppRoomDatabase {
            return Room.databaseBuilder(context, AppRoomDatabase::class.java, "database_name")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        PopulateDbAsync(instance).execute()
                    }
                }).build()
        }
    }
}

class PopulateDbAsync(private val db: AppRoomDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    override fun doInBackground(vararg params: Unit?) {
        db?.let {
            val wordDao = db.wordDao()
            wordDao.deleteAll()
            wordDao.insert(Word("Hello"))
            wordDao.insert(Word("Word"))
        }
    }
}