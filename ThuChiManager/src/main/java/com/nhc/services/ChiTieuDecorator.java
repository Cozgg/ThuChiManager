/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhc.services;

/**
 *
 * @author nguye
 */
public abstract class ChiTieuDecorator implements IChiTieu{

    protected IChiTieu decorator;

    public ChiTieuDecorator(IChiTieu decorator) {
        this.decorator = decorator;
    }
    
    @Override
    public String getStyle() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
