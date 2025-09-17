/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services;

import com.nhc.pojo.DanhMuc;
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
public class DanhMucServices {
    public List<DanhMuc> list() throws SQLException{
        Connection conn = MyConnector.getInstance().connect();
        
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM danhmuc");
        
        ResultSet rs = stm.executeQuery();
        
        List<DanhMuc> danhMucs = new ArrayList<>();
        
        while(rs.next()){
            DanhMuc dm = new DanhMuc();
            dm.setId(rs.getInt("id"));
            dm.setName(rs.getString("name"));
            danhMucs.add(dm);
        }
        return danhMucs;
    }
}
