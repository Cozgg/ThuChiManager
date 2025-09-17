/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.pojo;

import java.time.LocalDate;

/**
 *
 * @author nguye
 */
public class ChiTieu {
    private int id;
    private String name;
    private LocalDate date;
    private double money;
    private String note;
    private DanhMuc danhMuc;
    private LoaiChiTieu loaiCT;

    public ChiTieu(String name, LocalDate date, double money, String note, DanhMuc danhMuc, LoaiChiTieu loaiCT) {
        this.name = name;
        this.date = date;
        this.money = money;
        this.note = note;
        this.danhMuc = danhMuc;
        this.loaiCT = loaiCT;
    }

    public ChiTieu() {
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the money
     */
    public double getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the danhMuc
     */
    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    /**
     * @param danhMuc the danhMuc to set
     */
    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    /**
     * @return the loaiCT
     */
    public LoaiChiTieu getLoaiCT() {
        return loaiCT;
    }

    /**
     * @param loaiCT the loaiCT to set
     */
    public void setLoaiCT(LoaiChiTieu loaiCT) {
        this.loaiCT = loaiCT;
    }
    
    
    
    
}
