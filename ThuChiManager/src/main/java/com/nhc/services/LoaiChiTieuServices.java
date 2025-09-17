/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services;


import com.nhc.pojo.LoaiChiTieu;
import com.nhc.utils.MyConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class LoaiChiTieuServices {
    public List<LoaiChiTieu> list() throws SQLException{
        Connection conn = MyConnector.getInstance().connect();
        
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM loaichitieu");
        
        ResultSet rs = stm.executeQuery();
        
        List<LoaiChiTieu> loaiChiTieus = new ArrayList<>();
        
        while(rs.next()){
            LoaiChiTieu lct = new LoaiChiTieu();
            lct.setId(rs.getInt("id"));
            lct.setName(rs.getString("name"));
            loaiChiTieus.add(lct);
        }
        return loaiChiTieus;
    }
}
