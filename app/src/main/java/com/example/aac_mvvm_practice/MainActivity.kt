package com.example.aac_mvvm_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var ViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = Contact_Adapter({contact ->
            Add_Intent(contact)
        }, {contact ->
            delete_Dialog(contact)
        })
        val layoutManager = LinearLayoutManager(this)
        val rcv: RecyclerView = findViewById(R.id.main_recycleview)
        rcv.adapter = adapter
        rcv.layoutManager = layoutManager
        rcv.setHasFixedSize(true)



        ViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        ViewModel.getAll().observe(this, { contacts ->
            //UI업데이트 추후 rcv.notifyDataChagned() 넣어줄 예정임
            adapter.Set_Contact(contacts)
        })

        val add_btn: Button = findViewById(R.id.main_button)
        add_btn.setOnClickListener {
            val intent = Intent(this, Add_Activity::class.java)
            startActivity(intent)
        }
    }
    private fun delete_Dialog(contact: Contact){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("삭제하시겠습니까?")
            .setNegativeButton("아니요") {_, _ -> }
            .setPositiveButton("네") {_, _ ->
                ViewModel.delete(contact)
            }
        builder.show()
    }

    private fun Add_Intent(contact: Contact){
        val intent = Intent(this,Add_Activity::class.java)
        intent.putExtra(Add_Activity.EXTRA_CONTACT_NAME, contact.name)
        intent.putExtra(Add_Activity.EXTRA_CONTACT_NUMBER, contact.number)
        intent.putExtra(Add_Activity.EXTRA_CONTACT_ID, contact.id)
        startActivity(intent)
    }
}