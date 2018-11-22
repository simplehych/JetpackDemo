package com.simple.roomwordsample

import android.app.Application

/**
 * @author hych
 * @date 2018/11/22 15:09
 */
class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}