/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Connection.ConnectionFactory;
import Negócio.Canal;
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
public class CanalDAO {
    private Connection con = null;

    public CanalDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean incluirCanal(Canal canal){
        String sql = "	INSERT INTO canal (idCanal, nomeCanal,classEtaria,precoBase,Categoria_idCategoria) values (?,?,?,?,?)" ;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, null);
            stmt.setString(2, canal.getNome());
            stmt.setInt(3, canal.getClassEtaria());
            stmt.setFloat(4, canal.getPrecoBase());
            stmt.setInt(5, canal.getIdCategoria());

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
}
    public boolean atualizarCanal(Canal canal){
        String sql = "UPDATE canal set nomeCanal = ?, classEtaria = ?, precoBase = ?, Categoria_idCategoria=? where idCanal = ?" ;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, canal.getNome());
            stmt.setInt(2, canal.getClassEtaria());
            stmt.setFloat(3, canal.getPrecoBase());
            stmt.setInt(4, canal.getIdCategoria());
            stmt.setInt(5, canal.getNumero());

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar no banco, erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public boolean excluirCanal(Canal canal){
        String sql = "Delete from canal where idCanal = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, canal.getNumero());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir o canal do banco" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public List<Canal> buscarTodosCanais(){
        String sql = "select * from canal  inner join categoria on Categoria_idCategoria = idCategoria " ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Canal> canais = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Canal canal = new Canal();
                canal.setNome(rs.getString("nomeCanal"));
                canal.setNumero(rs.getInt("idCanal"));
                canal.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                canal.setPrecoBase(rs.getFloat("precoBase"));
                canal.setClassEtaria(rs.getInt("classEtaria"));
                canal.setNomeCategoria(rs.getString("nomeCategoria"));
                canais.add(canal);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return canais;
    }
    
    public List<Canal> buscarTodosCanaisComCategoria(int  id){
        String sql = "	select * from canal  inner join categoria on Categoria_idCategoria = idCategoria where Categoria_idCategoria= ?" ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Canal> canais = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Canal canal = new Canal();
                canal.setNome(rs.getString("nomeCanal"));
                canal.setNumero(rs.getInt("idCanal"));
                canal.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                canal.setPrecoBase(rs.getFloat("precoBase"));
                canal.setClassEtaria(rs.getInt("classEtaria"));
                canal.setNomeCategoria(rs.getString("nomeCategoria"));
                canais.add(canal);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
//            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return canais;
    }
    
    public List<Canal> buscarCanal(String nome){
        String sql = "select * from canal  inner join categoria on Categoria_idCategoria = idCategoria where nomeCanal like ? " ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Canal> canais = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%"+ nome + "%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Canal canal = new Canal();
                canal.setNome(rs.getString("nomeCanal"));
                canal.setNumero(rs.getInt("idCanal"));
                canal.setIdCategoria(rs.getInt("Categoria_idCategoria"));
                canal.setPrecoBase(rs.getFloat("precoBase"));
                canal.setClassEtaria(rs.getInt("classEtaria"));
                canal.setNomeCategoria(rs.getString("nomeCategoria"));
                canais.add(canal);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return canais;
    }
    
    
}
