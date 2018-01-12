/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negócio;
import java.util.ArrayList;
import java.util.Iterator;
/**
  @author Pedro
 */

public class Categoria {
    protected String nome;
    protected ArrayList<Canal> canais;
    protected int id;

    public Categoria(String nome) {
        this.nome = nome;
        this.canais = new ArrayList<>();
    }

    public Categoria(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    
    
    public Categoria() {

    }
    
    
    
    public ArrayList<Canal> getCanais() {
        return canais;
    }

    public void setCanais(ArrayList<Canal> canais) {
        this.canais = canais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void addCanal(Canal canal){
        this.canais.add(canal);
    }
    
    public void removeCanal(Canal canal){
        if (!canais.remove(canal))
            System.out.println("Este canal não faz parte desta categoria");        
    }

    public float valorCategoria(){
        float valor=0f;
        Canal buffer;
        for(Iterator it = canais.iterator(); it.hasNext();){
            buffer = (Canal)it.next();
            valor+=buffer.precoBase;
        }
        return valor;
    }
    
    public void exibirCanais(){
        Canal buffer;
        for (Iterator it = canais.iterator(); it.hasNext();){
            buffer = (Canal)it.next();
            System.out.println(buffer.toString());
        }
    }
}
