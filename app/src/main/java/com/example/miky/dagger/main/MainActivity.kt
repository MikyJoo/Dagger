package com.example.miky.dagger.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.miky.dagger.Application
import com.example.miky.dagger.R
import com.example.miky.dagger.data.Joke
import com.example.miky.dagger.data.JokeRepository
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    private var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("miky", "Main activity start")
        (applicationContext as Application).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.main_srl)
        swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.refreshList()
        }

        var recyclerView = findViewById<RecyclerView>(R.id.joke_list)
        recyclerView.layoutManager = layoutManager

        mainViewModel.jokeListLiveData.observe(this, Observer {

            Log.i("miky", "joke list is changed~!")
            swipeRefreshLayout.isRefreshing = false
            recyclerView.adapter = MainAdapter(this, it)
        })

    }
}

class MainViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
): ViewModel() {

    var jokeList: List<Joke> = jokeRepository.getList()
    var jokeListLiveData = MutableLiveData<List<Joke>>()

    init {
        Log.i("miky", "Main view model init")
        refreshList()
    }

    fun refreshList() {
        jokeRepository.requestList {
//            jokeList = it
            Log.i("miky", "get joke list is success")
            jokeListLiveData.value = it
        }
    }

}