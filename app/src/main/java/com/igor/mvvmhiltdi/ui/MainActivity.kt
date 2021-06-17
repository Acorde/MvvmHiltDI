package com.igor.mvvmhiltdi.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.igor.mvvmhiltdi.R
import com.igor.mvvmhiltdi.modules.Blog
import com.igor.mvvmhiltdi.utils.DataState
import com.igor.mvvmhiltdi.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    displayProgressBar(false)
                    handleData(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    handleError(dataState.exception.message)
                }
                is DataState.Loading -> displayProgressBar(true)
            }
        })
    }

    private fun handleError(message: String?) {
        message?.let {
            text.text = it
        } ?: kotlin.run {
            text.text = "Unknown Error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.show(isDisplayed)
    }

    private fun handleData(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }
        text.text = sb.toString()
    }
}