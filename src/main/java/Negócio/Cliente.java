/*
 Classe que define o cliente do sistema de TV por assinatura.
 */
package Negócio;

import Banco.ClienteDAO;
import java.util.ArrayList;

/**
 *
 * @author thiago
 */
public class Cliente {
    private String nome, email, cpf, telefone;
    private int idCliente;
    private ArrayList<Contrato> contratos;
    
    
    //Construtor sem idCliente
    public Cliente(String nome, String email, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.contratos = new ArrayList<>();
    }
    
    //Construtor com idCliente
    public Cliente(String nome, String email, String cpf, String telefone, int idCliente) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.idCliente = idCliente;
        this.contratos = new ArrayList<>();
    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    
    public Cliente() {
    }

    
    public int getIdCliente() {    
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<Contrato> getContratos() {
        return contratos;
    }

    /////////////////Getters e Setters////////////////////
    public void setContratos(ArrayList<Contrato> contratos) {
        this.contratos = contratos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    /////////////////////////////////
    
    @Override
    public String toString(){
        String stringRetorno;
        
        stringRetorno = "Cliente: \n Nome: " + this.nome + "\n Email: " + this.email + "\n CPF: " + this.cpf + "\n Telefone: " + this.telefone;
        
        for(int i=0; i < contratos.size();i++){
            stringRetorno = stringRetorno + contratos.get(i).toString() + "\n";
        }
        
        return stringRetorno; 
    }

    /**
     *
     * @return
     */
   public boolean incluir() {
        ClienteDAO cliente = new ClienteDAO();
        return cliente.incluirCliente(this); //To change body of generated methods, choose Tools | Templates.
    }
   
   public static ArrayList<Cliente> buscarClientes(){
       ClienteDAO dao = new ClienteDAO();
       ArrayList<Cliente> clientes = new ArrayList<>();
       return clientes = (ArrayList<Cliente>) dao.procurarTodosClientes();
   }
   
   public static boolean alteraCliente(Cliente cliente){
       ClienteDAO dao = new ClienteDAO();
       return dao.atualizarCliente(cliente);
   }
   
   public static boolean removeCliente(Cliente cliente){
       ClienteDAO dao = new ClienteDAO();
       return dao.excluirCliente(cliente);
   }
   
   public static ArrayList<Cliente> buscaCliente(String dado){
       ClienteDAO dao = new ClienteDAO();
       return (ArrayList<Cliente>) dao.procurarTodosClientes(dado);
   }
   
   public static Cliente buscaUmCliente(String cpf){
       ClienteDAO dao = new ClienteDAO();
       return dao.procurarCliente(cpf);
   }
    
}
