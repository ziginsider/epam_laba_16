package io.github.ziginsider.epam_laba_16

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateLoader(id: Int, args: Bundle?) = CursorLoader(this, CONTENT_URI,
            DEFAULT_REQUEST, null, null, null)

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        //TODO adapter.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        //TODO adapter.swapCursor(null)
    }


}
