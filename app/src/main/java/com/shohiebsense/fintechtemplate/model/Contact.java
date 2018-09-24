package com.shohiebsense.fintechtemplate.model;


/**
 * Created by Shohiebsense on 24/01/2018.
 */

public class Contact {

    String contactId;

    String name;

    String phoneNumber;

    String photo;

    boolean isRegistered;


    public Contact(String contactId, String name, String phoneNumber, String photo, boolean isRegistered) {
        this.contactId = contactId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
        this.isRegistered = isRegistered;
    }

    public Contact() {
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}
