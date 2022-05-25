package com.example.firebasechat.ui.dashboard

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {


    private val _imageview = MutableLiveData<String>().apply {

    }
    val imageview: LiveData<String> = _imageview
}