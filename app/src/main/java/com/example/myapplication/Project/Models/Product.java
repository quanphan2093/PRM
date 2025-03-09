package com.example.myapplication.Project.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private int ProdId, Quantity, CateId;
    private String ProdName, ProdDetail, Image, CateName;
    private float Price, UnitPrice;

    public Product() {
    }

    public Product(int prodId, int quantity, int cateId, String prodName, String prodDetail, String image, String cateName, float price, float unitPrice) {
        ProdId = prodId;
        Quantity = quantity;
        CateId = cateId;
        ProdName = prodName;
        ProdDetail = prodDetail;
        Image = image;
        CateName = cateName;
        Price = price;
        UnitPrice = unitPrice;
    }

    protected Product(Parcel in) {
        ProdId = in.readInt();
        Quantity = in.readInt();
        CateId = in.readInt();
        ProdName = in.readString();
        ProdDetail = in.readString();
        Image = in.readString();
        CateName = in.readString();
        Price = in.readFloat();
        UnitPrice = in.readFloat();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public int getCateId() {
        return CateId;
    }

    public void setCateId(int cateId) {
        CateId = cateId;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        ProdName = prodName;
    }

    public String getProdDetail() {
        return ProdDetail;
    }

    public void setProdDetail(String prodDetail) {
        ProdDetail = prodDetail;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        UnitPrice = unitPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(ProdId);
        dest.writeInt(Quantity);
        dest.writeInt(CateId);
        dest.writeString(ProdName);
        dest.writeString(ProdDetail);
        dest.writeString(Image);
        dest.writeString(CateName);
        dest.writeFloat(Price);
        dest.writeFloat(UnitPrice);
    }
}
