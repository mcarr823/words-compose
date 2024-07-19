package dev.mcarr.words.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.mcarr.words.data.daos.WordDao
import dev.mcarr.words.data.entities.Word

@Database(entities = [
    Word::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        const val DATABASE_NAME = "AppDatabase"

        @Volatile private var instance: AppDatabase? = null

        fun instantiate(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build().also { instance = it }
            }
    }

}
