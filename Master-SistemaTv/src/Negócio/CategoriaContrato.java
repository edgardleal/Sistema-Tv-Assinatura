/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;

import Banco.CategoriaContratoDAO;
import java.util.ArrayList;

/**
 *
 * @author Alexf
 */
public class CategoriaContrato {
    private int idContrato;
    private int idPlano;
    private int idCliente;
    private int idCategoria;

    public CategoriaContrato(int idContrato, int idPlano, int idCliente, int idCategoria) {
        this.idContrato = idContrato;
        this.idPlano = idPlano;
        this.idCliente = idCliente;
        this.idCategoria = idCategoria;
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    
    public static boolean adicionaIlimitado(Contrato contrato){
        boolean habilitado = true;
        CategoriaContrato cc = new CategoriaContrato(contrato.getIdContrato(), contrato.getIdPlano(), contrato.getIdCliente(), 1);
        System.out.println("idContrato: " + contrato.getIdContrato() + " IdPlano: " + contrato.getIdPlano() + " Id Cliente: " + contrato.getIdCliente() ) ;
        CategoriaContrato cc2 = new CategoriaContrato(contrato.getIdContrato(), contrato.getIdPlano(), contrato.getIdCliente(), 2);
        CategoriaContrato cc3 = new CategoriaContrato(contrato.getIdContrato(), contrato.getIdPlano(), contrato.getIdCliente(), 3);
        CategoriaContrato cc4 = new CategoriaContrato(contrato.getIdContrato(), contrato.getIdPlano(), contrato.getIdCliente(), 4);
        CategoriaContratoDAO  dao = new CategoriaContratoDAO();
        habilitado = dao.incluirCategoriaContrato(cc);
        habilitado = dao.incluirCategoriaContrato(cc2);
        habilitado = dao.incluirCategoriaContrato(cc3);
        return habilitado = dao.incluirCategoriaContrato(cc4);
    }
    
    
    public static boolean adicionaTop(Contrato contrato, ArrayList<Integer> numeros){
        boolean habilitado = true;
        CategoriaContrato cc = new CategoriaContrato(contrato.getIdContrato(), contrato.getIdPlano(), contrato.getIdCliente(), numeros.get(0)); 
        CategoriaContrato cc2 = new CategoriaContrato(contrato.getIdContrato(), contrato.getIdPlano(), contrato.getIdCliente(), numeros.get(1)); 
        CategoriaContrato cc3 = new CategoriaContrato(contrato.getIdContrato(), contrato.getIdPlano(), contrato.getIdCliente(), numeros.get(2));
        CategoriaContratoDAO dao = new CategoriaContratoDAO();
        habilitado = dao.incluirCategoriaContrato(cc);
        habilitado = dao.incluirCategoriaContrato(cc2);
        return habilitado = dao.incluirCategoriaContrato(cc3);
        
    }
    
    public static boolean adicionaRegular(Contrato contrato, ArrayList<Integer> numeros){
        boolean habilitado = true;
        CategoriaContrato cc = new CategoriaContrato(contrato.getIdContrato(), contrato.getIdPlano(), contrato.getIdCliente(), numeros.get(0)); 
        CategoriaContrato cc2 = new CategoriaContrato(contrato.getIdContrato(), contrato.getIdPlano(), contrato.getIdCliente(), numeros.get(1)); 
        CategoriaContratoDAO dao = new CategoriaContratoDAO();
        habilitado = dao.incluirCategoriaContrato(cc);
        return habilitado = dao.incluirCategoriaContrato(cc2);
        
    }
    
    
    
    
}
