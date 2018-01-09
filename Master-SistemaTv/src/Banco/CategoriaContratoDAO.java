/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Connection.ConnectionFactory;
import Negócio.Canal;
import Negócio.Categoria;
import Negócio.CategoriaContrato;
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
public class CategoriaContratoDAO {
    Connection con = null;

    public CategoriaContratoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean incluirCategoriaContrato(CategoriaContrato cc){
        String sql = "insert into contrato_has_categoria (Contrato_idContrato, Contrato_Plano_idPlano, Contrato_Cliente_idCliente, Categoria_idCategoria) values (?,?,?,?)" ;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cc.getIdContrato());
            stmt.setInt(2, cc.getIdPlano());
            stmt.setInt(3, cc.getIdCliente());
            stmt.setInt(4, cc.getIdCategoria());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            return false;
        }finally{
        }
    }
    
    public List procurarCategoriasContrato(Contrato contrato){
        String sql = " select ca.* from contrato co, categoria ca, contrato_has_categoria cc where co.idContrato = ? and cc.Categoria_idCategoria = ca.idCategoria and cc.Contrato_idContrato = co.idContrato";
        PreparedStatement stmt= null;
        ResultSet rs = null;
        List<Categoria> categorias = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, contrato.getIdContrato());
            rs = stmt.executeQuery();
            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nomeCategoria"));
                categorias.add(categoria);
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex );
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return categorias;
    }
    
    public boolean removeCategoriaContrato(CategoriaContrato cc){
        String sql = " delete from contrato_has_categoria where Contrato_idContrato = ? and Contrato_Plano_idPlano = ? and Contrato_Cliente_idCliente = ? and Categoria_idCategoria = ? ";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cc.getIdContrato());
            stmt.setInt(2, cc.getIdPlano());
            stmt.setInt(3, cc.getIdCliente());
            stmt.setInt(4, cc.getIdCategoria());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir do banco" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    
    
    
    
    
    
}
