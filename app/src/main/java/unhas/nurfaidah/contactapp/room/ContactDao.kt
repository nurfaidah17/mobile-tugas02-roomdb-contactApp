package unhas.nurfaidah.contactapp.room

import androidx.room.*

@Dao
interface ContactDao {

    @Insert
    suspend fun addContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact")
    suspend fun getContacts(): List<Contact>
}