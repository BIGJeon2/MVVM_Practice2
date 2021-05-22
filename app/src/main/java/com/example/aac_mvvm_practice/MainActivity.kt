package com.example.aac_mvvm_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore

class MainActivity : AppCompatActivity() {

    private lateinit var ViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        ViewModel.getAll().observe(this, Observer<List<Contact>> { contcts ->
            //UI업데이트 추후 rcv.notifyDataChagned() 넣어줄 예정임
        })
    }
}