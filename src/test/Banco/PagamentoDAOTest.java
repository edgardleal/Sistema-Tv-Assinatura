/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Negócio.Chamado;
import Negócio.Pagamento;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Alexf
 */
public class PagamentoDAOTest {
    
    public PagamentoDAOTest() {
    }

    @Ignore
    public void novaFatura() {
        PagamentoDAO dao = new PagamentoDAO();
        Pagamento pagamento = new Pagamento(357, 1, 1, 1);
        
        dao.novaFatura(pagamento);
    }
    
    @Test
    public void atualizaFatura(){
        PagamentoDAO dao = new PagamentoDAO();
        Pagamento pagamento = new Pagamento(2);
        dao.atualizarFatura(pagamento);
    }
}
