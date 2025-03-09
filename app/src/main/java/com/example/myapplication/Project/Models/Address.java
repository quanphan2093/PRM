package com.example.myapplication.Project.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Address implements Parcelable {
    private int AddressId, UserId;
    private String Location, PhoneNumber, Note;

    public Address() {
    }

    public Address(int addressId, int userId, String location, String phoneNumber, String note) {
        AddressId = addressId;
        UserId = userId;
        Location = location;
        PhoneNumber = phoneNumber;
        Note = note;
    }

    protected Address(Parcel in) {
        AddressId = in.readInt();
        UserId = in.readInt();
        Location = in.readString();
        PhoneNumber = in.readString();
        Note = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int addressId) {
        AddressId = addressId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(AddressId);
        dest.writeInt(UserId);
        dest.writeString(Location);
        dest.writeString(PhoneNumber);
        dest.writeString(Note);
    }
}
