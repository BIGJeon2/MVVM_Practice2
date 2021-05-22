package com.example.aac_mvvm_practice

import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception

class ContactRepository(application: Application) {

    private val contactDatabase: ContactDatabase= ContactDatabase.getInstance(application)!!
    private val contactDAO: ContactDAO = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDAO.getALL()

    fun getAll(): LiveData<List<Contact>>{
        return contacts
    }
    //메인 스레드에서 RoomDB에 접근 시도시 메인 UI 화면이 오랫동안 멈춰있을수 있습니다 라는 에러가 뜨기때문에
    //별도의 스레드를 이용해 접근해줘야한다.
    fun insert(contact: Contact){
        try {
            val thread = Thread(Runnable {
                contactDAO.insert(contact)
            })
            thread.start()
        }catch (e: Exception){

        }
    }
    fun delete(contact: Contact){
        try {
            val thread = Thread(Runnable {
                contactDAO.delete(contact)
            })
            thread.start()
        }catch (e: Exception){

        }
    }
}