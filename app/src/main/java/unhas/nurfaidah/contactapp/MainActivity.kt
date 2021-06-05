package unhas.nurfaidah.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import unhas.nurfaidah.contactapp.room.Contact
import unhas.nurfaidah.contactapp.room.ContactDB

class MainActivity : AppCompatActivity() {

    val db by lazy { ContactDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val contacts = db.contactDao().getContacts()
            Log.d("MainActivity", "dbResponse: $contacts")
        }
    }

    fun setupListener() {
        button_create.setOnClickListener() {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }
}