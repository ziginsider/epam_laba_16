package io.github.ziginsider.epam_laba_16

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

const val WRITERS = 1
const val WRITERS_ID = 2

class WritersContentProvider : ContentProvider() {

    companion object {
        private var writersProjectionMap: HashMap<String, String> = HashMap()
        private val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, DATA_BASE_TABLE_NAME, WRITERS)
            uriMatcher.addURI(AUTHORITY, "$DATA_BASE_TABLE_NAME/#", WRITERS_ID)
            for (request in DEFAULT_REQUEST) {
                writersProjectionMap[request] = request
            }
        }
    }

    private lateinit var dbHelper: DataBaseHelper

    override fun onCreate(): Boolean {
        dbHelper = DataBaseHelper(context)
        return true
    }

    override fun insert(uri: Uri?, contentValues: ContentValues?): Uri {
        val matchUri = uriMatcher.match(uri)
        if (matchUri != WRITERS) throw IllegalArgumentException("Unknown URI = $uri")
        val db = dbHelper.writableDatabase
        val values = if (contentValues != null) ContentValues(contentValues) else ContentValues()
        var rowUri = Uri.EMPTY
        val rowId = db.insert(DATA_BASE_TABLE_NAME, COLUMN_NAME_FIRST_NAME, values)
        if (rowId > 0) {
            rowUri = ContentUris.withAppendedId(CONTENT_ID_URI_BASE, rowId)
            context.contentResolver.notifyChange(rowUri, null)
        }
        return rowUri
    }

    override fun delete(uri: Uri?, where: String?, selectionArgs: Array<out String>?): Int {
        val db = dbHelper.writableDatabase
        val count: Int
        when (uriMatcher.match(uri)) {
            WRITERS -> count = db.delete(DATA_BASE_TABLE_NAME, where, selectionArgs)
            WRITERS_ID -> {
                var selection = "$_ID = ${uri?.pathSegments?.get(WRITERS_ID_PATH_POSITION)}"
                where?.let { selection = "$selection AND $where" }
                count = db.delete(DATA_BASE_TABLE_NAME, selection, selectionArgs)
            }
            else -> throw IllegalArgumentException("Unknown URI = $uri")
        }
        context.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(uri: Uri?, values: ContentValues?, where: String?,
                        selectionArgs: Array<out String>?): Int {
        val db = dbHelper.writableDatabase
        val count: Int
        when (uriMatcher.match(uri)) {
            WRITERS -> count = db.update(DATA_BASE_TABLE_NAME, values, where, selectionArgs)
            WRITERS_ID -> {
                val id = uri?.pathSegments?.get(WRITERS_ID_PATH_POSITION)
                var selection = "$_ID = $id"
                where?.let { selection = "$selection AND $where" }
                count = db.update(DATA_BASE_TABLE_NAME, values, selection, selectionArgs)
            }
            else -> throw IllegalArgumentException("Unknown URI = $uri")
        }
        context.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?,
                       selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        val qBuilder = SQLiteQueryBuilder()
        val orderBy: String
        when (uriMatcher.match(uri)) {
            WRITERS -> {
                qBuilder.tables = DATA_BASE_TABLE_NAME
                qBuilder.setProjectionMap(writersProjectionMap)
                orderBy = DEFAULT_SORT_ORDER
            }
            WRITERS_ID -> {
                qBuilder.tables = DATA_BASE_TABLE_NAME
                qBuilder.setProjectionMap(writersProjectionMap)
                qBuilder.appendWhere("$_ID = " +
                        "${uri?.pathSegments?.get(WRITERS_ID_PATH_POSITION)}")
                orderBy = DEFAULT_SORT_ORDER
            }
            else -> throw IllegalArgumentException("Unknown URI = $uri")
        }
        val db = dbHelper.readableDatabase
        val cursor = qBuilder.query(db, projection, selection, selectionArgs, null, null, orderBy)
        cursor.setNotificationUri(context.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri?) = when (uriMatcher.match(uri)) {
        WRITERS -> CONTENT_TYPE
        WRITERS_ID -> CONTENT_ITEM_TYPE
        else -> throw IllegalArgumentException("Unknown URI = $uri")
    }

    private class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATA_BASE_NAME,
            null, DATA_BASE_VERSION) {

        private val QUERY_DB_CREATE = "CREATE TABLE $DATA_BASE_TABLE_NAME " +
                "($_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME_FIRST_NAME STRING, " +
                "$COLUMN_NAME_SECOND_NAME STRING, " +
                "$COLUMN_NAME_BOOK STRING, " +
                "$COLUMN_NAME_ISBN LONG)"

        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(QUERY_DB_CREATE)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $DATA_BASE_TABLE_NAME")
            onCreate(db)
        }
    }
}