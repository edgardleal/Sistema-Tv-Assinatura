/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;

import Banco.PlanoDAO;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Pedro
 */
public class Plano {
    private ArrayList<Categoria> categorias;
    private String nome;
    private int id;
    private int numeroCategorias;
    
//    private ArrayList<Canal> canais; //inclusão para testes, ainda a definir se vai ficar dessa forma

    public Plano(String nome) {
        this.categorias = new ArrayList<>();
        this.nome = nome;
    }

    public Plano(String nome, int numeroCategorias) {
        this.nome = nome;
        this.numeroCategorias = numeroCategorias;
    }

    public Plano(String nome, int id, int numeroCategorias) {
        this.nome = nome;
        this.id = id;
        this.numeroCategorias = numeroCategorias;
    }

    public Plano() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroCategorias() {
        return numeroCategorias;
    }

    public void setNumeroCategorias(int numeroCategorias) {
        this.numeroCategorias = numeroCategorias;
    }
    
    
    
//    
//    public Plano(String nome, ArrayList<Categoria> categorias){
//        this.nome = nome;
//        this.categorias = categorias;
//    }
//    public Plano(ArrayList<Canal> canais){
//        this.nome = "TOP";
//        this.canais = canais;
//    }
    
    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float calcularMensalidade(){
        Categoria buffer;
        float mensalidade = 0f;
        for (Iterator it = this.categorias.iterator(); it.hasNext();){
            buffer = (Categoria)it.next();
            mensalidade+=buffer.valorCategoria();
        }
        return mensalidade;
    }
    
    public boolean adicionarCategoria(Categoria categoria){
        if(!categorias.contains(categoria)){
            categorias.add(categoria);
            return true;
        }else{
            return false;
        }
    }
    public boolean removerCategoria(Categoria categoria){
        if(categorias.contains(categoria)){
            categorias.remove(categoria);
            return true;
        }else{
            return false;
        }
    }
    
   public static ArrayList<Plano> procurarPlanos(){
       PlanoDAO dao = new PlanoDAO();
       ArrayList<Plano> planos = (ArrayList<Plano>) dao.buscarTodosPlanos();
       return planos;
   }
    
}
    

