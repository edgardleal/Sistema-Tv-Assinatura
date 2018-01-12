/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Connection.ConnectionFactory;
import Negócio.Canal;
import Negócio.Categoria;
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
public class CategoriaDAO {
    private Connection con = null;
    
    public CategoriaDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean incluirCategoria(Categoria categoria){
        String sql = "	INSERT INTO categoria (idCategoria, nomeCategoria) values (?,? ) " ;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, null);
            stmt.setString(2, categoria.getNome());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
        
    public boolean excluirCategoria(Categoria categoria){
        String sql = "Delete from categoria where idCategoria = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir a categoria do banco" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
        
    
    public List<Categoria> buscarTodasCategorias(){
        String sql = "	SELECT * from categoria" ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Categoria> categorias = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setNome(rs.getString("nomeCategoria"));
                categoria.setId(rs.getInt("idCategoria"));
                categorias.add(categoria);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return categorias;
    }
    
    

    
//    public List procurarCategoriasContrato(Contrato contrato){
//        String sql = "";
//        PreparedStatement stmt= null;
//        ResultSet rs = null;
//        
//        try {
//            stmt = con.prepareStatement(sql);
//        } catch (SQLException ex) {
//            System.err.println("Erro ao procurar no banco" + ex );
//        }
//        
//    }
//    
    
    
    
    
}
