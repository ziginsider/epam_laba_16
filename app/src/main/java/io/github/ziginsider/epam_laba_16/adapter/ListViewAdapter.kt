package io.github.ziginsider.epam_laba_16.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import io.github.ziginsider.epam_laba_16.*
import io.github.ziginsider.epam_laba_16.Contract.COLUMN_NAME_BOOK
import io.github.ziginsider.epam_laba_16.Contract.COLUMN_NAME_FIRST_NAME
import io.github.ziginsider.epam_laba_16.Contract.COLUMN_NAME_ISBN
import io.github.ziginsider.epam_laba_16.Contract.COLUMN_NAME_SECOND_NAME
import io.github.ziginsider.epam_laba_16.Contract._ID

/**
 * Adapter for list of database rows (see [WritersContentProvider.DataBaseHelper])
 *
 * implements [CursorAdapter]
 *
 * @author Alex Kisel
 * @since 2018-05-02
 */
class ListViewAdapter(context: Context, cursor: Cursor?, flags: Int,
                      private var removeClick: Int.() -> Unit = {})
    : CursorAdapter(context, cursor, flags) {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.item_view, parent, false)
        val holder = ViewHolder()
        with(holder) {
            writerName = view.findViewById(R.id.firstNameItem)
            writerSecondName = view.findViewById(R.id.secondNameItem)
            book = view.findViewById(R.id.bookItem)
            isbn = view.findViewById(R.id.isbnItem)
            img = view.findViewById(R.id.removeItemImg)
        }
        view.tag = holder
        return view
    }

    override fun bindView(view: View?, context: Context, cursor: Cursor) {
        val id = cursor.getInt(cursor.getColumnIndex(_ID))
        val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FIRST_NAME))
        val secondName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SECOND_NAME))
        val book = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BOOK))
        val isbn = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ISBN))
        val holder = view?.tag as ViewHolder?
        holder?.let {
            it.id = id
            it.writerName?.text = name
            it.writerSecondName?.text = secondName
            it.book?.text = book
            it.isbn?.text = isbn
            it.img?.setOnClickListener { onRemoveClick(id) }
        }
    }

    private class ViewHolder {

        var id: Int = 0
        var writerName: TextView? = null
        var writerSecondName: TextView? = null
        var book: TextView? = null
        var isbn: TextView? = null
        var img: ImageView? = null
    }

    private fun onRemoveClick(id: Int) {
        id.removeClick()
    }
}
