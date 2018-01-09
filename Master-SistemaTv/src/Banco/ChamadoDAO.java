/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Connection.ConnectionFactory;
import Negócio.Canal;
import Negócio.Chamado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Alexf
 */
public class ChamadoDAO {
    public Connection con ;
    public ChamadoDAO(){
        con = ConnectionFactory.getConnection();
    }
    
    public boolean incluirChamado(Chamado chamado){
        //java.util.Date date = new java.util.Date();
        //String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        String sql = "INSERT INTO Chamado (idChamado,data,situacao,motivo,ultimaAtualizacao,Contrato_idContrato,Contrato_Plano_idPlano,Contrato_Cliente_idCliente) values (?,?,?,?,?,?,?,?) " ;
        PreparedStatement stmt = null;
        
        java.sql.Date sqlDate = java.sql.Date.valueOf(chamado.getData()); //LINHA MAIS IMPORTANTE DA BAGAÇA
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, null);
            stmt.setDate(2, sqlDate);
            stmt.setString(3, chamado.getSituacao());
            stmt.setString(4,chamado.getMotivo());
            stmt.setDate(5, sqlDate);
            stmt.setInt(6, chamado.getIdContrato());
            stmt.setInt(7, chamado.getIdPlano());
            stmt.setInt(8, chamado.getIdCliente());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public List<Chamado> buscarTodosChamados(int idContrato){
        String sql = "	SELECT * from chamado where Contrato_idContrato= ?" ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Chamado> chamados = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idContrato);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Chamado chamado = new Chamado();
                chamado.setIdChamado(rs.getInt("idChamado"));
                chamado.setData (rs.getDate("data").toLocalDate());
                chamado.setMotivo(rs.getString("motivo"));
                chamado.setSituacao(rs.getString("situacao"));
                chamado.setUltima_atualizacao(rs.getDate("ultimaAtualizacao").toLocalDate());
                chamado.setIdContrato(rs.getInt("Contrato_idContrato"));
                chamado.setIdPlano(rs.getInt("Contrato_Plano_idPlano"));
                chamado.setIdCliente(rs.getInt("Contrato_Cliente_idCliente"));
                chamados.add(chamado);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return chamados;
    }
    
     public List<Chamado> BuscarChamado(int idContrato, int idChamado){
        String sql = "	SELECT * from chamado where Contrato_idContrato= ? and idChamado = ?  " ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Chamado> chamados = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idContrato);
            stmt.setInt(2, idChamado);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Chamado chamado = new Chamado();
                chamado.setIdChamado(rs.getInt("idChamado"));
                chamado.setData (rs.getDate("data").toLocalDate());
                chamado.setMotivo(rs.getString("motivo"));
                chamado.setSituacao(rs.getString("situacao"));
                chamado.setUltima_atualizacao(rs.getDate("ultimaAtualizacao").toLocalDate());
                chamado.setIdContrato(rs.getInt("Contrato_idContrato"));
                chamado.setIdPlano(rs.getInt("Contrato_Plano_idPlano"));
                chamado.setIdCliente(rs.getInt("Contrato_Cliente_idCliente"));
                chamados.add(chamado);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return chamados;
    }
    
    

    
    public boolean atualizarChamado(Chamado chamado){
        String sql = "update chamado set ultimaAtualizacao = ?, situacao = ?  where idChamado = ?";
        PreparedStatement stmt = null;
        LocalDate dataAtt = LocalDate.now(ZoneId.of( "America/Sao_Paulo" ));
        java.sql.Date sqlDate = java.sql.Date.valueOf(dataAtt);
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, sqlDate);
            stmt.setString(2, chamado.getSituacao());
            stmt.setInt(3, chamado.getIdChamado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar o chamado" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public boolean removerChamado(int idChamado){
        String sql = "Delete from chamado where idChamado = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idChamado);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir o chamado do banco" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
}
