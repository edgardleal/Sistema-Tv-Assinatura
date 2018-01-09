/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Negócio.Canal;
import Negócio.Categoria;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Alexf
 */
public class CategoriaDAOTest {
    
    public CategoriaDAOTest() {
    }

    @Ignore
    public void buscarTodasCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        CategoriaDAO dao = new CategoriaDAO();
        categorias = (ArrayList<Categoria>) dao.buscarTodasCategorias();
        for(Categoria categoria: categorias){
            System.out.println(categoria.getNome());
            
        }
    }
    
     @Ignore
     public void adicionar(){
        Categoria categoria = new Categoria("Teste");
        CategoriaDAO dao = new CategoriaDAO();
        
        dao.incluirCategoria(categoria);
    }
    
     @Test
     public void excluir(){
        Categoria categoria = new Categoria("Teste",4);
        CategoriaDAO dao = new CategoriaDAO();
        
        dao.excluirCategoria(categoria);
    }
    
    
   
   }
    
    

