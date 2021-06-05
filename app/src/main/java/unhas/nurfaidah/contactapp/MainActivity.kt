package unhas.nurfaidah.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import unhas.nurfaidah.contactapp.room.Contact
import unhas.nurfaidah.contactapp.room.ContactDB

class MainActivity : AppCompatActivity() {

    val db by lazy { ContactDB(this) }
    lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val contacts = db.contactDao().getContacts()
            Log.d("MainActivity", "dbResponse: $contacts")
            withContext(Dispatchers.Main) {
                contactAdapter.setData(contacts)
            }
        }
    }

    fun setupListener() {
        button_create.setOnClickListener() {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        contactAdapter = ContactAdapter(arrayListOf())
        list_contact.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = contactAdapter
        }
    }
}