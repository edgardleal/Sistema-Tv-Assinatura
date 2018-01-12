/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;

import Banco.PagamentoDAO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 *
 * @author Alexf
 */
public class Pagamento {
    private int idPagamento;
    private LocalDate data;
    private float valor;
    private int pago;
    private LocalDate dataPagamento;
    private int idContrato;
    private int idPlano;
    private int idCliente;

    public Pagamento() {
    }

    public Pagamento( float valor, int idContrato, int idPlano, int idCliente) {
        this.data = this.data = LocalDate.now(ZoneId.of( "America/Sao_Paulo" ));
        this.valor = valor;
        this.pago = 0;
        this.idContrato = idContrato;
        this.idPlano = idPlano;
        this.idCliente = idCliente;
    }

    public Pagamento(int idPagamento) {
        this.idPagamento = idPagamento;
        this.dataPagamento = LocalDate.now(ZoneId.of( "America/Sao_Paulo" ));
        this.pago = 1;
    }

    public Pagamento(int idContrato, int idPlano, int idCliente) {
        this.idContrato = idContrato;
        this.idPlano = idPlano;
        this.idCliente = idCliente;
    }
    
    
    
    

    public Pagamento(int idPagamento, LocalDate data, float valor, int pago, int idContrato, int idPlano, int idCliente) {
        this.idPagamento = idPagamento;
        this.data = data;
        this.valor = valor;
        this.pago = pago;
        this.idContrato = idContrato;
        this.idPlano = idPlano;
        this.idCliente = idCliente;
    }
    

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public LocalDate getDataPagamento() {
        if(dataPagamento!=null){
            return dataPagamento;
        }else{
           return null;
        }
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public static boolean geraFatura(Pagamento pagamento){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.novaFatura(pagamento);
    } 
    
    public static ArrayList<Pagamento> todasFaturas(Pagamento pagamento){
        PagamentoDAO dao = new PagamentoDAO();
        return  (ArrayList<Pagamento>) dao.buscarTodasFaturas(pagamento);
        
    }
    
    public static boolean atualizaPagamento(int id){
        PagamentoDAO dao = new PagamentoDAO();
        Pagamento pagamento = new Pagamento();
        pagamento.setIdPagamento(id);
        return dao.atualizarFatura(pagamento);
    }
    
    public static ArrayList<Pagamento> buscaFaturaNumero(Pagamento pagamento){
        PagamentoDAO dao = new PagamentoDAO();
        return  (ArrayList<Pagamento>) dao.buscarFaturaNumero(pagamento);
    }
    
    public static boolean removeFatura(int idFatura){
        PagamentoDAO dao = new PagamentoDAO();
        return dao.excluirFatura(idFatura);
    }
    
    public static ArrayList<Pagamento> buscaFaturasCancelamento(int idContrato){
        PagamentoDAO dao = new PagamentoDAO();
        return (ArrayList<Pagamento>) dao.buscarFaturaCancelamento(idContrato);
    }
    
    
}
