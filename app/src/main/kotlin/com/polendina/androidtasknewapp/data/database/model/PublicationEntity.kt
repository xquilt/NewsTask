package com.polendina.androidtasknewapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "publications"
)
data class PublicationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String?,
    val author: String?,
)
