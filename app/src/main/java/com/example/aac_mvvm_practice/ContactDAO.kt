package com.example.aac_mvvm_practice

import android.icu.text.Replaceable
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDAO {

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getALL() : LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}