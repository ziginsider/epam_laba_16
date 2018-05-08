package io.github.ziginsider.epam_laba_16

import android.content.ContentValues
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.view.View
import android.widget.EditText
import android.widget.Toast
import io.github.ziginsider.epam_laba_16.adapter.ListViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var listViewAdapter: ListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listViewAdapter = ListViewAdapter(this, null, 0, { removeItem(this) })
        listView.adapter = listViewAdapter
        supportLoaderManager.initLoader(0, null, this)
        initSubmitButton()
        initUpdateButton()
    }

    override fun onCreateLoader(id: Int, args: Bundle?) = CursorLoader(this, CONTENT_URI,
            DEFAULT_REQUEST, null, null, null)

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        if (data?.count!! > 0) {
            emptyListTextView.visibility = View.GONE
            listViewAdapter.swapCursor(data)
        } else {
            emptyListTextView.visibility = View.VISIBLE
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        listViewAdapter.swapCursor(null)
    }

    private fun initSubmitButton() {
        submitButton.setOnClickListener {
            insertData(
                    stringFieldForInsert(firstName, "Writer_name"),
                    stringFieldForInsert(secondName, "Second_name"),
                    stringFieldForInsert(bookName, "Title_of_book"),
                    intFieldForInsert(isbnNumber))
            cleanFields()
        }
    }

    private fun initUpdateButton() {
        updateButton.setOnClickListener {
            val result = updateData(
                    stringFieldForInsert(firstName, "Writer_name"),
                    stringFieldForInsert(secondName, "Second_name"),
                    stringFieldForInsert(bookName, "Title_of_book"),
                    intFieldForInsert(isbnNumber))
            if (!result) {
                Toast.makeText(this, "ISBN wasn't found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateData(name: String, secondName: String, book: String, isbn: Long): Boolean {
        val updateValues = ContentValues()
        with(updateValues) {
            put(COLUMN_NAME_FIRST_NAME, name)
            put(COLUMN_NAME_SECOND_NAME, secondName)
            put(COLUMN_NAME_BOOK, book)
            put(COLUMN_NAME_ISBN, isbn)
        }
        val selectionClause = "$COLUMN_NAME_ISBN = ?"
        val selectionArgs = arrayOf(isbn.toString())
        return contentResolver.update(CONTENT_URI, updateValues, selectionClause, selectionArgs) > 0
    }

    private fun cleanFields() {
        firstName.setText("")
        secondName.setText("")
        bookName.setText("")
        isbnNumber.setText("")
    }

    private fun stringFieldForInsert(field: EditText, defaultText: String = "")
            = if (field.text.isNotEmpty()) field.text.toString() else defaultText

    private fun intFieldForInsert(field: EditText, defaultInt: Long = 0L)
            = if (field.text.isNotEmpty()) field.text.toString().toLong() else defaultInt

    private fun insertData(name: String, secondName: String, book: String, isbn: Long) {
        val insertValues = ContentValues()
        with(insertValues) {
            put(COLUMN_NAME_FIRST_NAME, name)
            put(COLUMN_NAME_SECOND_NAME, secondName)
            put(COLUMN_NAME_BOOK, book)
            put(COLUMN_NAME_ISBN, isbn)
        }
        contentResolver.insert(CONTENT_URI, insertValues)
    }

    private fun removeItem(id: Int) {
        val selectionClause = "$_ID = ?"
        val selectionArgs = arrayOf(id.toString())
        contentResolver.delete(CONTENT_URI, selectionClause, selectionArgs)
    }
}
