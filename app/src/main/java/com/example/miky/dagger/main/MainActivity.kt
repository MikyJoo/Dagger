package com.example.miky.dagger.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.miky.dagger.Application
import com.example.miky.dagger.R
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
