package com.simple.roomwordsample

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author hych
 * @date 2018/11/21 16:33
 */
class WordViewModelFactory(
    private val context: Context

) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WordViewModel(context) as T
    }
}