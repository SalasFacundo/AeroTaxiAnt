
package com.mycompany.aerotaxi;

import java.time.LocalDate;
import java.util.Objects;


public class Reserva {

    protected LocalDate fecha;
    protected String origen,destino;
    protected int pasajeros;
    protected Avion avion;
    protected Cliente cliente;
    protected int distanciaDelViaje;
    protected double costoDelViaje;


    public Reserva(LocalDate fecha, String origen, String destino, int pasajeros, Avion avion, Cliente cliente) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.pasajeros = pasajeros;
        this.avion = avion;
        this.cliente = cliente;
        calcularDistancia();
        calcularCosto();
    }

    public void calcularDistancia() {

             if(origen=="BsAs"       && destino=="Cordoba"    || destino=="BsAs"       && origen=="Cordoba"   ) distanciaDelViaje=695;
        else if(origen=="BsAs"       && destino=="Santiago"   || destino=="BsAs"       && origen=="Santiago"  ) distanciaDelViaje=1400;
        else if(origen=="BsAs"       && destino=="Montevideo" || destino=="BsAs"       && origen=="Montevideo") distanciaDelViaje=950;
        else if(origen=="Cordoba"    && destino=="Montevideo" || destino=="Cordoba"    && origen=="Montevideo") distanciaDelViaje=1190;
        else if(origen=="Cordoba"    && destino=="Santiago"   || destino=="Cordoba"    && origen=="Santiago"  ) distanciaDelViaje=1050;
        else if(origen=="Montevideo" && destino=="Santiago"   || destino=="Montevideo" && origen=="Santiago"  ) distanciaDelViaje=2100;

    }

    public void calcularCosto(){
        costoDelViaje = (distanciaDelViaje * avion.costoKm) + (pasajeros * 3500) + avion.getPrecio();        
    }

    @Override
    public String toString() {
        return "\nReserva del cliente: "+cliente.nombre+" "+cliente.apellido+"\nFecha       :" + fecha + "\nOrigen      : " + origen +
                "\nDestino     : " + destino + "\nPasajeros   : " + pasajeros +"\nCosto del viaje: $"+costoDelViaje+
                "\nDETALLES DEL AVION:\n" + avion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;
        Reserva reserva = (Reserva) o;
        return pasajeros == reserva.pasajeros &&
                distanciaDelViaje == reserva.distanciaDelViaje &&
                Double.compare(reserva.costoDelViaje, costoDelViaje) == 0 &&
                fecha.equals(reserva.fecha) &&
                origen.equals(reserva.origen) &&
                destino.equals(reserva.destino) &&
                avion.equals(reserva.avion) &&
                cliente.equals(reserva.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, origen, destino, pasajeros, avion, cliente, distanciaDelViaje, costoDelViaje);
    }
}
