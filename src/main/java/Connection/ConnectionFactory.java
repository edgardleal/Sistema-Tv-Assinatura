/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Alexf
 */
public class ConnectionFactory implements AutoCloseable {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection connection;
    
    public static Connection getConnection(){
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASS);

            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException("Erro na conexão", ex);
            }
        }
        return connection;
    }

    /**
     * <p>
     *     Close internal connection
     * </p>
     */
    public static void closeConnection(){
        closeConnection(connection);
        connection = null; // if getConnection is called again, this will be able to create other connection
    }

    public static void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
        closeConnection(con);
    }

    public static void closeConnection(Connection con, PreparedStatement stmt,ResultSet rs){
        
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
        closeConnection(con,stmt);
    }

    @Override public void close() throws Exception {
        ConnectionFactory.closeConnection();
    }
}
