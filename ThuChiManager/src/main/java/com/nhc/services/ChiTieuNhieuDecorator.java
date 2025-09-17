/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services;

/**
 *
 * @author nguye
 */
public class ChiTieuNhieuDecorator extends ChiTieuDecorator{

    public ChiTieuNhieuDecorator(IChiTieu decorator) {
        super(decorator);
    }

    @Override
    public String getStyle() {
        return this.decorator.getStyle() + "-fx-background-color: red;";
    }
    
}
