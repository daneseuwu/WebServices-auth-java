/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author duga17
 */
public class connectionsql {
    
    //configure driver connection sql driver
    Connection cn = null;
    //String inst = "";
    static connectionsql instancia = null;
    String strConexionSql = "jdbc:sqlserver://localhost\\nameinstancesql:1433;databaseName=webservicedba";
    String strDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //contructor class connection
    public connectionsql() {
        
        //declare var user and pass to execute storage procdure on database
        String dbuser = "webservicedba";
        String dbpassword = "123";

        try {

            Class.forName(strDriver);
            cn = DriverManager.getConnection(strConexionSql,dbuser,dbpassword);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static connectionsql getInstancia() throws Exception {

        if (instancia == null) {
            instancia = new connectionsql();
        }
        return instancia;

    }

    public Connection getConexion() {
        return cn;
    }
    
}
