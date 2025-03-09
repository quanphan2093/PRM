package com.example.myapplication.Project.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Order implements Parcelable {
    private int OrderId,UserId,PaymentId, AddressId;
    private String OrderDate;

    public Order() {
    }

    public Order(int orderId, int userId, int paymentId, int addressId, String orderDate) {
        OrderId = orderId;
        UserId = userId;
        PaymentId = paymentId;
        AddressId = addressId;
        OrderDate = orderDate;
    }

    protected Order(Parcel in) {
        OrderId = in.readInt();
        UserId = in.readInt();
        PaymentId = in.readInt();
        AddressId = in.readInt();
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

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }

    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int addressId) {
        AddressId = addressId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(OrderId);
        dest.writeInt(UserId);
        dest.writeInt(PaymentId);
        dest.writeInt(AddressId);
        dest.writeString(OrderDate);
    }
}
