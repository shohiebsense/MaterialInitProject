package com.shohiebsense.constraintlayoutexample.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.shohiebsense.constraintlayoutexample.R
import com.shohiebsense.constraintlayoutexample.model.Contact
import com.shohiebsense.constraintlayoutexample.util.Contacts
import com.shohiebsense.constraintlayoutexample.adapter.ContactRecyclerViewAdapter
import com.shohiebsense.constraintlayoutexample.view.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_contact_list.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ContactListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ContactListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactListFragment : Fragment() {

    // TODO: Customize parameters

    lateinit var search_bar : AppCompatEditText
    lateinit var image_clear : ImageView
    lateinit var currentContactList : ArrayList<Contact>
    lateinit var adapter : ContactRecyclerViewAdapter
    lateinit var registeredAdapter : ContactRecyclerViewAdapter

    private var isLoading = false
    private var isLastPage = false
    private var CURRENT_PAGE = 0
    private var TOTAL_PAGES = 0
    private var contactsOffset = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recycler_contact.layoutManager = layoutManager
        recycler_contact_registered.layoutManager = LinearLayoutManager(context)


        //currentContactList = entireContactList.subList(0,listAddition)


        search_bar = activity!!.findViewById(R.id.edit_search)
        search_bar.hint = getString(R.string.text_search_phone_number)
        image_clear = activity!!.findViewById(R.id.image_clear)

        image_clear.setOnClickListener {
            search_bar.text.clear()
        }

        search_bar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(recycler_contact.visibility == View.VISIBLE){
                    //currentContactList = Contacts.getAllContactsFromDBByName(activity!!,s.toString()) as ArrayList<Contact>
                    adapter.refreshItems(currentContactList)
                }
                else if(recycler_contact_registered.visibility == View.VISIBLE){
                    //registeredAdapter.refreshItems(Contacts.getAllRegisteredContactsFromDBByName(activity!!,s.toString()) as ArrayList<Contact>)
                }
            }
        })

        toggle_contacts.setOnToggleSwitchChangeListener { position, isChecked ->
            if(toggle_contacts.checkedTogglePosition == 0){
                recycler_contact.visibility = View.VISIBLE
                recycler_contact_registered.visibility = View.INVISIBLE
            }
            else{
                recycler_contact.visibility = View.INVISIBLE
                recycler_contact_registered.visibility = View.VISIBLE
            }
        }

        recycler_contact.addOnScrollListener(object  : PaginationScrollListener(layoutManager){
            override fun loadMoreItems() {
                isLoading = true
                CURRENT_PAGE += 1
                loadNextPage()
            }

            override fun getTotalPageCount(): Int {
                return TOTAL_PAGES
            }

            override fun isLastPage(): Boolean {
                Log.e("shohiebsense ",isLastPage.toString())
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
        loadFirst()
        loadRegisteredMember()
    }

    fun loadRegisteredMember(){
        //registeredAdapter = ContactRecyclerViewAdapter(Contacts.getRegisteredMembers(context!!) as ArrayList<Contact>,mListener)
       // recycler_contact_registered.adapter = registeredAdapter
    }

    fun loadFirst(){

        var size = Contacts.getContactListSize(context!!)
        if(size != 0){
            //currentContactList = Contacts.getAllContactsFromDB(context!!,0) as ArrayList<Contact>
        }
        else{
            currentContactList = Contacts.getContactDetails(context!!,contactsOffset)
            size = Contacts.getContactListSize(context!!)
        }
        addOffset()

        if(size >= 100){
            TOTAL_PAGES = size / 100
            Log.e("shohiebsense ","total pages "+TOTAL_PAGES)
        }
        Log.e("shohiebsense ","size "+size)


        adapter = ContactRecyclerViewAdapter(currentContactList, mListener)
        recycler_contact.adapter = adapter
        if(CURRENT_PAGE <= TOTAL_PAGES) adapter.addLoadingFooter()
        else isLastPage = true
    }

    fun loadNextPage(){
        adapter.removeLoadingFooter()
        Log.e("shohiebsense ","load next page "+contactsOffset)
        isLoading = true
        CURRENT_PAGE += 1
        //val currentContactList = Contacts.getAllContactsFromDB(context!!,contactsOffset)
        addOffset()
        adapter.updateItems(currentContactList as ArrayList<Contact>)

        if(CURRENT_PAGE < TOTAL_PAGES) adapter.addLoadingFooter()
        else {
            Log.e("shohiebsense  ","current page "+CURRENT_PAGE)
            isLastPage = true
        }
    }

    fun addOffset(){
        contactsOffset += 100
    }

    val mListener = object : OnContactItemClickListener  {
        override fun onItemClick(item: Contact) {
            val intent = Intent()
            Log.e("shohiebsense ","phone number clickedd")
            //intent.putExtra(TransferBanksAndContactsActivity.FRAGMENT_CONTACTS_PHONENUMBER_VALUE, item.phoneNumber)
            activity!!.setResult(Activity.RESULT_OK, intent)
            activity!!.finish()
        }
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnContactItemClickListener {
        // TODO: Update argument type and name
        fun onItemClick(item: Contact)
    }



}
