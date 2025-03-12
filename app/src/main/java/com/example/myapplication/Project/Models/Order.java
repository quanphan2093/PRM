package com.example.myapplication.Project.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Order implements Parcelable {
    private int OrderId,UserId,PaymentId, AddressId, StatusId;
    private String OrderDate;

    public Order() {
    }

    public Order(int orderId, int userId, int paymentId, int addressId, int statusId, String orderDate) {
        OrderId = orderId;
        UserId = userId;
        PaymentId = paymentId;
        AddressId = addressId;
        StatusId = statusId;
        OrderDate = orderDate;
    }

    protected Order(Parcel in) {
        OrderId = in.readInt();
        UserId = in.readInt();
        PaymentId = in.readInt();
        AddressId = in.readInt();
        StatusId = in.readInt();
        OrderDate = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(OrderId);
        parcel.writeInt(UserId);
        parcel.writeInt(PaymentId);
        parcel.writeInt(AddressId);
        parcel.writeInt(StatusId);
        parcel.writeString(OrderDate);
    }
}
