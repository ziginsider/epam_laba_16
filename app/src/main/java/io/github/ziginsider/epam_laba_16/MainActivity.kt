package io.github.ziginsider.epam_laba_16

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.util.Log
import android.view.View
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
        Log.d("TAG", "[ ON LOADER FINISHED ${data?.count}]")
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        //TODO adapter.swapCursor(null)
        Log.d("TAG", "[ ON LOADER RESET ]")
    }


}
