package com.example.aac_mvvm_practice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "number") var number: String,
    @ColumnInfo(name = "initial") var initial: Char
){
    //\u0000 -> char형의 null처리
    constructor() : this(null, "", "", '\u0000')
}