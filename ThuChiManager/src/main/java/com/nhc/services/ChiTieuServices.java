/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services;

import com.nhc.pojo.ChiTieu;
import com.nhc.pojo.DanhMuc;
import com.nhc.pojo.LoaiChiTieu;
import com.nhc.utils.MyConnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.nhc.services.DanhMucServices;
import com.nhc.services.LoaiChiTieuServices;

/**
 *
 * @author nguye
 */
public class ChiTieuServices {

    public List<ChiTieu> list() throws SQLException {
        Connection conn = MyConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareStatement("SELECT * FROM chitieu");

        ResultSet rs = stm.executeQuery();

        List<ChiTieu> chiTieus = new ArrayList<>();

        while (rs.next()) {
            ChiTieu ct = new ChiTieu();
            ct.setId(rs.getInt("id"));
            ct.setName(rs.getString("name"));
            ct.setDate(rs.getDate("date").toLocalDate());
            ct.setMoney(rs.getDouble("money"));
            ct.setNote(rs.getString("ghichu"));
            int id_danhmuc = rs.getInt("id_danhmuc");
            int id_loaict = rs.getInt("id_loaict");

            DanhMuc dm = new DanhMuc();
            DanhMucServices danhMucServices = new DanhMucServices();
            for (var c : danhMucServices.list()) {
                if (c.getId() == id_danhmuc) {
                    dm = c;
                }
            }
            ct.setDanhMuc(dm);
            
            LoaiChiTieu lct = new LoaiChiTieu();
            LoaiChiTieuServices loaiCtServices = new LoaiChiTieuServices();
            for (var c : loaiCtServices.list()) {
                if (c.getId() == id_loaict) {
                    lct = c;
                }
            }
            ct.setLoaiCT(lct);
            chiTieus.add(ct);
        }
        return chiTieus;
    }
    
    public boolean addChiTieu(ChiTieu ct) throws SQLException{
        Connection conn = MyConnector.getInstance().connect();
        
        PreparedStatement stm = conn.prepareStatement("INSERT into chitieu(name, date, money, ghichu, id_danhmuc, id_loaict) values(?,?,?,?,?,?)");
        conn.setAutoCommit(false);
        stm.setString(1, ct.getName());
        stm.setDate(2, Date.valueOf(ct.getDate()));
        stm.setDouble(3, ct.getMoney());
        stm.setString(4, ct.getNote());
        stm.setInt(5, ct.getDanhMuc().getId());
        stm.setInt(6, ct.getLoaiCT().getId());
        if(stm.executeUpdate() > 0){
            System.out.println("Them thanh cong");
            conn.commit();
            return true;
        }
        else {
            System.out.println("Them that bai");
            conn.rollback();
            return false;
        }
    }
}
