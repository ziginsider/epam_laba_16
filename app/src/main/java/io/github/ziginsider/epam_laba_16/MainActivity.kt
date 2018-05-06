package io.github.ziginsider.epam_laba_16

import android.content.ContentValues
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.util.Log
import android.view.View
import android.widget.EditText
import io.github.ziginsider.epam_laba_16.adapter.ListViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var listViewAdapter: ListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewAdapter = ListViewAdapter(this, null, 0)

        listView.adapter = listViewAdapter

        supportLoaderManager.initLoader(0, null, this)

        initSubmitButton()
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
        Log.d("TAG", "[ ON LOADER FINISHED ]")
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        listViewAdapter.swapCursor(null)
        Log.d("TAG", "[ ON LOADER RESET ]")
    }

    private fun initSubmitButton() {
        submitButton.setOnClickListener {
            insertData(
                    stringFieldForInsert(firstName, "Writer_name"),
                    stringFieldForInsert(secondName, "Second_name"),
                    stringFieldForInsert(bookName, "Title_of_book"),
                    intFieldForInsert(isbnNumber))
        }
    }

    private fun stringFieldForInsert(field: EditText, defaultText: String = "")
            = if (field.text.isNotEmpty()) field.text.toString() else defaultText

    private fun intFieldForInsert(field: EditText, defaultInt: Int = 0)
            = if (field.text.isNotEmpty()) field.text.toString().toInt() else defaultInt

    private fun insertData(name: String, secondName: String, book: String, isbn: Int) {
        val insertValues = ContentValues()
        insertValues.put(COLUMN_NAME_FIRST_NAME, name)
        insertValues.put(COLUMN_NAME_SECOND_NAME, secondName)
        insertValues.put(COLUMN_NAME_BOOK, book)
        insertValues.put(COLUMN_NAME_ISBN, isbn)

        val resultUri = contentResolver.insert(CONTENT_URI, insertValues)

        Log.d("TAG", "[ INSERT URI = $resultUri]")
    }
}
