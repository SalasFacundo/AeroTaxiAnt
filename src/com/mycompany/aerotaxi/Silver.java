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
public class Silver extends Avion  {
    protected boolean catering=true;

    public Silver(double combustible, double costoKm, int capacidad, int velocidadMaxima, Propulsion tipoMotor) {
        super(combustible, costoKm, capacidad, velocidadMaxima, tipoMotor);
    }

    public Silver()
    {
        
    }
    
    @Override
    public String toString() {
        return super.toString()+ "\nCatering : " + catering;
    }

    public boolean getCatering() {
        return catering;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }
    
    
    
}
