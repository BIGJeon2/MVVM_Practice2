package com.example.aac_mvvm_practice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
//파라미터를 context가 아닌 application을 사용하는 이유 :
//context사용시 해당 액티비티 수명주기가 destroy되면 메모리 leak이 발생하기 때문
class ContactViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>>{
        return this.contacts
    }

    fun insert(contact: Contact){
        repository.insert(contact)
    }

    fun delete(contact: Contact){
        repository.delete(contact)
    }
}