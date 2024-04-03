package com.example.kmanga.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_mangas")
data class TopMangaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
)