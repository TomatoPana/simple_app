package com.mdlb.basicapplication.databases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registers")
data class Register(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "first_names") val firstNames: String,
)