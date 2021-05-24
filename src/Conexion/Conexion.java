/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kevin
 */
public class Conexion {
     Connection con;
    private String host="localhost";
    private String port="3306";
    private String dbName="bdbaniscutp";
    private String user="root";
    private String contraseña="";
    
    public Conexion (){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbName;
            con = DriverManager.getConnection(url, this.user, this.contraseña);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("No se conecto. ");
        }
    }
}
