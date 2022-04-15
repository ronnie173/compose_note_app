package com.appsian.composenoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appsian.composenoteapp.model.Note
import com.appsian.composenoteapp.utils.DateConverter
import com.appsian.composenoteapp.utils.UUIDConverter

@Database(entities = [Note::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao
}