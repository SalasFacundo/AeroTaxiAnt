/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aerotaxi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
● Capacidad de combustible
● Costo por km (este valor ronda entre los $150 y $300)
● Capacidad máxima de pasajeros
● Velocidad máxima(en km/h)
● Tipo de propulsión que tiene (motor a reacción, motor a hélice o motor de
pistones). 

 */
public abstract class Avion {
    protected double combustible;
    protected double costoKm;
    protected int capacidad;
    protected int velocidadMaxima;
    protected Propulsion tipoMotor;
    protected int precio;
    protected ArrayList<LocalDate> fechasOcupadas = new ArrayList<>();

    public Avion(double combustible, double costoKm, int capacidad, int velocidadMaxima, Propulsion tipoMotor) {
        this.combustible = combustible;
        this.costoKm = costoKm;
        this.capacidad = capacidad;
        this.velocidadMaxima = velocidadMaxima;
        this.tipoMotor = tipoMotor;
        precioAvion();
    }

    public Avion()
    {
        
    }
    
    public void precioAvion() {

        if(this instanceof Bronze) precio=3000;
        else if(this instanceof Silver) precio=4000;
        else if(this instanceof Gold)   precio=6000;

    }

  
    public void agregarFechaOcupada(LocalDate fecha){
        fechasOcupadas.add(fecha);
    }

    public void liberarFecha(LocalDate fecha){
        for(LocalDate f : fechasOcupadas){
            if (f.isEqual(fecha)){
                fechasOcupadas.remove(f);
            }
        }
    }

    public boolean comprobarDisponibilidad(LocalDate fecha){
        boolean rta = true;
        for(LocalDate f : fechasOcupadas){
            if(f.isEqual(fecha)){
                rta = false;
            }
        }
        return rta;
    }

    @Override
    public String toString() {
        return  this.getClass()+"      \nCombustible : " + combustible + "      \nCostoKm     : " + costoKm + "      \nCapacidad   : " + capacidad + "      \nVelocidad   : " + velocidadMaxima + "      \nTipoMotor  : " + tipoMotor ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avion)) return false;
        Avion avion = (Avion) o;
        return Double.compare(avion.combustible, combustible) == 0 &&
                Double.compare(avion.costoKm, costoKm) == 0 &&
                capacidad == avion.capacidad &&
                velocidadMaxima == avion.velocidadMaxima &&
                getPrecio() == avion.getPrecio() &&
                tipoMotor == avion.tipoMotor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(combustible, costoKm, capacidad, velocidadMaxima, tipoMotor, getPrecio());
    }

      public int getPrecio() {
        return precio;
    }
      
    public void setPrecio(int precio) {
        this.precio = precio;
    }  
      
    public double getCombustible() {
        return combustible;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public double getCostoKm() {
        return costoKm;
    }

    public void setCostoKm(double costoKm) {
        this.costoKm = costoKm;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public Propulsion getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(Propulsion tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public ArrayList<LocalDate> getFechasOcupadas() {
        return fechasOcupadas;
    }

    public void setFechasOcupadas(ArrayList<LocalDate> fechasOcupadas) {
        this.fechasOcupadas = fechasOcupadas;
    }
    
    
    
}
