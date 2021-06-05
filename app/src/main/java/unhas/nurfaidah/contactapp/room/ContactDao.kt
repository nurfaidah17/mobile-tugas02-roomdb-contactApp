package unhas.nurfaidah.contactapp.room

import androidx.room.*

@Dao
interface ContactDao {

    @Insert
    fun addContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact")
    suspend fun getContacts(): List<Contact>

    @Query("SELECT * FROM contact WHERE id=:contact_id")
    suspend fun getContact(contact_id: Int): List<Contact>
}