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
public class CanalFilmes extends Canal {

    public CanalFilmes(String nome, int numero, int classEtaria, float precoBase, int idCategoria) {
        super(nome, numero, classEtaria, precoBase, idCategoria);
    }

    public CanalFilmes(String nome, int classEtaria, float precoBase, int idCategoria) {
        super(nome, classEtaria, precoBase, idCategoria);
    }
    
    
    
}
