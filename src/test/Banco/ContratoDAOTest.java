/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Negócio.Contrato;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexf
 */
public class ContratoDAOTest {
    
    public ContratoDAOTest() {
    }

    @Test
    public void incluir() {
        Contrato contrato = new Contrato(1, 1, 1, "Rua das gaivotas");
        ContratoDAO dao = new  ContratoDAO();
        
        dao.incluirContrato(contrato);
    }
    
}
