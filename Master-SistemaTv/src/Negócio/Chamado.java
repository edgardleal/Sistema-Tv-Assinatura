/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;

import Banco.ChamadoDAO;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author thiago
 */
public class Chamado {
    private int idChamado;
    private LocalDate data;
    private LocalDate ultima_atualizacao;
    private String situacao, motivo;
    private int idCliente;
    private int idPlano;
    private int idContrato;

    public Chamado(LocalDate data, String situacao, String motivo, int idCliente) {
        this.data = data;
        this.situacao = situacao;
        this.motivo = motivo;
        this.idCliente = idCliente;
    }
    // construtor que ja te da a data e o id o banco controla

    public Chamado(int idChamado, String situacao) {
        this.idChamado = idChamado;
        this.situacao = situacao;
    }
    
    
    public Chamado( String situacao, String motivo, int idCliente, int idPlano, int idContrato) {
        this.situacao = situacao;
        this.motivo = motivo;
        this.idCliente = idCliente;
        this.idPlano = idPlano;
        this.idContrato = idContrato;
        this.data = LocalDate.now(ZoneId.of( "America/Sao_Paulo" ));
    }

    public Chamado(int idChamado, LocalDate ultima_atualizacao, String situacao, String motivo) {
        this.idChamado = idChamado;
        this.ultima_atualizacao = ultima_atualizacao;
        this.situacao = situacao;
        this.motivo = motivo;
    }

     
    
    public Chamado() {
    }
    
    
    ///////////////////getters e setters//////////////////

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    
    
    public LocalDate getUltima_atualizacao() {
        ultima_atualizacao = LocalDate.now(ZoneId.of( "America/Sao_Paulo" ));
        return ultima_atualizacao ; 
    }

    public void setUltima_atualizacao(LocalDate ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }

    
    
    public int getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(int idChamado) {
        this.idChamado = idChamado;
    }
    
    
    
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    //toString

    @Override
    public String toString() {
        return "Chamado{" + "data=" + data + ", situacao=" + situacao + ", motivo=" + motivo + ", Id Cliente=" + idCliente + '}';
    }
    
    public static boolean adicionaChamado(Chamado chamado){
        ChamadoDAO dao = new ChamadoDAO();
        return dao.incluirChamado(chamado);
    }

    public static ArrayList<Chamado> procuraChamados(int idContrato){
        ChamadoDAO dao = new ChamadoDAO();
        return (ArrayList<Chamado>) dao.buscarTodosChamados(idContrato);
    }
    
    public static boolean atualizaChamado(Chamado chamado){
        ChamadoDAO dao= new ChamadoDAO();
        return dao.atualizarChamado(chamado);
    }
    
    public static boolean excluiChamado(int idChamado){
        ChamadoDAO dao = new ChamadoDAO();
        return dao.removerChamado(idChamado);
    }
    
    public static ArrayList<Chamado> procuraChamados(int idContrato, int idChamado){
        ChamadoDAO dao = new ChamadoDAO();
        return (ArrayList<Chamado>) dao.BuscarChamado(idContrato, idChamado);
    }
    
    
    
}
