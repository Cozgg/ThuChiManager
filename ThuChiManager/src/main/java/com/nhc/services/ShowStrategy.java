/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services;

import com.nhc.pojo.ChiTieu;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author nguye
 */
public abstract class ShowStrategy {
    public abstract List<ChiTieu> render();
}
