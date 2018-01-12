/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Negócio.Canal;
import Negócio.CanalNoticias;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Alexf
 */
public class CanalDAOTest {
    
    public CanalDAOTest() {
    }

    @Ignore
    public void incluir() {
        CanalNoticias can = new CanalNoticias("Globo News", 13, (float) 50.0, 1); 
        CanalDAO  dao = new CanalDAO();
        
        if(dao.incluirCanal(can)){
            System.out.println("Incluido com sucesso no banco");
        }else{
            fail("Erro ao inserir no banco pelo teste");
        }
    }
    
    @Ignore
    public void atualizar(){
    
       Canal canal = new Canal("Novo disney chanel",1 ,12, (float) 50.0, 1);
        CanalDAO dao = new CanalDAO();
        
        if(dao.atualizarCanal(canal)){
            System.out.println("Canal atualizado com sucesso!");
        }else{
            System.out.println("Erro ao atualizar");
        }
    }
    @Ignore
    public void excluir(){
        Canal canal = new Canal("Alterei", 12, 99, 9, 3);
        CanalDAO dao = new CanalDAO();
        
        if(dao.excluirCanal(canal)){
            System.out.println("Canal excluido com sucesso!");
        }else{
            System.out.println("Erro ao excluir");
        }
    }
    @Test
    public void buscarTodosCanais(){
        
       ArrayList<Canal> canais = new ArrayList<>();
       CanalDAO dao = new CanalDAO();
       canais = (ArrayList<Canal>) dao.buscarTodosCanais();
       for(Canal canal: canais){
           System.out.println(canal.getNome());
        }
    }




}
