
package com.mycompany.aerotaxi;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AeroTaxi {
    
    ArrayList<Avion> aviones=new ArrayList();
    //ArrayList<Cliente> clientes=new ArrayList();

   HashSet<Cliente> clientes = new HashSet<>();
    ArrayList<Reserva> reservas = new ArrayList<>();


    Scanner sc = new Scanner(System.in);

    public AeroTaxi() {
        
    }      
    
    
    public Avion elegirAvion(int id)
    {
     id-=1;     
     return aviones.get(id);            
    }
    public void eliminarReserva(Reserva reserva, Cliente cliente){

        LocalDate hoy= LocalDate.now();

        int flag=0;
        // eliminar la reserva de la lista de la empresa
        for(int i=0; i<reservas.size(); i++)
        {
            if(reservas.get(i).equals(reserva))
            {
                if (hoy.isBefore(reserva.fecha.minusDays(1)))                 //Comparo si la fecha de hoy es menor a un dia anterior a la fecha de vuelo
                {
                    // Borramos la reserva de la lista de la empresa y del cliente
                    //Tomar los datos del avion y fecha para eliminar la fecha de la lista de fechas ocupadas del avion
//                    Avion avionUsado = reserva.avion;
//                    LocalDate fechaBorrar = reserva.fecha;

                    reservas.remove(i);
                    cliente.eliminarReserva(reserva);
                    System.out.println("\nReserva eliminada con exito de la lista de la empresa");

                    // Ver forma de eliminar la fecha de la lista de fechas ocupadas del avion
//                    avionUsado.liberarFecha(fechaBorrar);




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
            System.out.println("\nLa reserva no existe o ha sido eliminada");
        }


    }

    public void cancelarVuelo(Cliente cliente){
        Reserva borrar;
        int opcionBorrar=0;
        // Mostramos las reservas del cliente
        cliente.mostrarReservas();
        System.out.println("Indique cual reserva desea cancelar:");
        try{
            opcionBorrar = sc.nextInt();
            System.out.println("Opcion seleccionada"+opcionBorrar);
            while (opcionBorrar<1 || opcionBorrar > cliente.reservas.size()){
                System.out.println("Ingrese una opcion valida: ");
                opcionBorrar = sc.nextInt();

            }
        }catch (InputMismatchException e){
            System.out.println("Debe ingresar un numero para indicar la reserva");
            sc.nextLine();
            cancelarVuelo(cliente);
        }
        borrar = cliente.reservas.get(opcionBorrar-1);
        System.out.println("recibe la reserva a eliminar"+borrar);
        eliminarReserva(borrar,cliente);
    }
    public void agregarAvion(Avion avion)
    {
        aviones.add(avion);
    }
    
    public void agregarCliente(Cliente cliente)
    {
        clientes.add(cliente);
    }

    public void agregarReserva(Reserva reserva){
        reservas.add(reserva);
    }

    public void mostrarReservas(){

        if(reservas.size() > 0){
            for(Reserva r : reservas){
                System.out.println("LISTADO DE RESERVAS AEROTAXI");
                System.out.println(r.toString());
            }
        }else{
            System.out.println("No hay reservas.");
        }

    }

    public void mostrarReservasDeLaFecha(LocalDate fecha){

        System.out.println("Reservas de la fecha:"+fecha);

        for(Reserva r : reservas){
            if(r.fecha.isEqual(fecha)){
                System.out.println(r.toString());
            }
        }
    }
    
    public void mostrarAviones()
    {
        for(int i=0; i<aviones.size(); i++)
        {
            System.out.println(i+1+"-"+aviones.get(i)+"\n");
        }
    }
    public void mostrarAvionesDisponibles(LocalDate date){

        for (Avion a : aviones){
            if(a.comprobarDisponibilidad(date)){
                System.out.println(a);
            }
        }

    }

    public ArrayList<Avion> generarArrayAvionesDisponibles(LocalDate date,int pasajeros){
        ArrayList<Avion> arrayAvionesDisponibles = new ArrayList<>();
        for (Avion a : aviones){
            if(a.comprobarDisponibilidad(date) && a.capacidad >= pasajeros){
                arrayAvionesDisponibles.add(a);
            }
        }

        return arrayAvionesDisponibles;
    }

    public void mostrarClientes() {
        System.out.println("Lista de clientes: ");


        for (Cliente c : clientes){
            System.out.println("Informacion del cliente: \n"+c+
                        "\nMejor avion utilizado: "+c.mejorAvion()+
                        "\nTotal de dinero gastado: $"+c.totalGastado());

        }


    }


    public void generarReserva(Cliente cliente){


        int dni=0,opcionCiudadOrigen = (-1), opcionCiudadDestino = (-1), pasajeros=0, opcionAvion=0,opcionConfirmar=0;
        String ciudadOrigen, ciudadDestino;
        Avion avion;
        //Cliente cliente;
        LocalDate fecha;
        LocalDate hoy= LocalDate.now();
        ArrayList<Avion> avionesCorrectos; // Arraylist de aviones que cumplan con los requisitos del cliente (Fecha / Cant de pax)
        ArrayList<String> ciudades=new ArrayList();
        ciudades.add("BsAs");
        ciudades.add("Cordoba");
        ciudades.add("Santiago");
        ciudades.add("Montevideo");


        // Pedimos el dni del cliente

        /*
            System.out.println("Ingrese el DNI del cliente:");
            try{
                dni = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("El numero de DNI debe ser ingresado en forma de un numero entero, sin puntos, guiones ni signos.");
                sc.nextLine();
                generarReserva();
            }

            cliente = buscarClienteDNI(dni); // Buscamos si el dni ingresado corresponde a un cliente existente

            // Si el dni no corresponde a un cliente existente, generamos un nuevo cliente
            if(cliente == null){
                System.out.println("El DNI ingresado no corresponde a un cliente registrado, por favor complete estos datos para agregarlo a la lista: ");
                cliente = registrarCliente(dni);
                // El cliente se agregará a nuestra lista de clientes una vez que realize la reserva del vuelo
            }


         */

            System.out.println("Cliente: "+cliente.nombre+" "+cliente.apellido);


            System.out.println("Ingrese la información del viaje: ");
            // Asignamos la fecha del viaje
            fecha = asignarFecha();

            // Si la fecha ingresada es anterior a la fecha actual, pedimos los datos hasta que se ingresen correctamente
            while (fecha.isBefore(hoy)) {
                System.out.println("Por favor ingrese una fecha posterior al dia de la fecha: "+hoy);
                fecha = asignarFecha();
            }

            // Pedimos las ciudades de origen y destino

            System.out.println("Ingrese ciudad de origen");
            mostrarCiudades(ciudades);
            try{
                opcionCiudadOrigen = sc.nextInt();
                while (opcionCiudadOrigen < 1 ||opcionCiudadOrigen > ciudades.size()){
                    System.out.println("La opcion ingresada no es valida, ingresela correctamente: ");
                    opcionCiudadOrigen = sc.nextInt();
                }
            }catch (InputMismatchException e){
                System.out.println("Para confirmar una opción debe ingresar el numero de opción correspondiente");
                sc.nextLine();
                generarReserva(cliente);
            }

            ciudadOrigen= ciudades.get(opcionCiudadOrigen-1);


            // Eliminamos la ciudad de origen como opcion de ciudad de destino
            ciudades.remove(opcionCiudadOrigen-1);

            System.out.println("Ingrese ciudad de destino");
            mostrarCiudades(ciudades);

            try{
                opcionCiudadDestino= sc.nextInt();
                while (opcionCiudadDestino < 1 || opcionCiudadDestino > ciudades.size()){
                    System.out.println("La opcion ingresada no es valida, ingresela correctamente: ");
                    opcionCiudadDestino= sc.nextInt();
                }
            }catch (InputMismatchException e){
                System.out.println("Para confirmar una opción debe ingresar el numero de opción correspondiente");
                sc.nextLine();
                generarReserva(cliente);
            }
            ciudadDestino= ciudades.get(opcionCiudadDestino-1);

            // Pedimos la cantidad de pasajeros

            pasajeros = seleccionarCantidadPasajeros();

            // Generamos el array con los aviones que cumplen con los requisitos del cliente (fecha / cant pax)
            avionesCorrectos = generarArrayAvionesDisponibles(fecha,pasajeros);

            // Si no hay aviones que cumplan con los requisitos del cliente
            if(avionesCorrectos.size()==0){
                // Lo siento
                System.out.println("Lo siento, no contamos con aviones preparados para esa cantidad de pasajeros disponibles en la fecha indicada.");
            }else{
                // Todo bien, seguimos con la reserva

                // Le damos al cliente las opciones que tenemos disponibles en esa fecha y para esa cantidad de pasajeros

                avion = elegirAvion(avionesCorrectos);

                Reserva reserva1= new Reserva(fecha, ciudadOrigen, ciudadDestino, pasajeros, avion, cliente);

                System.out.println("El precio del viaje es: $"+reserva1.costoDelViaje);

                System.out.println("INFORMACION DE LA RESERVA:\n");
                System.out.println(reserva1);

                System.out.println("\nPARA CONFIRMAR LA RESERVA PRESIONE 1 ");
                try{
                    opcionConfirmar = sc.nextInt();
                }catch (InputMismatchException e ){
                    System.out.println("Las opciones deben ser ingresadas como numeros enteros");
                    sc.nextLine();
                    generarReserva(cliente);
                }


                if(opcionConfirmar == 1){
                    // Mostramos mensaje de confirmacion y agregamos la reserva a la lista de la empresa y del cliente
                    System.out.println("RESERVA CONFIRMADA CON EXITO!");
                    cliente.agregarReserva(reserva1);
                    agregarReserva(reserva1);
                    // Agregamos la fecha ocupada a la lista del avion
                    avion.agregarFechaOcupada(fecha);
                    // Agregamos el cliente al hashset de clientes
                    clientes.add(cliente);

                }else {
                    System.out.println("LA RESERVA NO FUE CONFIRMADA");
                }

            }



    }

    public Cliente verificarCliente(){
        int dni=0;
        Cliente cliente;
        System.out.println("Ingrese el DNI del cliente:");
        try{
            dni = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("El numero de DNI debe ser ingresado en forma de un numero entero, sin puntos, guiones ni signos.");
            sc.nextLine();
            verificarCliente();
        }

        cliente = buscarClienteDNI(dni); // Buscamos si el dni ingresado corresponde a un cliente existente

        // Si el dni no corresponde a un cliente existente, generamos un nuevo cliente
        if(cliente == null){
            System.out.println("El DNI ingresado no corresponde a un cliente registrado, por favor complete estos datos para agregarlo a la lista: ");
            cliente = registrarCliente(dni);
            // El cliente se agregará a nuestra lista de clientes una vez que realize la reserva del vuelo
        }

        return cliente;
    }



    private String elegirCiudad (ArrayList<String> ciudades, String origenDestino){
        int opcion=0;
        String ciudad;
        System.out.println("Ingrese ciudad de "+origenDestino);
        mostrarCiudades(ciudades);
        try{
            opcion = sc.nextInt();
            while (opcion < 1 || opcion > ciudades.size()){
                System.out.println("La opcion ingresada no es valida, ingresela correctamente: ");
                opcion = sc.nextInt();
            }
            ciudad = (String) ciudades.get(opcion-1);
        }catch (InputMismatchException e){
            System.out.println("Para confirmar una opción debe ingresar el numero de opción correspondiente");
            sc.nextLine();
            ciudad = elegirCiudad(ciudades,origenDestino);
        }

        return ciudad;
    }

    private Avion elegirAvion(ArrayList<Avion> avionesCorrectos){
        int opcionAvion =0;
        Avion avion;
        // Mostramos los aviones
        for (int i =0; i < avionesCorrectos.size(); i++){
            System.out.println((i+1)+"-"+avionesCorrectos.get(i));
        }

        System.out.println("Elija el avion: ");
        try{
            opcionAvion=sc.nextInt();
            while (opcionAvion < 1 || opcionAvion > avionesCorrectos.size()){
                System.out.println("La opcion ingresada no es valida, ingresela nuevamente por favor: ");
                opcionAvion = sc.nextInt();
            }
            avion = avionesCorrectos.get(opcionAvion-1);
        }catch (InputMismatchException e){
            System.out.println("Las opciones deben ser ingresadas como numeros enteros");
            sc.nextLine();
            avion = elegirAvion(avionesCorrectos);
        }

        return avion;
    }
    private int seleccionarCantidadPasajeros(){
        int pasajeros =0;
        System.out.println("Ingrese cantidad de pasajeros: ");
        try{
            pasajeros = sc.nextInt();
            while (pasajeros <1){
                System.out.println("La cantidad minima de pasajeros es 1, por favor, ingresela nuevamente: ");
                pasajeros = sc.nextInt();
            }
        }catch (InputMismatchException e){
            System.out.println("Las cantidades deben ser expresadas en numeros enteros");
            sc.nextLine();
            seleccionarCantidadPasajeros();
        }
        return pasajeros;
    }

    public Cliente registrarCliente(int dni){

        Cliente nuevo;


        try{
            sc.nextLine();
            System.out.println("Ingrese el nombre:");
            String name = sc.nextLine();
            while (name.isEmpty()){
                System.out.println("El campo no puede estar vacio, por favor ingrese el nombre");
                name=sc.nextLine();
            }
            System.out.println("Ingrese el apellido:");
            String lastname = sc.nextLine();
            while (lastname.isEmpty()){
                System.out.println("El campo no puede estar vacio, por favor ingrese el apellido");
                lastname=sc.nextLine();
            }
            System.out.println("Ingrese la edad");

            int age = sc.nextInt();
            while (age < 1){
                System.out.println("Ingrese una edad valida:");
                age=sc.nextInt();
            }
            nuevo = new Cliente(name,lastname,dni,age);

        }catch (InputMismatchException e){

            System.out.println("Por favor complete los campos de información correctamente.");
            nuevo=registrarCliente(dni);
            sc.nextLine();
        }

        // El cliente se agregará a nuestra lista de clientes una vez que realize la reserva del vuelo

        return nuevo;
    }

    public void mostrarCiudades(ArrayList a){
        for(int i =0; i < a.size() ; i++){
            System.out.println((i+1)+" - "+a.get(i));
        }
    }

    public LocalDate asignarFecha(){

        int dia,mes,anio;
        LocalDate fecha = LocalDate.now();

        try{
            System.out.println("Ingrese dia de vuelo: ");
            dia = sc.nextInt();

            System.out.println("Ingrese mes de vuelo: ");
            mes = sc.nextInt();

            System.out.println("Ingrese año de vuelo: ");
            anio = sc.nextInt();
            fecha = LocalDate.of(anio,mes,dia);

        }catch (DateTimeException e ) {
            System.out.println("Debe ingresar fechas validas indicando el dia, mes y año ");
            sc.nextLine();
            asignarFecha();
        }catch (InputMismatchException e){
            System.out.println("La fecha debe ser ingresada con numeros enteros indicando el dia, mes y año");
            sc.nextLine();
            asignarFecha();
        }

        return fecha;
    }

    public Cliente buscarClienteDNI(int dni){
        Cliente encontrado = null;
        for (Cliente c : clientes){
            if(c.dni == dni){
                encontrado = c;
            }
        }
        return encontrado;
    }
    
    
    
}
