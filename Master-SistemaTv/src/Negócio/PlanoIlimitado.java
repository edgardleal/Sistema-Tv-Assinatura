/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;

/**
 *
 * @author Alexf
 */
public class PlanoIlimitado extends Plano{
   
    
    public static float calculaPlanoIlimitado(int idContrato){
        Contrato c = Contrato.devolveContrato(idContrato);
        if(c.getIdPlano()==1){
            return 700;
        }else{
            return -1;
        }
    }
    
    public static float calculaPlanoIlimitado(){
            return 700;
    }
    
    
}
