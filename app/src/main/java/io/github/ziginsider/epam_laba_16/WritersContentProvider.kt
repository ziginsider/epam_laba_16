package io.github.ziginsider.epam_laba_16

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri

const val WRITERS = 1
const val WRITERS_ID = 2

class WritersContentProvider: ContentProvider() {

    companion object {
        private lateinit var writersRequestMap: HashMap<String, String>
        private val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, DATA_BASE_TABLE_NAME, WRITERS)
            uriMatcher.addURI(AUTHORITY, "$DATA_BASE_TABLE_NAME/#", WRITERS_ID)

            for (request in DEFAULT_REQUEST) {
                writersRequestMap.put(request, request)
            }
        }
    }

















    override fun delete(p0: Uri?, p1: String?, p2: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(p0: Uri?, p1: ContentValues?): Uri {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(p0: Uri?, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?): Cursor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(p0: Uri?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private class DataBaseHelper(val context: Context): SQLiteOpenHelper(context, DATA_BASE_NAME,
            null, DATA_BASE_VERSION) {
        private val DB_NAME = DATA_BASE_NAME
        private val TABLE_NAME = DATA_BASE_TABLE_NAME
        private val ID = COLUMN_NAME_ID
        private val FIRST_NAME = COLUMN_NAME_FIRST_NAME
        private val SECOND_NAME = COLUMN_NAME_SECOND_NAME
        private val BOOK = COLUMN_NAME_BOOK
        private val ISBN = COLUMN_NAME_ISBN

        private val QUERY_DB_CREATE
                = "CREATE TABLE $TABLE_NAME " +
                "($ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$FIRST_NAME STRING, " +
                "$SECOND_NAME STRING, " +
                "$BOOK STRING, " +
                "$ISBN INTEGER)"

        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(QUERY_DB_CREATE)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }

    }
}