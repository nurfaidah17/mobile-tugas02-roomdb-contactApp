package unhas.nurfaidah.contactapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val nama_kontak: String,
        val nomor_kontak: Int
)