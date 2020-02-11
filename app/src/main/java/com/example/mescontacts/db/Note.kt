package com.example.mescontacts.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
val  titre: String,
val  note: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int= 0
}