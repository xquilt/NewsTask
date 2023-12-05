package com.polendina.androidtasknewapp.data.database.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PublicationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PublicationsDb: RoomDatabase() {
    abstract fun applicationsDao(): PublicationsDao
    companion object {
        @Volatile
        private var INSTANCE: PublicationsDb? = null
        fun getDatabase(context: Context): PublicationsDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = PublicationsDb::class.java,
                    name = "words"
                )
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}