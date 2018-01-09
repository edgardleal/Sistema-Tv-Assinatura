/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Connection.ConnectionFactory;
import Negócio.Canal;
import Negócio.Contrato;
import Negócio.Plano;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexf
 */
public class PlanoDAO {
    Connection con = null;

    public PlanoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean incluirPlano(Plano plano){
        String sql = "	Insert into plano (idPlano, nome, numeroCategorias) values ( ? ,?, ?)" ;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, null);
            stmt.setString(2, plano.getNome());
            stmt.setInt(3, plano.getNumeroCategorias());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
        public boolean atualizarPlano(Plano plano){
        String sql = "UPDATE plano set idPlano =?, nome = ?, numeroCategorias= ? where idPlano = ?" ;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, plano.getId());
            stmt.setString(2, plano.getNome());
            stmt.setInt(3, plano.getNumeroCategorias());
            stmt.setInt(4, plano.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar no banco, erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
        
    public boolean excluirPlano(Plano plano){
        String sql = "Delete from plano where idPlano = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, plano.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir o plano do banco" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public List<Plano> buscarTodosPlanos(){
        String sql = "SELECT * from plano " ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Plano> planos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Plano plano = new Plano();
                plano.setNome(rs.getString("nome"));
                plano.setId(rs.getInt("idPlano"));
                plano.setNumeroCategorias(rs.getInt("numeroCategorias"));
                planos.add(plano);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return planos;
    }
    
    
    public Plano buscarPlano(Contrato contrato){
        String sql = " select * from plano inner join contrato on Plano_idPlano = idPlano where idContrato = ?" ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Plano plano = new  Plano();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, contrato.getIdContrato());
            rs = stmt.executeQuery();
            rs.next();
            
            plano.setId(rs.getInt("idPlano"));
            plano.setNome(rs.getString("nome"));
            plano.setNumeroCategorias(rs.getInt("numeroCategorias"));
            
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return plano;
    }
    
    
        
    
    
    
    
}
