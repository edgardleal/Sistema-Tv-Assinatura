/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Connection.ConnectionFactory;
import Negócio.Central;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexf
 */
public class CentralDAO {
    private Connection con = null;

    public CentralDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public List<Central> verificaLogin(){
        String sql = "Select * from central";
        PreparedStatement stmt = null;
        ArrayList<Central> centrais= new ArrayList<>();
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Central c = new Central();
                c.setLogin(rs.getString("login"));
                c.setSenha(rs.getString("senha"));
                centrais.add(c);
            }
        }catch (SQLException ex) {
            System.err.println("Erro ao consultar o banco de dados " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return centrais;
    }
    
}
