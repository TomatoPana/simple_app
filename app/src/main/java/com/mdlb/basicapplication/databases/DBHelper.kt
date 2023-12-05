package com.mdlb.basicapplication.databases

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper private constructor (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                FIRST_NAME + " TEXT," +
                LAST_NAME + " TEXT," +

                ")")

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getCursor(): Cursor? {
        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getData(): String {
        val cursor = getCursor()

        cursor?.close()

        return ""
    }

    fun addRegister(
        firstName: String,
        lastName: String,
        birthDate: String,
        timeDate: String?,
        gender: String,
        isOtherGender: Boolean,
        level: Boolean,
        hasComment: Boolean,
        comment: String?,
        preference01: Boolean,
        preference02: Boolean,
        preference03: Boolean,
        preference04: Boolean,
        preference05: Boolean,
        preference06: Boolean,
        preference07: Boolean,
        preference08: Boolean,
        preference09: Boolean
    ) {
        val values = ContentValues()

        values.put(FIRST_NAME, firstName)
        values.put(LAST_NAME, lastName)
        values.put(BIRTH_DATE, birthDate)
        values.put(TIME_DATE, timeDate)
        values.put(GENDER, gender)
        values.put(IS_OTHER_GENDER, isOtherGender)
        values.put(LEVEL, level)
        values.put(HAS_COMMENT, hasComment)
        values.put(COMMENT, comment)
        values.put(PREFERENCE_01, preference01)
        values.put(PREFERENCE_02, preference02)
        values.put(PREFERENCE_03, preference03)
        values.put(PREFERENCE_04, preference04)
        values.put(PREFERENCE_05, preference05)
        values.put(PREFERENCE_06, preference06)
        values.put(PREFERENCE_07, preference07)
        values.put(PREFERENCE_08, preference08)
        values.put(PREFERENCE_09, preference09)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        
        db.close()
    }

    companion object {

        private var Instance: DBHelper? = null

        fun getInstance(context: Context): DBHelper {
            return Instance ?: DBHelper(context, null)
        }

        private const val DATABASE_NAME = "DATA"

        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "data_table"

        const val ID = "id"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val BIRTH_DATE = "birth_date"
        const val TIME_DATE = "time_date"
        const val GENDER = "gender"
        const val IS_OTHER_GENDER = "is_other_gender"
        const val LEVEL = "level"
        const val HAS_COMMENT = "has_comment"
        const val COMMENT = "comment"
        const val PREFERENCE_01 = "preference_01"
        const val PREFERENCE_02 = "preference_02"
        const val PREFERENCE_03 = "preference_03"
        const val PREFERENCE_04 = "preference_04"
        const val PREFERENCE_05 = "preference_05"
        const val PREFERENCE_06 = "preference_06"
        const val PREFERENCE_07 = "preference_07"
        const val PREFERENCE_08 = "preference_08"
        const val PREFERENCE_09 = "preference_09"
    }
}