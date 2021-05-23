package com.example.aac_mvvm_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Add_Activity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        var edit_name: EditText = findViewById(R.id.add_edittext_name)
        var edit_number: EditText = findViewById(R.id.add_edittext_number)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        if(intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(EXTRA_CONTACT_ID) && intent.hasExtra(
                EXTRA_CONTACT_NUMBER)){
            edit_name.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            edit_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }

        val add_btn: Button = findViewById(R.id.add_button)
        add_btn.setOnClickListener { 
            val name = edit_name.text.toString().trim()
            val number = edit_number.text.toString()

            if (name.isEmpty() || number.isEmpty()){
                Toast.makeText(this, "빈값이 존재합니다.", Toast.LENGTH_SHORT).show()
            }else{
                val initial = name[0].uppercaseChar()
                val contact = Contact(id, name, number, initial)
                contactViewModel.insert(contact)
                finish()
            }
        }
    }

    companion object{
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }
}