/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services;

import com.nhc.pojo.ChiTieu;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableView;

/**
 *
 * @author nguye
 */
public class ShowFullStrategy  extends ShowStrategy{

    @Override
    public List<ChiTieu> render() {
        ChiTieuServices ctServices = new ChiTieuServices();
        try {
            return ctServices.list();
        } catch (SQLException ex) {
            Logger.getLogger(ShowFullStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
}
