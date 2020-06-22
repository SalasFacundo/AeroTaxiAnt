package com.mycompany.aerotaxi;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    AeroTaxi aeroTaxi;

    Scanner entrada = new Scanner(System.in);
    Cliente cliente;

    public Menu(AeroTaxi aeroTaxi) {
        this.aeroTaxi = aeroTaxi;
    }

    public void menúPrincipal() {

        int opc;

        System.out.println("------------------------AEROTAXI-------------------------------------");
        System.out.println("Seleccione una opción: ");
        System.out.println("1.Ingresar como usuario");
        System.out.println("2.Ingresar como administrador");
        System.out.println("0.Salir");
        try {
            opc = entrada.nextInt();
            switch (opc) {
                case 1:
                    cliente = aeroTaxi.verificarCliente();
                    opcionesMenuClientes(cliente);
                    break;
                case 2:
                    menuAdministrador(aeroTaxi);
                    break;
                case 0:
                    System.out.println("Saliendo del programa. Hasta la proxima!");
                    break;
                default:
                    System.out.println("La opción ingresada no es correcta");
                    menúPrincipal();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Debe insertar un número");
            entrada.next();
            menúPrincipal();
        }
    }

    public void opcionesMenuClientes(Cliente cliente){
        int opc;

        System.out.println("------------------------AEROTAXI / CLIENTES------------------------------------");
        System.out.println("Cliente: "+cliente.nombre+" "+cliente.apellido);
        System.out.println("Seleccione una opción: ");
        System.out.println("1.Reservar un vuelo");
        System.out.println("2.Ver mis Reservas");
        System.out.println("3.Cancelar un vuelo");
        System.out.println("0.Salir");
        try {
            opc = entrada.nextInt();
            switch (opc) {
                case 1:
                    aeroTaxi.generarReserva(cliente);
                    opcionesMenuClientes(cliente);
                    break;
                case 2:
                    cliente.mostrarReservas();
                    opcionesMenuClientes(cliente);
                    break;

                case 3:
                    aeroTaxi.cancelarVuelo(cliente);
                    opcionesMenuClientes(cliente);
                    break;
                case 0:
                    System.out.println("Saliendo del programa. Hasta la proxima!");
                    menúPrincipal();
                    break;
                default:
                    System.out.println("La opción ingresada no es correcta");
                    opcionesMenuClientes(cliente);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Debe insertar un número");
            entrada.next();
            menúPrincipal();
        }
    }

    private void menuAdministrador(AeroTaxi aeroTaxi){
        int opc;

        System.out.println("------------------------AEROTAXI / ADMINISTRACIÓN------------------------------------");
        System.out.println("Seleccione una opción: ");
        System.out.println("1.Agregar avion a la flota");
        System.out.println("2.Ver listado de clientes");
        System.out.println("3.Ver listado de reservas");
        System.out.println("4.Ver reservas de una fecha");
        System.out.println("0.Salir");
        try {
            opc = entrada.nextInt();
            switch (opc) {
                case 1:
                    Avion avion = pedirInfoAvion();
                    aeroTaxi.agregarAvion(avion);
                    System.out.println("Avion agregado correctamente");
                    menuAdministrador(aeroTaxi);
                    break;
                case 2:
                    aeroTaxi.mostrarClientes();
                    menuAdministrador(aeroTaxi);
                    break;
                case 3:
                    aeroTaxi.mostrarReservas();
                    menuAdministrador(aeroTaxi);
                    break;

                case 4:
                    LocalDate fecha = aeroTaxi.asignarFecha();
                    aeroTaxi.mostrarReservasDeLaFecha(fecha);
                    menuAdministrador(aeroTaxi);
                    break;
                case 0:
                    System.out.println("Saliendo del programa. Hasta la proxima!");
                    break;
                default:
                    System.out.println("La opción ingresada no es correcta");
                    menúPrincipal();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Debe insertar un número");
            entrada.next();
            menúPrincipal();
        }

    }
    private Avion pedirInfoAvion(){
        int opcionTipoAvion=0,capacidad=0,velocidadMaxima=0,opcPropulsion=0;
        double costoKm=0, combustible=0;
        Propulsion tipo = Propulsion.MOTORPISTONES;

        Avion avion = null;
        System.out.println("Ingrese el tipo de avion que desea agregar:\n1-Gold\n2-Silver\n3-Bronze");
        try {
            opcionTipoAvion = entrada.nextInt();
            while (opcionTipoAvion < 1 || opcionTipoAvion > 3){
                System.out.println("La opcion ingresada no es valida, ingresela correctamente");
                opcionTipoAvion = entrada.nextInt();
            }
            System.out.println("Ingrese la capacidad de pasajeros:");
            capacidad = entrada.nextInt();
            System.out.println("Ingrese el precio por km:");
            costoKm = entrada.nextDouble();
            while (costoKm < 0){
                System.out.println("Ingrese un costo por km mayor a 0");
                costoKm = entrada.nextDouble();
            }
            System.out.println("Ingrese la capacidad de combustible:");
            combustible = entrada.nextDouble();
            while (combustible < 0){
                System.out.println("Ingrese la capacidad de combustible correctamente");
                combustible = entrada.nextDouble();
            }
            System.out.println("Ingrese la velocidad maxima: ");
            velocidadMaxima = entrada.nextInt();
            while (velocidadMaxima < 0){
                System.out.println("Ingrese la velocidad maxima correctamente");
                velocidadMaxima = entrada.nextInt();
            }
            System.out.println("Ingrese el tipo de propulsion que utiliza:\n1-Motor Reaccion \n2-Motor Helices \n3-Motor Pistones");
            opcPropulsion = entrada.nextInt();
            while (opcPropulsion < 1 || opcPropulsion > 3){
                System.out.println("La opcion ingresada no es valida, ingresela correctamente");
                opcPropulsion = entrada.nextInt();
            }
            if(opcPropulsion == 1){
                tipo = Propulsion.MOTORREACCION;
            }else if(opcPropulsion == 2){
                tipo = Propulsion.MOTORHELICE;
            }else if(opcPropulsion == 3){
                tipo = Propulsion.MOTORPISTONES;
            }
        }catch (InputMismatchException e){
            System.out.println("Debe ingresar el numero indicando el tipo de avion");
            entrada.nextLine();
            pedirInfoAvion();
        }

        if(opcionTipoAvion == 1){
            int opcionWifi;
            System.out.println("Wi-fi: 1-si / 2-no");
            opcionWifi = entrada.nextInt();
            if(opcionWifi == 1){
                avion = new Gold(true,combustible,costoKm,capacidad,velocidadMaxima,tipo);
            }else if(opcionWifi == 2){
                avion = new Gold(false,combustible,costoKm,capacidad,velocidadMaxima,tipo);
            }

        }else if(opcionTipoAvion == 2){
            avion = new Silver(combustible,costoKm,capacidad,velocidadMaxima,tipo);
        }else if(opcionTipoAvion == 3){
            avion = new Bronze(combustible,costoKm,capacidad,velocidadMaxima,tipo);
        }

        return avion;
    }
}
