package com.mycompany.aerotaxi;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//import java.time.Month;
//import javafx.util.converter.LocalDateStringConverter;


public class Main {
    public static void main(String[] args)
    {
       String opcion;
       AeroTaxi aerotaxi= new AeroTaxi();
       Scanner scan= new Scanner(System.in);
       
       
       File clienteJson= new File("files\\Clientes.json");
       
       crearArchivo(clienteJson);
            
       //Cargo primeros aviones

       Gold gold1= new Gold(false, 50000, 300, 50, 5000, Propulsion.MOTORHELICE);
       Silver silver1= new Silver(1500, 230, 40, 2000, Propulsion.MOTORPISTONES);
       Bronze bronze1= new Bronze(5000, 180, 30, 1000, Propulsion.MOTORREACCION);
       
       
       //Agrego aviones a aerotaxi
       
       aerotaxi.agregarAvion(gold1);
       aerotaxi.agregarAvion(silver1);
       aerotaxi.agregarAvion(bronze1); 
       
             
       ArrayList<Cliente> clientesGuardados= json2arrayCliente();  
        
        
       clientesGuardados2AeroTaxi(aerotaxi, clientesGuardados);
        
        
       
        //
        System.out.println("Archivo cargado de cleintes desde Json solo para testeo \n");
        
       leerJsonCliente();
        
        
        
         

        Menu menu = new Menu(aerotaxi);
        menu.menúPrincipal();
       
              
        
    }
    
    
    public static void clientesGuardados2AeroTaxi(AeroTaxi aerotaxi, ArrayList<Cliente> clientes)
    {
        for(int i=0; i<clientes.size(); i++)
        {
            aerotaxi.agregarCliente(clientes.get(i));
        }
    }
    
    
    
    
        
    public static void agregar2jsonCliente(Cliente cliente)
    {
        File archivo= new File("files\\Clientes.json");
        
        if(archivo.exists())
        {
            
        
        ArrayList<Cliente> array= json2arrayCliente();                                                      // Paso el json a un array
        array.add(cliente);                                                                                 //Agrego dato nuevo al array
        array2jsonCliente(array);                                                                           //Paso el array a json
        
        System.out.println("dato agregado");
        }
        else
        {
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                System.out.println("No se pudo crear el archivo");
            }
            agregar2jsonCliente(cliente);
        }
        
        
    }
    
    
    public static void array2jsonCliente(ArrayList<Cliente> array)
    {        
        File archivo= new File("files\\Clientes.json");
                
                ObjectMapper mapper = new ObjectMapper();  
        try {
                mapper.writeValue(archivo, array);                                                          //Agrego array a json
        } catch (IOException ex) {
            System.out.println("exception");
        }
    }
    
    
    
    public static void leerJsonCliente() {

        File archivo= new File("files\\Clientes.json");
        
        if (archivo.length() <= 0) {
            System.out.println("El archivo esta en blanco");                                                    //Si el archivo no ocupa espacio en disco esta en blanco
        } else {

            ObjectMapper mapper = new ObjectMapper();
            try {
                Cliente[] cliente = mapper.readValue(archivo, Cliente[].class);                                   //Capturo datos de Json
                for (int i = 0; i < cliente.length; i++) {
                    System.out.println(cliente[i].toString());                                                      //Muestro array
                }
            } catch (IOException ex) {
                System.out.println("El archivo no existe o no se puede leer");
            }
        }
    }
    
    
        
    public static ArrayList<Cliente> json2arrayCliente()
    {
        File archivo= new File("files\\Clientes.json");
        
        ObjectMapper mapper= new ObjectMapper();  
        ArrayList<Cliente> arraylist= new ArrayList();                              //Creo array vacio
        
        if(archivo.length()>0)                                                       // Si el archivo no esta vacio, pasamos el contenido a un arraylist                                                   
        {
            
        
        try {
            Cliente[] cliente= mapper.readValue(archivo,Cliente[].class);               //Capturo el array en Json a un array.
            
            
            for(int i=0; i<cliente.length; i++)
            {
                arraylist.add(cliente[i]);                                               //Convierto el array, en un arrayList
            } 
            
        } catch (IOException ex) {
            System.out.println("Error en lectura del archivo");
        }
        }
        
        return arraylist;
    }
    
    
    
    public static void crearArchivo(File archivo)                                           //Si el archivo no existe lo crea
    {
                
        if(!archivo.exists())                                               
        {
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                System.out.println("No se puede crear el archivo");
            }
        }
    }
    
    
    
    
    
    
    
}
    