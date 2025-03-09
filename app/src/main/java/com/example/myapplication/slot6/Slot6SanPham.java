package com.example.myapplication.slot6;

public class Slot6SanPham {
    private String masp;
    private String tensp;
    private int sl;

    public Slot6SanPham() {
    }

    public Slot6SanPham(String masp, String tensp, int sl) {
        this.masp = masp;
        this.tensp = tensp;
        this.sl = sl;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
