/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Negócio.Cliente;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Alexf
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
    }

    @Ignore
    public void incluir() {
        Cliente cliente = new Cliente("Caio", "caio@hotmail.com.br", "222222", "333333");
        ClienteDAO dao = new ClienteDAO();
        
        dao.incluirCliente(cliente);
        
    }
    @Ignore
     public void exluir() {
        Cliente cliente = new Cliente("Caio", "caio@hotmail.com.br", "222222", "333333");
        ClienteDAO dao = new ClienteDAO();
        
        dao.excluirCliente(cliente);
        
    }
     @Ignore
     public void atualizar() {
        Cliente cliente = new Cliente("Alex Freire", "alexfreire@hotmail.com.br", "222222", "333333",4);
        ClienteDAO dao = new ClienteDAO();
        
        dao.atualizarCliente(cliente);
        
    }
     @Test
     public void listarTodos(){
         ArrayList<Cliente> clientes = new ArrayList<>();
         ClienteDAO dao = new ClienteDAO();
         
         clientes = (ArrayList<Cliente>) dao.procurarTodosClientes();
         Cliente jesus = new Cliente();
        
         for(Cliente cliente: clientes){
             System.out.println(cliente.getEmail());
         }
     }
    
}
