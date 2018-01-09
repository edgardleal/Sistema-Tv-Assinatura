/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Connection.ConnectionFactory;
import Negócio.Chamado;
import Negócio.Pagamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexf
 */
public class PagamentoDAO {
    private Connection con = null;

    public PagamentoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean novaFatura(Pagamento pagamento){
        String sql = "insert into pagamento (idPagamento, data, valor, pago, Contrato_idContrato, Contrato_Plano_idPlano, Contrato_Cliente_idCliente) Values (?, ?, ?, ? , ?, ?, ?); " ;
        PreparedStatement stmt = null;
        
        java.sql.Date sqlDate = java.sql.Date.valueOf(pagamento.getData()); //LINHA MAIS IMPORTANTE DA BAGAÇA
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, null);
            stmt.setDate(2, sqlDate);
            stmt.setFloat(3, pagamento.getValor());
            stmt.setInt(4, pagamento.getPago());
            stmt.setInt(5, pagamento.getIdContrato());
            stmt.setInt(6, pagamento.getIdPlano());
            stmt.setInt(7, pagamento.getIdCliente());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir no banco, erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public boolean excluirFatura(int idPagamento){
        String sql = "Delete from pagamento where idPagamento= ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPagamento);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir a fatura  do banco" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    
    
    public boolean atualizarFatura(Pagamento pagamento){
        String sql = "update pagamento set pago = ?, dataPagamento = ? where idPagamento = ?";
        PreparedStatement stmt = null;
        LocalDate dataAtual = LocalDate.now(ZoneId.of( "America/Sao_Paulo" ));
        java.sql.Date sqlDate = java.sql.Date.valueOf(dataAtual);
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, 1);
            stmt.setDate(2, sqlDate);
            stmt.setInt(3, pagamento.getIdPagamento());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar o chamado" + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Pagamento> buscarTodasFaturas(Pagamento pagamento2){
        String sql = "	SELECT * from pagamento where Contrato_idContrato = ? and Contrato_Plano_idPlano = ? and Contrato_Cliente_idCliente = ? " ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pagamento> pagamentos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pagamento2.getIdContrato());
            stmt.setInt(2, pagamento2.getIdPlano());
            stmt.setInt(3, pagamento2.getIdCliente());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamento pagamento = new Pagamento();
                pagamento.setIdPagamento(rs.getInt("idPagamento"));
                pagamento.setValor(rs.getFloat("valor"));
                pagamento.setPago(rs.getInt("pago"));
                pagamento.setIdContrato(rs.getInt("Contrato_idContrato"));
                pagamento.setIdPlano(rs.getInt("Contrato_Plano_idPlano"));
                pagamento.setIdCliente(rs.getInt("Contrato_Cliente_idCliente"));
                pagamento.setData (rs.getDate("data").toLocalDate());
                if(rs.getDate("dataPagamento")!=null){
                   pagamento.setDataPagamento(rs.getDate("dataPagamento").toLocalDate());
                }
                pagamentos.add(pagamento);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pagamentos;
    }
    
    public List<Pagamento> buscarFaturaNumero(Pagamento pagamento2){
        String sql = "	SELECT * from pagamento where Contrato_idContrato = ? and Contrato_Plano_idPlano = ? and Contrato_Cliente_idCliente = ? and idPagamento = ? " ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pagamento> pagamentos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pagamento2.getIdContrato());
            stmt.setInt(2, pagamento2.getIdPlano());
            stmt.setInt(3, pagamento2.getIdCliente());
            stmt.setInt(4, pagamento2.getIdPagamento());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamento pagamento = new Pagamento();
                pagamento.setIdPagamento(rs.getInt("idPagamento"));
                pagamento.setValor(rs.getFloat("valor"));
                pagamento.setPago(rs.getInt("pago"));
                pagamento.setIdContrato(rs.getInt("Contrato_idContrato"));
                pagamento.setIdPlano(rs.getInt("Contrato_Plano_idPlano"));
                pagamento.setIdCliente(rs.getInt("Contrato_Cliente_idCliente"));
                pagamento.setData (rs.getDate("data").toLocalDate());
                if(rs.getDate("dataPagamento")!=null){
                   pagamento.setDataPagamento(rs.getDate("dataPagamento").toLocalDate());
                }
                pagamentos.add(pagamento);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pagamentos;
    }
    
    public List<Pagamento> buscarFaturaCancelamento(int idContrato){
        String sql = "	SELECT * from pagamento where Contrato_idContrato = ?" ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pagamento> pagamentos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idContrato);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamento pagamento = new Pagamento();
                pagamento.setIdPagamento(rs.getInt("idPagamento"));
                pagamento.setPago(rs.getInt("pago"));
                pagamentos.add(pagamento);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao procurar no banco" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pagamentos;
    }
    
    
    
    
    
}