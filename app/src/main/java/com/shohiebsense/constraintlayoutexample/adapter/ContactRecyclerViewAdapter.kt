package com.shohiebsense.constraintlayoutexample.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shohiebsense.constraintlayoutexample.R
import com.shohiebsense.constraintlayoutexample.fragment.ContactListFragment
import com.shohiebsense.constraintlayoutexample.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnContactItemClickListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ContactRecyclerViewAdapter(var mValues: ArrayList<Contact>, private val mListener: ContactListFragment.OnContactItemClickListener?) : RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {

    private val LOADING = 3
    var isLoadingAdded = false
    var contactNumbers = ""
    var isSynchronized = false

    fun updateItems(contacts: ArrayList<Contact>){
        for (contact in contacts) {
            mValues.add(contact)
            notifyItemInserted(mValues.size -1)
        }
    }

    fun refreshItems(contacts: ArrayList<Contact>){
        val diffResult = DiffUtil.calculateDiff(ContactDiffUtil(mValues, contacts))
        mValues.clear()
        mValues.addAll(contacts)
        diffResult.dispatchUpdatesTo(this)
    }

    fun toggleSynchronize(){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)
        if(viewType % 2 == 0)
            view.item_contact_root.setBackgroundColor(ContextCompat.getColor(parent.context,R.color.lightGray))
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //contactNumbers = contactNumbers+ mValues[position].phoneNumber + RequestCekMemberMulti.getSeparator()
        if(position >= 70 && !isSynchronized){
            isSynchronized = true
        }
        if(position == mValues.lastIndex){
            isSynchronized = true
        }
        when(getItemViewType(position)){
            LOADING -> {
                holder.toggleLoading()
            }
            else -> {
                holder.fetch(mValues[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return  mValues.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == mValues.size -1 && isLoadingAdded) LOADING else position % 2
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        fun fetch(contact: Contact){
            if(contact.name == null){

                return
            }
            if(!true){
                //add to db
            }
            itemView.text_name.text = contact.name
            itemView.text_number.text = contact.phoneNumber
            itemView.setOnClickListener {
                mListener!!.onItemClick(contact)
            }
        }

        fun toggleLoading(){
            itemView.progress_bar_item_contact.visibility = View.VISIBLE
            itemView.text_name.visibility = View.GONE
            itemView.text_number.visibility = View.GONE
            itemView.image_isaku.visibility = View.GONE
            itemView.image_avatar.visibility = View.GONE
        }


    }

    inner class ContactDiffUtil(val oldList : List<Contact>, val newList : List<Contact>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList.get(oldItemPosition).contactId.equals(newList.get(newItemPosition).contactId)

    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        mValues.add(Contact())
        notifyItemInserted(mValues.size - 1)
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position = mValues.lastIndex
        mValues.removeAt(position)
        notifyItemRemoved(position)
    }


}
