/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author root
 */
package componentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Conexion {
    String url = null;
    String user = null;
    String pass = null;
    Connection conexion = null;
    PreparedStatement sentencia = null;
    ResultSet result = null;
    
    public Conexion(String url, String user, String pass)throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        this.conexion = DriverManager.getConnection(url,user, pass);
    }

    
    public void consultar(String query, String[] args) throws SQLException{
        this.sentencia = this.conexion.prepareStatement(query);
        for(int i = 0 ; i < args.length ; i ++){
            this.sentencia.setString(i, args[i+1]);
        }
        this.result = this.sentencia.executeQuery();
    }
    
    public void consultar(String query) throws SQLException{
        this.sentencia = this.conexion.prepareStatement(query);
        this.result = this.sentencia.executeQuery();
    }
    
    public ResultSet getResult(){
        return this.result;
    }
    
    public void cerrarConexion() throws SQLException{
        this.result.close();
        this.sentencia.close();
        this.conexion.close();
    }
        
}
