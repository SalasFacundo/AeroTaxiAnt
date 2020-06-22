package com.mycompany.aerotaxi;

import java.util.Scanner;

//import java.time.Month;
//import javafx.util.converter.LocalDateStringConverter;


public class Main {
    public static void main(String[] args)
    {
       String opcion;
       AeroTaxi aerotaxi= new AeroTaxi();
       Scanner scan= new Scanner(System.in);

       Gold gold1= new Gold(false, 50000, 300, 50, 5000, Propulsion.MOTORHELICE);
       Silver silver1= new Silver(1500, 230, 40, 2000, Propulsion.MOTORPISTONES);
       Bronze bronze1= new Bronze(5000, 180, 30, 1000, Propulsion.MOTORREACCION);
       
       aerotaxi.agregarAvion(gold1);
       aerotaxi.agregarAvion(silver1);
       aerotaxi.agregarAvion(bronze1);         
       //aerotaxi.mostrarAviones();
       
       Cliente cliente1= new Cliente("Diego", "Ullua", 123, 26);
       Cliente cliente2 = new Cliente("Facundo","Salas",234,24);
       Cliente cliente3 = new Cliente("Juan","Perez",345,22);

        aerotaxi.agregarCliente(cliente1);
        aerotaxi.agregarCliente(cliente2);
        aerotaxi.agregarCliente(cliente3);

//        aerotaxi.generarReserva();
//        aerotaxi.generarReserva();
//        aerotaxi.generarReserva();
//        aerotaxi.generarReserva();
//        aerotaxi.generarReserva();

        Menu menu = new Menu(aerotaxi);
        menu.menúPrincipal();

       //aerotaxi.mostrarClientes();


        //aerotaxi.mostrarReservasDeLaFecha(4,10,2020);


       
        
    }
    
