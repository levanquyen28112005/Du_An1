package com.example.giaodien.Models;


public class KhuyenMaiModel {

    String tenkm,hansudung,loaikhachhang,sotiengiam;

    public KhuyenMaiModel() {
    }

    public KhuyenMaiModel(String tenkm, String hansudung, String loaikhachhang, String sotiengiam) {
        this.tenkm = tenkm;
        this.hansudung = hansudung;
        this.loaikhachhang = loaikhachhang;
        this.sotiengiam = sotiengiam;
    }

    public String getTenkm() {
        return tenkm;
    }

    public void setTenkm(String tenkm) {
        this.tenkm = tenkm;
    }

    public String getHansudung() {
        return hansudung;
    }

    public void setHansudung(String hansudung) {
        this.hansudung = hansudung;
    }

    public String getLoaikhachhang() {
        return loaikhachhang;
    }

    public void setLoaikhachhang(String loaikhachhang) {
        this.loaikhachhang = loaikhachhang;
    }

    public String getSotiengiam() {
        return sotiengiam;
    }

    public void setSotiengiam(String sotiengiam) {
        this.sotiengiam = sotiengiam;
    }
}
