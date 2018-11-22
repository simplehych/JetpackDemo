package com.simple.roomwordsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author hych
 * @date 2018/11/21 16:33
 */
class WordViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WordViewModel(MyApplication.instance) as T
    }
}