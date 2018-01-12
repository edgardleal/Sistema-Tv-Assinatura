/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Connection.ConnectionFactory;
import Negócio.Canal;
import Negócio.Cliente;
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
 * @author 071610030
 */
public class ClienteDAO {
    private Connection con;
    
    public ClienteDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean incluirCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (idCliente, nomeCliente, email, cpf, telefone) values (?, ?,  ? ,? ,? )  " ;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, null);
            stmt.setString(2, cliente.getNome());
            stmt.setString(3,cliente.getEmail());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefone());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public boolean excluirCliente(Cliente cliente){
        String sql = "Delete from cliente where idCliente = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir o cliente do banco" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean atualizarCliente(Cliente cliente){
        String sql = "update cliente set nomeCliente = ?, email = ?, cpf = ?, telefone = ? where idCliente = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setInt(5, cliente.getIdCliente());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar o cliente" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente> procurarTodosClientes(){
        String sql = "	SELECT * from cliente " ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setNome(rs.getString("nomeCliente"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes ;
    }
    
        public List<Cliente> procurarTodosClientes(String dado){
        String sql = "	SELECT * from cliente where  nomeCliente like ?  or cpf = ? " ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,"%" +  dado + "%");
            stmt.setString(2, dado);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setNome(rs.getString("nomeCliente"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes ;
    }
        
        public Cliente procurarCliente(String dado){
        String sql = "	SELECT * from cliente where cpf = ? " ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, dado);
            rs = stmt.executeQuery();
            while(rs.next()){
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setNome(rs.getString("nomeCliente"));
                cliente.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cliente;
    }
    
}