    /*
    public static void concluirReserva(AeroTaxi aerotaxi, Cliente cliente1)
    {
        System.out.println("Reserva del cliente: "+cliente1.getNombre());
        Scanner scan= new Scanner(System.in);
        String opcion;
         Reserva reserva1=reservar(aerotaxi, cliente1);
       
        System.out.println(reserva1);        
       
        System.out.println("Para reservar presione S");
        opcion=scan.nextLine().toUpperCase();
        reserva1.avion.agregarFechaOcupada(reserva1.fecha); // Agrega la fecha a la lista de fechas ocupadas del avion
        
        if(opcion.equals("S")){
        System.out.println("Reserva realizada satisfactoriamente");
        // Agregamos la reserva a la lista del cliente y de la empresa
        cliente1.agregarReserva(reserva1);
        aerotaxi.agregarReserva(reserva1);
            //System.out.println("entra");
        }
        else
        System.out.println("Reserva no generada");
        
        //cliente1.mostrarReservas();
        //cliente1.eliminarReserva(reserva1);
        //cliente1.mostrarReservas();
    }
    
    
    public static Reserva reservar(AeroTaxi aerotaxi, Cliente cliente)
    {
        LocalDate fecha;
        LocalDate hoy= LocalDate.now();
        Scanner scan= new Scanner(System.in);
        int dia, mes, año ,opcionCiudadOrigen, opcionCiudadDestino, pasajeros, opcionAvion;
        Avion avion;
        String ciudadOrigen, ciudadDestino;
        ArrayList<Avion> avionesDisponiblesEnLaFecha;

        ArrayList<String> ciudades=new ArrayList();  
        ciudades.add("BsAs");
        ciudades.add("Cordoba");
        ciudades.add("Santiago");
        ciudades.add("Montevideo");
        
        
        System.out.println("Ingrese dia de vuelo: ");
        dia = scan.nextInt();
        
        System.out.println("Ingrese mes de vuelo: ");
        mes = scan.nextInt();
        
        System.out.println("Ingrese año de vuelo: ");
        año = scan.nextInt();
        
        fecha= LocalDate.of(año, mes, dia); //Creamos un objeto de tipo fecha con los datos obtenidos.
        
         // Generamos el array de los aviones disponibles en la fecha indicada

        while (fecha.isBefore(hoy)) { // Si la fecha ingresada es anterior a la fecha actual, pedimos los datos hasta que se ingresen correctamente
            System.out.println("No se pueden reservar viajes para una fecha anterior a la actual, ingrese la fecha nuevamente");
            System.out.println("Ingrese dia de vuelo: ");
            dia = scan.nextInt();

            System.out.println("Ingrese mes de vuelo: ");
            mes = scan.nextInt();

            System.out.println("Ingrese año de vuelo: ");
            año = scan.nextInt();

            fecha= LocalDate.of(año, mes, dia);

        }
        
        System.out.println("Ingrese ciudad de origen");
        mostrarCiudades(ciudades);
        opcionCiudadOrigen = scan.nextInt();
        ciudadOrigen= ciudades.get(opcionCiudadOrigen-1);
        
        ciudades.remove(opcionCiudadOrigen-1);
        
         System.out.println("Ingrese ciudad de destino");
        mostrarCiudades(ciudades);
        opcionCiudadDestino= scan.nextInt();
        ciudadDestino= ciudades.get(opcionCiudadDestino-1);

        
        System.out.println("Ingrese cantidad de pasajeros: ");
        pasajeros = scan.nextInt();

        avionesDisponiblesEnLaFecha = aerotaxi.generarArrayAvionesDisponibles(fecha,pasajeros);
        
        //aerotaxi.mostrarAviones();

        if(avionesDisponiblesEnLaFecha.size()==0){
            System.out.println("No contamos con aviones preparados para esa cantidad de pasajeros");
        }

        else{
            for (int i =0; i < avionesDisponiblesEnLaFecha.size(); i++){ // Mostramos los aviones disponibles en la fecha y con la capacidad suficiente para el servicio

                System.out.println((i+1)+"-"+avionesDisponiblesEnLaFecha.get(i));

            }

        }


        
        System.out.println("Elija el avion: ");
        opcionAvion=scan.nextInt();
        //avion=aerotaxi.elegirAvion(opcionAvion);
        avion = avionesDisponiblesEnLaFecha.get(opcionAvion-1);

        Reserva reserva1= new Reserva(fecha, ciudadOrigen, ciudadDestino, pasajeros, avion, cliente);



        //System.out.println("El precio de los pasajes es de : "+calcularPrecio(reserva1));
        System.out.println("El precio del viaje es: $"+reserva1.costoDelViaje);

        return reserva1;
        
    }
    
      
    
    public static void mostrarCiudades(ArrayList a){
        for(int i =0; i < a.size() ; i++){
            System.out.println((i+1)+" - "+a.get(i));            
        }
    }
    
    public static double calcularPrecio(Reserva reserva)
    {
        int cantidadKm=distanciaViaje(reserva.origen, reserva.destino), precioPasajeros=3500*reserva.pasajeros;
        
        double costoKm=reserva.avion.costoKm;
        
        int tarifaAvion=precioAvion(reserva.avion); 
        
        return (double) ( (double) cantidadKm*costoKm) + precioPasajeros + tarifaAvion;
    }
         
    public static int distanciaViaje(String ciudadOrigen, String ciudadDestino)
    {
        int distancia = 0;
        
             if(ciudadOrigen=="BsAs"       && ciudadDestino=="Cordoba"    || ciudadDestino=="BsAs"       && ciudadOrigen=="Cordoba"   ) distancia=695;
        else if(ciudadOrigen=="BsAs"       && ciudadDestino=="Santiago"   || ciudadDestino=="BsAs"       && ciudadOrigen=="Santiago"  ) distancia=1400;
        else if(ciudadOrigen=="BsAs"       && ciudadDestino=="Montevideo" || ciudadDestino=="BsAs"       && ciudadOrigen=="Montevideo") distancia=950;
        else if(ciudadOrigen=="Cordoba"    && ciudadDestino=="Montevideo" || ciudadDestino=="Cordoba"    && ciudadOrigen=="Montevideo") distancia=1190;
        else if(ciudadOrigen=="Cordoba"    && ciudadDestino=="Santiago"   || ciudadDestino=="Cordoba"    && ciudadOrigen=="Santiago"  ) distancia=1050;
        else if(ciudadOrigen=="Montevideo" && ciudadDestino=="Santiago"   || ciudadDestino=="Montevideo" && ciudadOrigen=="Santiago"  ) distancia=2100;
        
        return distancia;
    }
               
    public static int precioAvion(Avion avion)
    {
        int precio=0;
        
             if(avion instanceof Bronze) precio=3000;        
        else if(avion instanceof Silver) precio=4000;
        else if(avion instanceof Gold)   precio=6000;
        
        return precio;
    }
    
    


     */
        
}
    