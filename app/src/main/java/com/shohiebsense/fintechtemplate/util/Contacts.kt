package com.shohiebsense.fintechtemplate.util

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import com.shohiebsense.fintechtemplate.model.Contact


/**
 * Created by Shohiebsense on 25/01/2018.
 */
object Contacts {

    var memberPhoneNumberRequest = ""

    fun getContacts(context : Context) : List<Contact> {
        val contacts = arrayListOf<Contact>()
        val contentResolver = context.contentResolver
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,
                ContactsContract.Contacts.HAS_PHONE_NUMBER + " = 1",null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC")
        if(cursor.count > 0){
            while (cursor.moveToNext()){
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                if(cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0){
                    val cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?", arrayOf(id),null)

                    val personContentUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id.toLong())
                    val photoUri = Uri.withAppendedPath(personContentUri,ContactsContract.Contacts.Photo.CONTENT_DIRECTORY)
                    while(cursorInfo.moveToNext()){
                        /* val contact = Contact(id,cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                                 cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                                 photoUri,false)
                         contacts.add(contact)*/
                    }
                    cursorInfo.close()
                }
            }
            cursor.close()
        }
        return contacts
    }



    fun getContactDetails(context: Context, currentOffset: Int): ArrayList<Contact>{

        val contentResolver = context.contentResolver
        val contacts = arrayListOf<Contact>()
        /*  val phones = contentResolver.query(
                  ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
                  ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY+" ASC")*/
        val phones = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY+" ASC")
        val mobileNoSet = HashSet<String>()


        while (phones!!.moveToNext()) {
            val name = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER))
            val email = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS))
            val imagUri = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI))

            val id = phones.getColumnIndex(ContactsContract.Contacts._ID)

            if (mobileNoSet.add(phoneNumber) && phoneNumber != null) {
                var simplifiedPhoneNumber = AppUtil.convertIndonesianCode(phoneNumber)
                //dbHelper.contactDao.create(Contact(id.toString(),name,simplifiedPhoneNumber,imagUri,false))
                contacts.add(Contact(id.toString(), name, simplifiedPhoneNumber,  imagUri,false ))
            }
        }
        phones.close()
        return contacts;
    }

   fun getContactListSize(context: Context) : Int{
        //return DatabaseHelper.getInstance(context).allContactsSize
        return getContactDetails(context,0).size
    }

    /* fun getRegisteredMembers(context: Context) : MutableList<Contact>{
        //return DatabaseHelper.getInstance(context).isakuContacts
    }*/

   /* fun updateRegisteredMembers(context: Context, contactList: List<ResponseContact>){
        val dbHelper = DatabaseHelper.getInstance(context)

        for (responnseContact in contactList){
            if(!responnseContact.memberStatus.equals("member tidak ditemukan",true)){
                dbHelper.updateRegisteredContact(responnseContact.phoneNumber)
            }
        }
    }

    fun getAllContactsFromDB(context: Context, offset : Int) : MutableList<Contact> {
        return DatabaseHelper.getInstance(context).getAllContacts(offset)
    }

    fun getAllContactsFromDBByName(context: Context, searchKey : String) : MutableList<Contact> {
        return DatabaseHelper.getInstance(context).getAllContactsByName(searchKey)
    }

    fun getAllRegisteredContactsFromDBByName(context: Context, searchKey : String) : MutableList<Contact> {
        return DatabaseHelper.getInstance(context).getRegisteredContactsByName(searchKey)
    }
*/


    //


    fun getContactDetailsByName(context: Context, key : String): ArrayList<Contact>{

        val contentResolver = context.contentResolver
        val contacts = arrayListOf<Contact>()
        val selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+"like'%" + key +"%'"
        val phones = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, selection, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC")
        val mobileNoSet = HashSet<String>()

        while (phones!!.moveToNext()) {
            val name = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER))
            val email = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS))
            val imagUri = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI))

            val id = phones.getColumnIndex(ContactsContract.Contacts._ID)

            if (mobileNoSet.add(phoneNumber)) {

                contacts.add(Contact(id.toString(), name, phoneNumber,  imagUri, false ))
                mobileNoSet.add(phoneNumber)
            }
        }
        phones.close()
        return contacts
    }
}