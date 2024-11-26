package com.example.giaodien.Models;


public class SanPhamModel {

    String tensp,turl,mota,kichthuoc
            ,giatiengoc,giatienkm
            ,chatlieu,spkho,xuatsu;

    public SanPhamModel() {
    }

    public SanPhamModel(String tensp, String turl, String mota, String kichthuoc, String giatiengoc, String giatienkm, String chatlieu, String spkho, String xuatsu) {
        this.tensp = tensp;
        this.turl = turl;
        this.mota = mota;
        this.kichthuoc = kichthuoc;
        this.giatiengoc = giatiengoc;
        this.giatienkm = giatienkm;
        this.chatlieu = chatlieu;
        this.spkho = spkho;
        this.xuatsu = xuatsu;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    public String getGiatiengoc() {
        return giatiengoc;
    }

    public void setGiatiengoc(String giatiengoc) {
        this.giatiengoc = giatiengoc;
    }

    public String getGiatienkm() {
        return giatienkm;
    }

    public void setGiatienkm(String giatienkm) {
        this.giatienkm = giatienkm;
    }

    public String getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
    }

    public String getSpkho() {
        return spkho;
    }

    public void setSpkho(String spkho) {
        this.spkho = spkho;
    }

    public String getXuatsu() {
        return xuatsu;
    }

    public void setXuatsu(String xuatsu) {
        this.xuatsu = xuatsu;
    }
}
