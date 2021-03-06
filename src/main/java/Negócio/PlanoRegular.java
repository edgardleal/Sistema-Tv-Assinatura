/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;

import Banco.CanalDAO;
import Banco.CategoriaContratoDAO;
import Banco.CategoriaDAO;
import java.util.ArrayList;

/**
 *
 * @author Alexf
 */
public class PlanoRegular extends Plano{
    public static float calculaPlanoRegular(int idContrato){
        Contrato c = Contrato.devolveContrato(idContrato);
        if(c.getIdPlano()==3){
            Contrato contrato = new Contrato();
            contrato.setIdContrato(idContrato);
            float valor = 0;
            CategoriaContratoDAO dao = new CategoriaContratoDAO();
            CanalDAO dao2 = new CanalDAO();
            ArrayList<Categoria> categorias = (ArrayList<Categoria>) dao.procurarCategoriasContrato(contrato);
            ArrayList<Canal> canais = new ArrayList<>();
            
            for(Categoria categoria: categorias){
                ArrayList<Canal> provisorio =  (ArrayList<Canal>) dao2.buscarTodosCanaisComCategoria(categoria.getId());
                canais.addAll(provisorio);
            }
            
            for(Canal canal: canais){
                valor = canal.getPrecoBase() + valor;
            }
            
            return (float) (valor * 0.8);
        }else{
            return -1;
        }
    }
    
    public static float calculaPlanoRegular(ArrayList<Integer> numeros){
        ArrayList<Categoria> categorias = new ArrayList<>();
        CanalDAO dao2 = new CanalDAO();
        float valor=0f;
        for(int i: numeros){
            Categoria c = new Categoria();
            c.setId(i);
            categorias.add(c);
        }
        ArrayList<Canal> canais = new ArrayList<>();
        
        for(Categoria categoria: categorias){
            ArrayList<Canal> provisorio =  (ArrayList<Canal>) dao2.buscarTodosCanaisComCategoria(categoria.getId());
            canais.addAll(provisorio);
        }

        for(Canal canal: canais){
            valor = canal.getPrecoBase() + valor;
        }

        return (valor * 0.8f);
    }
}
