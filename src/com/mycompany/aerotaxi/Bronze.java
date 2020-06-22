
package com.mycompany.aerotaxi;


public class Bronze extends Avion  {

    public Bronze(double combustible, double costoKm, int capacidad, int velocidadMaxima, Propulsion tipoMotor) {
        super(combustible, costoKm, capacidad, velocidadMaxima, tipoMotor);
    }
    
    public Bronze()
    {
        
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
}
