/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bdfinalenrira;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
/**
 *
 * @author enriq
 */
public class BDFinalEnrIra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException{
        // Primer apartado
        Concesionario conce = new Concesionario ();
        // Segundo apartado
        conce.Modificar_coches("ROJO","MAGENTA");
        conce.ListarArrayList();
        // Tercer apartado
        String matricula,marca,modelo,color,dni;
        int cilindrada;
        BufferedReader teclado = new BufferedReader (new InputStreamReader (System.in));
        for (int i = 0; i < 2; i++){
            System.out.println("Dime la matrícula");
            matricula = teclado.readLine();
            while (!Pattern.matches("[0-9]{4}-[A-Z]{3}",matricula)){
                System.out.println("Formato incorrecto, repite");                
                matricula = teclado.readLine();
            }
            System.out.println("Dime la marca");
            marca = teclado.readLine();
            System.out.println("Dime el modelo");
            modelo = teclado.readLine();
            System.out.println("Dime el color");
            color = teclado.readLine();
            System.out.println("Dime el dni");
            dni = teclado.readLine();
            System.out.println("Dime la cilindrada");
            cilindrada = Integer.parseInt(teclado.readLine());
            conce.Insertar_coches(matricula, marca, modelo, color, dni, cilindrada);
        }
        // Cuarto apartado
        conce.Borrar_coches("1566-BYD");
        conce.Listar_coches();
        conce.ListarArrayList();
        // Quinto y sexto apartado
        conce.Persistencia_coches();
        conce.Listar_coches();
        
    }
    
}
