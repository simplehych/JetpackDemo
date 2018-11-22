package com.simple.roomwordsample.other

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.lang.ref.WeakReference

/**
 * @author hych
 * @date 2018/11/22 13:55
 */

class TestLiveData {
    fun test(context: Context, owner: LifecycleOwner) {
        MyLiveData.getInstance(context).observe(owner, Observer<Int> { i ->
        })
    }
}


class MyLiveData(context: Context) : LiveData<Int>() {

    private val mContextWeakReference = WeakReference<Context>(context)

    companion object {

        @Volatile
        private var instance: MyLiveData? = null

        fun getInstance(context: Context): MyLiveData {
            return instance ?: synchronized(this) {
                instance ?: MyLiveData(context)
            }
        }
    }

    override fun onActive() {
        super.onActive()
        registerReceiver()
    }

    override fun onInactive() {
        super.onInactive()
        unregisterReceiver()
    }

    private fun registerReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION)
        mContextWeakReference.get()?.registerReceiver(wifiReceiver, intentFilter)
    }

    private fun unregisterReceiver() {
        mContextWeakReference.get()?.unregisterReceiver(wifiReceiver)
    }

    private val wifiReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (WifiManager.RSSI_CHANGED_ACTION == intent.action) {
                val wifiRssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI, -200)
                val wifiLevel = WifiManager.calculateSignalLevel(wifiRssi, 4)
                instance?.value = wifiLevel
            }
        }
    }

}