package com.hrap.app.da_agile.DTO;

public class Top10 {
    public String tenSP;
    public int soLuongTOP;

    public Top10(String tenSP, int soLuongTOP) {
        this.tenSP = tenSP;
        this.soLuongTOP = soLuongTOP;
    }

    public Top10() {
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuongTOP() {
        return soLuongTOP;
    }

    public void setSoLuongTOP(int soLuongTOP) {
        this.soLuongTOP = soLuongTOP;
    }
}
