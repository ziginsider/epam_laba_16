package io.github.ziginsider.epam_laba_16

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

const val WRITERS = 1
const val WRITERS_ID = 2

class WritersContentProvider: ContentProvider() {

    companion object {
        private lateinit var writersRequestMap: HashMap<String, String>
        private val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, WRITERS)
            uriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", WRITERS_ID)

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

}