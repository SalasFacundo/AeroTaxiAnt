/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aerotaxi;

/**
 *
 * @author Facu
 */
public class Gold extends Avion {
   protected boolean catering=true; 
   protected boolean wifi; 

    public Gold(boolean wifi, double combustible, double costoKm, int capacidad, int velocidadMaxima, Propulsion tipoMotor) {
        super(combustible, costoKm, capacidad, velocidadMaxima, tipoMotor);
        this.wifi = wifi;
    }

    @Override
    public String toString() {
        
        return super.toString()+"\nCatering    : " + catering + "\nWifi      :" + wifi;
    }
   
    
   
   
}
