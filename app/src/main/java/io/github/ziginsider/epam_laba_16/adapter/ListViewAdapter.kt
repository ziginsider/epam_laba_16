package io.github.ziginsider.epam_laba_16.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import io.github.ziginsider.epam_laba_16.*

class ListViewAdapter(val context: Context, cursor: Cursor, val flags: Int)
    : CursorAdapter(context, cursor, flags) {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    //inflater = LayoutInflater.from(context)


    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.item_view, parent, false)
        val holder = ViewHolder()

        view.tag = holder
        return view
    }

    override fun bindView(view: View?, context: Context, cursor: Cursor) {
        val id = cursor.getLong(cursor.getColumnIndex(_ID))
        val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FIRST_NAME))
        val secondName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SECOND_NAME))
        val book = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BOOK))
        val isbn = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ISBN))
        val holder = view?.tag as ViewHolder?
        holder?.let {
            it.id = id
            it.writerName.text = name
            it.writerSecondName.text = secondName
            it.book.text = book
            it.isbn.text = isbn
        }
    }

    private class ViewHolder {
        var id: Long = 0
        lateinit var writerName: TextView
        lateinit var writerSecondName: TextView
        lateinit var book: TextView
        lateinit var isbn: TextView
    }

}