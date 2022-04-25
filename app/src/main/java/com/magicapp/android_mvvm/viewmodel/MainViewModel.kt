package com.magicapp.android_mvvm.viewmodel

import android.view.TouchDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magicapp.android_mvvm.model.Post
import com.magicapp.android_mvvm.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val allPosts = MutableLiveData<ArrayList<Post>>()
    val deletePost = MutableLiveData<Post>()

    fun apiPostList() {
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>
            ) {
                allPosts.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                allPosts.value = null
            }
        })
    }

    fun apiPostDelete(post: Post) {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                deletePost.value = response.body()
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                deletePost.value = null
            }
        })
    }
}