/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Connection.ConnectionFactory;
import Negócio.Canal;
import Negócio.Contrato;
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
public class ContratoDAO {
    Connection con = null;

    public ContratoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean incluirContrato(Contrato contrato){
        String sql = "insert into contrato(idContrato, Plano_idPlano, Cliente_idCliente, quantReceptores, endCobranca, acesso) values (?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, null);
            stmt.setInt(2, contrato.getIdPlano());
            stmt.setInt(3, contrato.getIdCliente());
            stmt.setInt(4, contrato.getQuantReceptores());
            stmt.setString(5, contrato.getEndCobranca());
            stmt.setInt(6, contrato.getAcesso());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
}
    
     public Contrato devolveContrato(int idContrato){
        String sql = "select * from contrato inner join plano on Plano_idPlano = idPlano where idContrato= ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Contrato c = new Contrato();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idContrato);
            rs = stmt.executeQuery();
            while(rs.next()){
               c.setIdCliente(rs.getInt("Cliente_idCliente"));
               c.setIdContrato(rs.getInt("idContrato"));
               c.setIdPlano(rs.getInt("Plano_idPlano"));
               c.setNomePlano(rs.getString("nome"));
               c.setQuantReceptores(rs.getInt("quantReceptores"));
               c.setEndCobranca(rs.getString("endCobranca"));
               c.setAcesso(rs.getInt("acesso"));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            
        }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        return c;
    }
    
    
    
    public Contrato devolveContrato(Contrato contrato){
        String sql = "Select * from contrato where Plano_idPlano = ? and Cliente_idCliente = ? and quantReceptores = ? and endCobranca = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Contrato c = new Contrato();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, contrato.getIdPlano());
            stmt.setInt(2, contrato.getIdCliente());
            stmt.setInt(3, contrato.getQuantReceptores());
            stmt.setString(4, contrato.getEndCobranca());
            rs = stmt.executeQuery();
            while(rs.next()){
               c.setIdCliente(rs.getInt("Cliente_idCliente"));
               c.setIdContrato(rs.getInt("idContrato"));
               c.setIdPlano(rs.getInt("Plano_idPlano"));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            
        }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        return c;
    }
    
    public List<Contrato> devolveContratos(int idCliente){
        String sql = "select * from contrato inner join plano on Plano_idPlano = idPlano where Cliente_idCliente= ? ;";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Contrato> contratos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            while(rs.next()){
               Contrato c = new Contrato();
               c.setIdCliente(rs.getInt("Cliente_idCliente"));
               c.setIdContrato(rs.getInt("idContrato"));
               c.setIdPlano(rs.getInt("Plano_idPlano"));
               c.setNomePlano(rs.getString("nome"));
               c.setQuantReceptores(rs.getInt("quantReceptores"));
               c.setEndCobranca(rs.getString("endCobranca"));
               c.setAcesso(rs.getInt("acesso"));
               contratos.add(c);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            
        }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        for(Contrato c2 : contratos){
            System.out.println("No loop do banco o numero do contrato é: " + c2.getIdContrato());
        }
        return contratos;
    }
    
        public List<Contrato> devolveContratosChamado(int idCliente){
        String sql = "select * from contrato inner join plano on Plano_idPlano = idPlano where Cliente_idCliente= ? or ;";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Contrato> contratos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            while(rs.next()){
               Contrato c = new Contrato();
               c.setIdCliente(rs.getInt("Cliente_idCliente"));
               c.setIdContrato(rs.getInt("idContrato"));
               c.setIdPlano(rs.getInt("Plano_idPlano"));
               c.setNomePlano(rs.getString("nome"));
               c.setQuantReceptores(rs.getInt("quantReceptores"));
               c.setEndCobranca(rs.getString("endCobranca"));
               c.setAcesso(rs.getInt("acesso"));
               contratos.add(c);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            
        }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        return contratos;
    }
    
    public boolean excluirContratoPt1(int idContrato){
        String sql = "delete from pagamento where Contrato_idContrato = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idContrato);
            
            stmt.executeUpdate();
           return excluirContratoPt2(idContrato);
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir o contrato no banco pt1, erro: " + ex);
            return false;
        }
        finally{
            //ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
        
    public boolean excluirContratoPt2(int idContrato){
        String sql = "delete from Contrato_has_Categoria where Contrato_idContrato = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idContrato);
            stmt.executeUpdate();
            return excluirContratoPt3(idContrato);
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir o contrato no banco pt2, erro: " + ex);
           return false;
        }
        finally{
            //ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    
    public boolean excluirContratoPt3(int idContrato){
        String sql = "delete from Contrato where idContrato = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idContrato);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir o contrato no banco pt3, erro: " + ex);
           return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }


    
    
}
