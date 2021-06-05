package unhas.nurfaidah.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import unhas.nurfaidah.contactapp.room.Constant
import unhas.nurfaidah.contactapp.room.Contact
import unhas.nurfaidah.contactapp.room.ContactDB

class EditActivity : AppCompatActivity() {

    val db by lazy { ContactDB(this) }
    private var contactId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        setupListener()
    }

    fun setupView() {
        val intentType = intent.getIntExtra("intent_type",0)
        when(intentType) {
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                button_save.visibility = View.GONE
                getContact()
            }
        }
    }

    fun setupListener() {
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.contactDao().addContact(
                        Contact (0,edit_nama_kontak.text.toString(), edit_nomor_kontak.text.toString())
                )
                finish()
            }
        }
    }

    fun getContact() {
        contactId = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val contacts = db.contactDao().getContact(contactId)[0]
            edit_nama_kontak.setText(contacts.nama_kontak)
            edit_nomor_kontak.setText(contacts.nomor_kontak)
        }
    }
}