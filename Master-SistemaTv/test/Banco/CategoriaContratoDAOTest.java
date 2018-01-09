/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Negócio.Categoria;
import Negócio.CategoriaContrato;
import Negócio.Contrato;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Alexf
 */
public class CategoriaContratoDAOTest {
    
    public CategoriaContratoDAOTest() {
    }

    @Test
    public void incluir() {
        CategoriaContrato cc2 = new CategoriaContrato(1, 1, 1, 2);
        
        CategoriaContratoDAO dao = new CategoriaContratoDAO();
        
        dao.incluirCategoriaContrato(cc2);
    }
    
    @Ignore
    public void procuraCategoriaContrato(){
        ArrayList<Categoria> categorias = new ArrayList<>();
        Contrato a  = new Contrato();
        a.setIdContrato(1);
        CategoriaContratoDAO dao = new CategoriaContratoDAO();
        categorias = (ArrayList<Categoria>) dao.procurarCategoriasContrato(a);
        
        for(Categoria categoria :categorias){
            System.out.println(categoria.getNome());
        }
    }
    
    
    
}
