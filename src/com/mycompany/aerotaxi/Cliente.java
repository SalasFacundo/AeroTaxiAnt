
package com.mycompany.aerotaxi;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente  {
    

    
    protected  String nombre;
    protected  String apellido;
    protected  int dni;
    protected  int edad;
    protected  ArrayList<Reserva> reservas= new ArrayList();

    public Cliente(String nombre, String apellido, int dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
    }

    public Cliente()
    {
        
    }
    
    public void agregarReserva(Reserva reserva)
    {
        reservas.add(reserva);
    }
    
    public void eliminarReserva(Reserva reserva)
    {
       LocalDate hoy= LocalDate.now();
       int flag=0;
                                                                            // Eliminar la reserva de la lista del cliente
        for(int i=0; i<reservas.size(); i++)
        {
            if(reservas.get(i).equals(reserva))
            {
                if (hoy.isBefore(reserva.fecha.minusDays(1)))                 //Comparo si la fecha de hoy es menor a un dia anterior a la fecha de vuelo
                {

                    reservas.remove(reserva);
                    System.out.println("\nReserva eliminada con exito de la lista del cliente");
                }
                else
                {
                    System.out.println("\nNo se puede cancelar un vuelo con menos de 24hs de anticipación");
                }
                flag=1;
            }
            else
            {
                flag=flag;
            }
            
            
        }
        
        if(flag==0)
            {
                System.out.println("\nLa reserva no existe o ha sido eliminada de la lista del cliente");
            }            
       
    }
    
    public void mostrarReservas()
    {
        if(reservas.size() > 0){
            System.out.println("RESERVAS DEL CLIENTE: "+nombre+" "+apellido);
            for(int i=0; i<reservas.size(); i++)
            {
               System.out.println((i+1)+"-"+reservas.get(i)+"\n-----------------------\n");
                
            }
        }else {
            System.out.println("No ha realizado reservas.");
        }

    }
    
           
    public String mejorAvion()
    {
        boolean gold= false, silver= false, bronze= false;
        String rta="";
        
        for(int i=0; i<reservas.size(); i++)
        {
                 if(reservas.get(i).avion instanceof Gold)   gold=true;   
            else if(reservas.get(i).avion instanceof Silver) silver=true;
            else if(reservas.get(i).avion instanceof Bronze) bronze=true;
        }
        if(gold ==true)                          rta="Gold";                                     //Si existe un avion Gold guarda este como mejor   
        if(gold ==false && silver==true)    rta="Silver";                                   //Si no existe un avion Gold, y existe un Silver guarda este como mejor     
        if(silver==false && bronze==true)   rta="Bronze";                                   //Si no existe 
        
        return rta;
    }

    public double totalGastado(){
        double gastado =0;

        for (Reserva rsv : reservas){
            gastado += rsv.costoDelViaje;
        }
        return gastado;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "\nNombre :" + nombre + "\nApellido :" + apellido + "\nDni :" + dni + "\nEdad :" + edad ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return dni == cliente.dni;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    
    
    
    
}
