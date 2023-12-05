package com.polendina.androidtasknewapp.data.database.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PublicationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(publication: PublicationEntity): Long

    @Query("SELECT * FROM PUBLICATIONS")
    suspend fun getAllPublications(): List<PublicationEntity>

    @Delete
    suspend fun deletePublication(publication: PublicationEntity)
}