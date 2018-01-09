/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;

import Banco.CanalDAO;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Canal {
  protected String nome;
  protected int numero;
  protected int classEtaria;
  protected float precoBase;
  protected int idCategoria;
  protected String nomeCategoria;
    
  
    //Construtor de canal com o numero do canal
    public Canal(String nome, int numero, int classEtaria, float precoBase, int idCategoria) {
        this.nome = nome;
        this.numero = numero;
        this.classEtaria = classEtaria;
        this.precoBase = precoBase;
        this.idCategoria = idCategoria;
    }
    
    //Construtor de canal sem o numero do canal
    public Canal(String nome, int classEtaria, float precoBase, int idCategoria) {
        this.nome = nome;
        this.classEtaria = classEtaria;
        this.precoBase = precoBase;
        this.idCategoria = idCategoria;
    }

    public Canal(int numero) {
        this.numero = numero;
    }
    

    public Canal() {
    }
    
    
    public static ArrayList<Canal> buscarCanais(){
        CanalDAO dao = new CanalDAO();
        ArrayList<Canal> canais = (ArrayList<Canal>) dao.buscarTodosCanais();
        return canais;
    }
    
    public static boolean excluirCanal(int numero){
        CanalDAO dao = new CanalDAO();
        Canal canal = new Canal(numero);
        return dao.excluirCanal(canal);
    }
    
    public static ArrayList<Canal> buscaCanal(String nome){
        CanalDAO dao = new CanalDAO();
        ArrayList<Canal> canais = (ArrayList<Canal>) dao.buscarCanal(nome);
        return canais;
    }
    
    public static boolean atualizarCanal(Canal canal){
        CanalDAO dao = new CanalDAO();
        return dao.atualizarCanal(canal);
    }
    
    public static boolean adicionarCanal(Canal canal){
        CanalDAO dao = new CanalDAO();
        return dao.incluirCanal(canal);
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
    
    
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

  
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public int getClassEtaria() {
        return classEtaria;
    }

    public void setClassEtaria(int classEtaria) {
        this.classEtaria = classEtaria;
    }

    public float getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(float precoBase) {
        this.precoBase = precoBase;
    }

    @Override
    public String toString() {
        return "Canal{" + "nome=" + nome + ", numero=" + numero + ", classEtaria=" + classEtaria + ", precoBase=" + precoBase + '}';
    }
    
}
