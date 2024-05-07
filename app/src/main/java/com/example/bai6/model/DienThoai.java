package com.example.bai6.model;

public class DienThoai {
    private int img;
    private String ten,xuatsu;
    double gia;

    public DienThoai(int img, String ten,  String xuatsu, double gia) {
        this.img = img;
        this.ten = ten;
        this.xuatsu = xuatsu;
        this.gia = gia;
    }

    public DienThoai() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getXuatsu() {
        return xuatsu;
    }

    public void setXuatsu(String xuatsu) {
        this.xuatsu = xuatsu;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
}
