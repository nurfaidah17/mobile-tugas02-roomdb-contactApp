package unhas.nurfaidah.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import unhas.nurfaidah.contactapp.room.Contact
import unhas.nurfaidah.contactapp.room.ContactDB

class EditActivity : AppCompatActivity() {

    val db by lazy { ContactDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupListener()
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
}