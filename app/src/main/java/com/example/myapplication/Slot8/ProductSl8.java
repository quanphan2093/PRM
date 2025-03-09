package com.example.myapplication.Slot8;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ProductSl8 implements Parcelable {
    private String search_image;
    private String styleid;
    private String price;
    private String product_additional_info;

    public ProductSl8() {
    }
    public ProductSl8(String search_image, String styleid, String price, String product_additional_info) {
        this.search_image = search_image;
        this.styleid = styleid;
        this.price = price;
        this.product_additional_info = product_additional_info;
    }

    protected ProductSl8(Parcel in) {
        search_image = in.readString();
        styleid = in.readString();
        price = in.readString();
        product_additional_info = in.readString();
    }

    public static final Creator<ProductSl8> CREATOR = new Creator<ProductSl8>() {
        @Override
        public ProductSl8 createFromParcel(Parcel in) {
            return new ProductSl8(in);
        }

        @Override
        public ProductSl8[] newArray(int size) {
            return new ProductSl8[size];
        }
    };

    public String getSearch_image() {
        return search_image;
    }

    public void setSearch_image(String search_image) {
        this.search_image = search_image;
    }

    public String getStyleid() {
        return styleid;
    }

    public void setStyleid(String styleid) {
        this.styleid = styleid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_additional_info() {
        return product_additional_info;
    }

    public void setProduct_additional_info(String product_additional_info) {
        this.product_additional_info = product_additional_info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(search_image);
        dest.writeString(styleid);
        dest.writeString(price);
        dest.writeString(product_additional_info);
    }
}
