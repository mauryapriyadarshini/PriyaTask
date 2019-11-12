package com.example.tarams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tarams.model.Article
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.no_internet_layout.*

const val PAGE_SIZE = 10
private const val INITIAL_PAGE_SIZE = 1

class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsLayoutManager: LinearLayoutManager
    private var newsRecyclerViewAdapter: NewsRecyclerViewAdapter? = null
    private var pageIndex: Int = 1

    private lateinit var scrollListener: RecyclerView.OnScrollListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        newsRecyclerViewAdapter =
            NewsRecyclerViewAdapter(object : NewsRecyclerViewAdapter.CallBack {
                override fun clickCallBack(item: Article) {
                    val intent = Intent(this@MainActivity, NewsDetailActivity::class.java)
                    intent.putExtra("object", item)
                    startActivity(intent)
                }
            })

        recyclerView.apply {
            layoutManager = newsLayoutManager
            adapter = newsRecyclerViewAdapter
        }

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        loadData(INITIAL_PAGE_SIZE)
        setRecyclerViewScrollListener()
    }

    private fun loadData(page: Int) {
        newsViewModel.getNewsLists("us", page, PAGE_SIZE).observe(this, Observer {
            if(it == null) {
                noInternetLayout.isGone = false
                recyclerView.isGone = true
            } else {
                if (it.status == "ok") {
                    it.articles?.let { list ->
                        pageIndex++
                        newsRecyclerViewAdapter?.updateNewsList(list as MutableList<Article>)
                    }
                }
            }
        })
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val visibleCount = newsLayoutManager.childCount
                val totalItemCount = newsLayoutManager.itemCount
                val firstVisibleItemPosition = newsLayoutManager.findFirstVisibleItemPosition()

                if (!newsViewModel.isLastPage()) {
                    if ((visibleCount +firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                        loadData(pageIndex)
                    }
                }

            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }
}
