package com.example.miky.dagger.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.miky.dagger.data.Joke
import com.example.miky.dagger.data.JokeRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) {

    var jokeListLiveData = MutableLiveData<List<Joke>>()

    init {
        Log.i("miky", "Main view model init")
        refreshList()
    }

    fun refreshList() {
        jokeRepository.requestList {
            Log.i("miky", "get joke list is success")
            jokeListLiveData.value = it
        }
    }
}