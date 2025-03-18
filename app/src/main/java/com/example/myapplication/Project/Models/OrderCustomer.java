package com.example.myapplication.Project.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class OrderCustomer implements Parcelable {
    private int OrderId,UserId,StatusId;
    private String OrderDate, ProductName, PaymentMethod, Location, PhoneNumber, Note, Status;
    private int Quantity;
    private float Price;

    public OrderCustomer() {
    }

    public OrderCustomer(int orderId, int statusId, String status, int userId, String orderDate, String productName, String paymentMethod, String location, String phoneNumber, String note, int quantity, float price) {
        OrderId = orderId;
        UserId = userId;
        OrderDate = orderDate;
        ProductName = productName;
        PaymentMethod = paymentMethod;
        Status= status;
        Location = location;
        PhoneNumber = phoneNumber;
        Note = note;
        Quantity = quantity;
        Price = price;
        StatusId = statusId;
    }

    protected OrderCustomer(Parcel in) {
        OrderId = in.readInt();
        UserId = in.readInt();
        OrderDate = in.readString();
        ProductName = in.readString();
        PaymentMethod = in.readString();
        Location = in.readString();
        PhoneNumber = in.readString();
        Note = in.readString();
        Quantity = in.readInt();
        Price = in.readFloat();
        Status=in.readString();
        StatusId = in.readInt();
    }

    public static final Creator<OrderCustomer> CREATOR = new Creator<OrderCustomer>() {
        @Override
        public OrderCustomer createFromParcel(Parcel in) {
            return new OrderCustomer(in);
        }

        @Override
        public OrderCustomer[] newArray(int size) {
            return new OrderCustomer[size];
        }
    };

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

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

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(OrderId);
        parcel.writeInt(UserId);
        parcel.writeString(OrderDate);
        parcel.writeString(ProductName);
        parcel.writeString(PaymentMethod);
        parcel.writeString(Location);
        parcel.writeString(PhoneNumber);
        parcel.writeString(Note);
        parcel.writeInt(Quantity);
        parcel.writeFloat(Price);
        parcel.writeString(Status);
        parcel.writeInt(StatusId);
    }
}
