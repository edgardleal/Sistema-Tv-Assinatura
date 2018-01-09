/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;

import Banco.CategoriaContratoDAO;
import Banco.ContratoDAO;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Contrato {
    private int idContrato;
    private int idPlano;
    private String nomePlano;
    private int idCliente;
    private int quantReceptores;
    private String endCobranca;
    private int acesso;
    
    private ArrayList<Categoria> categorias;
    private ArrayList<Canal> canais;
    
    public Contrato() {
    }

    public Contrato(int idPlano, int idCliente, int quantReceptores, String endCobranca) {
        this.idPlano = idPlano;
        this.idCliente = idCliente;
        this.quantReceptores = quantReceptores;
        this.endCobranca = endCobranca;
        this.acesso = 1;
    }

    public Contrato(int idContrato, int idPlano, int idCliente, int quantReceptores, String endCobranca, int acesso) {
        this.idContrato = idContrato;
        this.idPlano = idPlano;
        this.idCliente = idCliente;
        this.quantReceptores = quantReceptores;
        this.endCobranca = endCobranca;
        this.acesso = acesso;
    }

    
    
    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
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

    public int getQuantReceptores() {
        return quantReceptores;
    }

    public void setQuantReceptores(int quantReceptores) {
        this.quantReceptores = quantReceptores;
    }

    public String getEndCobranca() {
        return endCobranca;
    }

    public void setEndCobranca(String endCobranca) {
        this.endCobranca = endCobranca;
    }

    public int getAcesso() {
        return acesso;
    }

    public void setAcesso(int acesso) {
        this.acesso = acesso;
    }
    
    public static boolean adicionaContrato(Contrato contrato){
        ContratoDAO dao = new ContratoDAO();
        return dao.incluirContrato(contrato);
    }
    
    public static Contrato devolveContrato(Contrato contrato){
        ContratoDAO dao = new ContratoDAO();
        Contrato contrato_novo = new Contrato();
        return contrato_novo = dao.devolveContrato(contrato);
    }

    public static ArrayList<Contrato> devolveContratos(int idCliente){
        ContratoDAO dao = new ContratoDAO();
        ArrayList<Contrato> contratos = new ArrayList<>();
        contratos =  (ArrayList<Contrato>) dao.devolveContratos(idCliente);
        return contratos;
        
    }
    
    public static Contrato devolveContrato(int idContrato){
        ContratoDAO dao = new ContratoDAO();
        return dao.devolveContrato(idContrato);
    }
    
    public static ArrayList<Categoria> devolveCategoriasContrato(int idContrato){
        CategoriaContratoDAO dao = new CategoriaContratoDAO();
        Contrato contrato = new Contrato();
        contrato.setIdContrato(idContrato);
        return (ArrayList<Categoria>) dao.procurarCategoriasContrato(contrato);
    }
    
    public static boolean removeContrato (int idContrato){
        ContratoDAO dao = new ContratoDAO();
        return dao.excluirContratoPt1(idContrato);
    }
    
    
    
    
}
