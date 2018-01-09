/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;

/**
 *
 * @author Pedro
 */
public class CanalInfantil extends Canal {

    public CanalInfantil(String nome, int numero, int classEtaria, float precoBase, int idCategoria) {
        super(nome, numero, classEtaria, precoBase, idCategoria);
    }

    public CanalInfantil(String nome, int classEtaria, float precoBase, int idCategoria) {
        super(nome, classEtaria, precoBase, idCategoria);
    }
    
    
}
