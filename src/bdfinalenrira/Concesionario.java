/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdfinalenrira;
import java.sql.*;
import java.util.*;
/**
 *
 * @author enriq
 */
public class Concesionario {
    private ArrayList <Coche> coches = new ArrayList ();
    
    public Concesionario () throws SQLException {
        Connection conn = conectar();
        Statement stm = conn.createStatement();
        Coche c = null;
        ResultSet rset = stm.executeQuery("SELECT * FROM COCHES");
        while (rset.next()){
            c = new Coche (rset.getString("MATRICULA"),rset.getString("MARCA"),rset.getString("MODELO"),rset.getString("COLOR"),rset.getString("DNI"),rset.getInt("CILINDRADA"));
            coches.add(c);
        }
    }
   /* public void Crear_tabla (String nombre) throws SQLException {
        Connection conn = conectar ();
        Statement stm = conn.createStatement();
        String creacion = "CREATE TABLE "+nombre+" (MATRICULA VARCHAR2(8) PRIMARY KEY,MARCA VARCHAR2 (20),MODELO VARCHAR2 (40), COLOR VARCHAR2 (10),DNI VARCHAR2 (12),CILINDRADA NUMBER (4))";
        if (stm.execute(creacion))
            System.out.println("Tabla creada correctamente");
    }*/
    
    public void Persistencia_coches () throws SQLException {
        Connection conn = conectar ();
        Statement stm = conn.createStatement();
        String delete = "DELETE COCHES";
        stm.execute(delete);
        String insert = " ";
        for (Coche coche : coches){
            insert = "INSERT INTO COCHES VALUES ('"+coche.getMatricula()+"','"+coche.getMarca()+"','"+coche.getModelo()+"','"+coche.getColor()+"','"+coche.getDni()+"',"+coche.getCilindrada()+")";
            stm.executeUpdate(insert);
        }
        System.out.println("Datos introducidos con éxito");
    }
    public Connection conectar () throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection(
            "jdbc:oracle:thin:@192.168.7.4:1521:xe", "sys as sysdba", "admin"
        );
        return conn;
    }
    public void Insertar_coches (String matricula,String marca, String modelo, String color, String dni, int cilindrada) throws SQLException {
        boolean contiene = false;
        for (Coche coche : coches){
            if (coche.getMatricula().equals(matricula))
                contiene = true;
        }
        if (contiene){
            System.out.println("Lo siento, la matrícula ya existe");
        }
        else {
            Coche c = new Coche (matricula,marca,modelo,color,dni,cilindrada);
            coches.add(c);
            System.out.println("Coche insertado correctamente");
        }
    }
    public void Modificar_coches(String color, String nuevocolor){
        boolean contiene = false;
        
        for (Coche coche : coches){
            if (coche.getColor().equals(color)){
                contiene = true;
                coche.setColor(nuevocolor);
            }
        }
        if (contiene){
            System.out.println("Color actualizado correctamente");
        }
        else {
            System.out.println("No se ha encontrado el coche por la matrícula, intentalo de nuevo");
        }
    }
    public void Borrar_coches (String matricula) throws SQLException {
        Iterator <Coche> itr = coches.iterator();
        while (itr.hasNext()) {
            Coche coche = itr.next();
            if (coche.getMatricula().equals(matricula))
                itr.remove();
        }
        
        Connection conn = conectar();
        Statement stm = conn.createStatement();
        String delete = "DELETE COCHES WHERE MATRICULA = '"+matricula+"'";
        if (stm.executeUpdate(delete) >= 1)
            System.out.println("Borrado con éxito de la base de datos");
        else
            System.out.println("Ha habido un problema, inténtalo de nuevo"); 
        stm.close();
    }
    public void Consultar_coches (String matricula){
        for (Coche coche : coches){
            if (coche.getMatricula().equals(matricula)){
                System.out.println(coche.toString());
                System.out.println("");
            }
        }
    }
    public void Listar_coches () throws SQLException {
        Connection conn = conectar ();
        Statement stm = conn.createStatement();
        String select = "SELECT * FROM COCHES";
        ResultSet rset = stm.executeQuery(select);
        while (rset.next()){
            System.out.println("Matrícula --> "+rset.getString("MATRICULA"));
            System.out.println("Marca --> "+rset.getString("MARCA"));
            System.out.println("Modelo --> "+rset.getString("MODELO"));
            System.out.println("Color --> "+rset.getString("COLOR"));
            System.out.println("DNI --> "+rset.getString("DNI"));
            System.out.println("Cilindrada --> "+rset.getInt("CILINDRADA"));            
        }
    }
    
        public void ListarArrayList (){
            for (Coche coche : coches){
                System.out.println(coche.toString());  
            }
    }
}

