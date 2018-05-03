package io.github.ziginsider.epam_laba_16

import android.net.Uri

const val AUTHORITY = "io.github.ziginsider.epam_laba_16.ContractKt"
const val SCHEME = "content://"
const val PATH_WRITERS = "/writers"
const val PATH_WRITERS_ID = "/writers/"

const val WRITERS_ID_PATH_POSITION = 1
val CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH_WRITERS)
val CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_WRITERS_ID)

const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.io.ziginsider.writers"
const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.io.ziginsider.writers"

const val DATA_BASE_VERSION = 1
const val DATA_BASE_NAME = "writers_db"
const val DATA_BASE_TABLE_NAME = "writers_table"
const val COLUMN_NAME_ID = "_id"
const val COLUMN_NAME_FIRST_NAME = "first_name"
const val COLUMN_NAME_SECOND_NAME = "second_name"
const val COLUMN_NAME_BOOK = "book_name"
const val COLUMN_NAME_ISBN = "isbn"
const val DEFAULT_SORT_ORDER = "book_name ASC"
val DEFAULT_REQUEST = arrayOf(
        COLUMN_NAME_ID,
        COLUMN_NAME_FIRST_NAME,
        COLUMN_NAME_SECOND_NAME,
        COLUMN_NAME_BOOK,
        COLUMN_NAME_ISBN)

