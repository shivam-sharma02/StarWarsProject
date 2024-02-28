package com.example.starwars

import android.net.http.HttpException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setuprecyclerview()

        lifecycleScope.launch {
            try {
                val response = StarAPI.instance.getPeople() // Make API request
                if (response.isSuccessful) { // Check if request was successful
                    val resultList = response.body() // Get list of results from response
                    adapter.differ.submitList(resultList?.results) // Update adapter with data
                } else {
                    Log.e(TAG, "Request failed with code shivam: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e(TAG, "IOException: ${e.message}")
            } catch (e: retrofit2.HttpException) {
                Log.e(TAG, "HttpException: ${e.message}")
            }
        }

    }

    private fun setuprecyclerview(){
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        adapter  = Adapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
    }
}

