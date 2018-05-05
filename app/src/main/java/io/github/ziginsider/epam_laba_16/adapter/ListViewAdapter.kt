package io.github.ziginsider.epam_laba_16.adapter

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter

class ListViewAdapter(val context: Context, cursor: Cursor, val flags: Int)
    : CursorAdapter(context, cursor, flags) {
    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup?): View {
        
    }

    override fun bindView(view: View?, context: Context, cursor: Cursor) {

    }

}