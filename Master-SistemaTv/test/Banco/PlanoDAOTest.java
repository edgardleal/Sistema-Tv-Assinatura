/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Negócio.Contrato;
import Negócio.Plano;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Alexf
 */
public class PlanoDAOTest {
    
    public PlanoDAOTest() {
    }

    @Ignore
    public void incluir() {
        Plano plano = new Plano("Topper", 3);
        PlanoDAO dao = new PlanoDAO();
        
        dao.incluirPlano(plano);
    }
    
    @Ignore
    public void atualizar() {
        Plano plano = new Plano("Topzera", 2, 1);
        PlanoDAO dao = new PlanoDAO();
        
        dao.atualizarPlano(plano);
    }
    @Ignore
    public void excluir() {
        Plano plano = new Plano("Topzera", 2, 1);
        PlanoDAO dao = new PlanoDAO();
        
        dao.excluirPlano(plano);
    }
    @Ignore
    public void listar() {
        //Plano plano = new Plano("Topzera", 2, 1);
        ArrayList<Plano> planos = new ArrayList<>();
        PlanoDAO dao = new PlanoDAO();
        
        planos = (ArrayList<Plano>) dao.buscarTodosPlanos();
        
        for(Plano plano: planos){
            System.out.println(plano.getNome());
        }
    }
    
    @Test
    public void listaProcurandoContrato(){
        Contrato contrato = new Contrato();
        contrato.setIdContrato(1);
        Plano plano = new Plano();
        PlanoDAO dao = new PlanoDAO();
        plano = dao.buscarPlano(contrato);
        System.out.println(plano.getNome());
        
    }
    
}