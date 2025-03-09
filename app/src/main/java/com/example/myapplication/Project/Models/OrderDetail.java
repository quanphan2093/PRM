package com.example.myapplication.Project.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class OrderDetail implements Parcelable {
    private int OrderDetailId, OrderId,ProdId, Quantity;
    private float Price;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, int orderId, int prodId, int quantity, float price) {
        OrderDetailId = orderDetailId;
        OrderId = orderId;
        ProdId = prodId;
        Quantity = quantity;
        Price = price;
    }

    protected OrderDetail(Parcel in) {
        OrderDetailId = in.readInt();
        OrderId = in.readInt();
        ProdId = in.readInt();
        Quantity = in.readInt();
        Price = in.readFloat();
    }

    public static final Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {
        @Override
        public OrderDetail createFromParcel(Parcel in) {
            return new OrderDetail(in);
        }

        @Override
        public OrderDetail[] newArray(int size) {
            return new OrderDetail[size];
        }
    };

    public int getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        OrderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getProdId() {
        return ProdId;
    }

    public void setProdId(int prodId) {
        ProdId = prodId;
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

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(OrderDetailId);
        dest.writeInt(OrderId);
        dest.writeInt(ProdId);
        dest.writeInt(Quantity);
        dest.writeFloat(Price);
    }
}
