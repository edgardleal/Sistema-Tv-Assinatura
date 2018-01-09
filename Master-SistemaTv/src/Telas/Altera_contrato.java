/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Banco.CanalDAO;
import Banco.CategoriaDAO;
import Negócio.Canal;
import Negócio.Categoria;
import Negócio.CategoriaContrato;
import Negócio.Contrato;
import Negócio.Plano;
import Negócio.PlanoIlimitado;
import Negócio.PlanoRegular;
import Negócio.PlanoTop;
import Telas.PopUps.Aviso_preco_contrato;
import Telas.PopUps.Campos_preenchidos;
import Telas.PopUps.Operacao_sucesso;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Alexf
 */
public class Altera_contrato extends javax.swing.JFrame {
    String endCobranca;
    int idPlano;
    ArrayList<Categoria> categorias;
    int quantReceptores;
    int idContrato;
    
    /**
     * Creates new form Janela_modelo
     */
    public Altera_contrato(String id, String endCobranca,int idPlano, ArrayList<Categoria> categorias, int quantReceptores, int idContrato) {
        initComponents();
        
        this.endCobranca = endCobranca;
        this.idPlano = idPlano;
        this.categorias = categorias;
        this.quantReceptores = quantReceptores;
        this.idContrato = idContrato;
        jComboBoxPlanos.setSelectedIndex(0);
        lblId.setVisible(false); 
        lblId.setText(id);
        setaPlanos();
        camposPlano();
        bloqueiaComponentes();
        categoriasDoPlano();
     
        for(Categoria categoria: categorias){
            System.out.println("idCategoria: " + categoria.getId());
        }
    }
    
    public void camposPlano(){
        tEndCobranca.setText(endCobranca);
        jComboBoxPlanos.setSelectedIndex(idPlano);
        jSpinnerReceptores.setValue(quantReceptores);
    }
    
    public void categoriasDoPlano(){
        for(Categoria categoria: this.categorias){
            if(categoria.getId()==1){
                botao1.setSelected(true);
            }
            if(categoria.getId()==2){
                botao2.setSelected(true);
            }
            if(categoria.getId()==3){
                botao3.setSelected(true);
            }
            if(categoria.getId()==4){
                botao4.setSelected(true);
            }
            
        }
    }

    private Altera_contrato() {
        initComponents();
        jComboBoxPlanos.setSelectedIndex(0);
        setaPlanos();
    }
    
    public void bloqueiaComponentes(){
        if(jComboBoxPlanos.getSelectedIndex() == 2 || jComboBoxPlanos.getSelectedIndex() == 3 || jComboBoxPlanos.getSelectedIndex() == 1){
            botao1.setEnabled(true);
            botao2.setEnabled(true);
            botao3.setEnabled(true);
            botao4.setEnabled(true);
            
        }else{
            botao1.setEnabled(false);
            botao2.setEnabled(false);
            botao3.setEnabled(false);
            botao4.setEnabled(false);
        }
    }
    
        public void bloqueiaComponentes(boolean habilitado){
        if(((jComboBoxPlanos.getSelectedIndex() == 2) || (jComboBoxPlanos.getSelectedIndex() == 3))&& habilitado == true){
            botao1.setEnabled(true);
            botao2.setEnabled(true);
            botao3.setEnabled(true);
            botao4.setEnabled(true);
            
        }else{
            if(!botao1.isSelected())
                botao1.setEnabled(false);
            if(!botao2.isSelected())
                botao2.setEnabled(false);
            if(!botao3.isSelected())
                botao3.setEnabled(false);
            if(!botao4.isSelected())
                botao4.setEnabled(false);
        }
    }
    
    
    public void setaPlanos(){
        ArrayList<Plano> planos = Plano.procurarPlanos();
        for(Plano plano: planos){
            jComboBoxPlanos.addItem(plano.getNome());
        }
    }
    
    public boolean verificaCampos(){
        boolean habilitado = true;
        int contadorBotao = 0;
       int  quantReceptores = (int) jSpinnerReceptores.getValue();
        if( quantReceptores == 0){
            habilitado = false;
            setColorRed(lblQuantReceptores);
        }else{
            setColorBlack(lblQuantReceptores);
        }
        if(tEndCobranca.getText().length() == 0){
            habilitado = false;
            setColorRed(lblEndCobranca);
        }else{
            setColorBlack(lblEndCobranca);
        }
        if(jComboBoxPlanos.getSelectedIndex() == 0){
            habilitado = false;   
            setColorRed(lblPlano);
            setColorRed(lblCategorias);
        }else{
            setColorBlack(lblPlano);
            setColorBlack(lblCategorias);
        }
        if(jComboBoxPlanos.getSelectedIndex()==2){
            if(botao1.isSelected())
                contadorBotao++;
            if(botao2.isSelected())
                contadorBotao++;
            if(botao3.isSelected())
                contadorBotao++;
            if(botao4.isSelected())
                contadorBotao++;
            if(contadorBotao!=3){
                habilitado = false;   
                setColorRed(lblCategorias);  
            }else{
                setColorBlack(lblCategorias);  
            }
                
        } else if(jComboBoxPlanos.getSelectedIndex()==3){
            if(botao1.isSelected())
                contadorBotao++;
            if(botao2.isSelected())
                contadorBotao++;
            if(botao3.isSelected())
                contadorBotao++;
            if(botao4.isSelected())
                contadorBotao++;
            if(contadorBotao!=2){
                habilitado = false;   
                setColorRed(lblCategorias);  
            }else{
                setColorBlack(lblCategorias);
            }
                
        }
        return habilitado;
    }
    
    public void setColorRed(JLabel label){
        label.setForeground(Color.red);
    }
    public void setColorBlack(JLabel label){
        label.setForeground(Color.black);
    }
    
    public boolean contaCategorias(Contrato contrato){
        boolean habilitado = true;
        ArrayList<Integer> numeros = new ArrayList<>();
        if(botao1.isSelected()){
            numeros.add(1);
        }
        if(botao2.isSelected()){
            numeros.add(2);
        }
        if(botao3.isSelected()){
            numeros.add(3);
        }
        if(botao4.isSelected()){
            numeros.add(4);
        }
        if(numeros.size()==2){
            return habilitado = CategoriaContrato.adicionaRegular(contrato, numeros);
        }else {
            return habilitado = CategoriaContrato.adicionaTop(contrato, numeros);
        }
        
    }
    
    public ArrayList<Integer> contaCategorias(){
        ArrayList<Integer> numeros = new ArrayList<>();
        if(botao1.isSelected()){
            numeros.add(1);
        }
        if(botao2.isSelected()){
            numeros.add(2);
        }
        if(botao3.isSelected()){
            numeros.add(3);
        }
        if(botao4.isSelected()){
            numeros.add(4);
        }
       return numeros;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        PainelPrincipal = new javax.swing.JPanel();
        painel_header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        tEndCobranca = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblQuantReceptores = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblEndCobranca = new javax.swing.JLabel();
        jComboBoxPlanos = new javax.swing.JComboBox<>();
        jSpinnerReceptores = new javax.swing.JSpinner();
        lblCategorias = new javax.swing.JLabel();
        botao1 = new javax.swing.JRadioButton();
        botao2 = new javax.swing.JRadioButton();
        botao3 = new javax.swing.JRadioButton();
        botao4 = new javax.swing.JRadioButton();
        lblPlano = new javax.swing.JLabel();
        btnVerificaPreco = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnVerificaCanais = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PainelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        PainelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        painel_header.setBackground(new java.awt.Color(45, 118, 232));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo-contrato.png"))); // NOI18N
        jLabel1.setText("Alterar Contrato");

        lblId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblId.setForeground(new java.awt.Color(255, 255, 255));
        lblId.setText("Recebe-Id");

        javax.swing.GroupLayout painel_headerLayout = new javax.swing.GroupLayout(painel_header);
        painel_header.setLayout(painel_headerLayout);
        painel_headerLayout.setHorizontalGroup(
            painel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_headerLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 521, Short.MAX_VALUE)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );
        painel_headerLayout.setVerticalGroup(
            painel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_headerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(painel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        PainelPrincipal.add(painel_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 100));

        tEndCobranca.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tEndCobranca.setBorder(null);
        tEndCobranca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tEndCobrancaActionPerformed(evt);
            }
        });
        PainelPrincipal.add(tEndCobranca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 920, 30));
        PainelPrincipal.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 920, 20));

        lblQuantReceptores.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblQuantReceptores.setText("Quantidade de Receptores:");
        PainelPrincipal.add(lblQuantReceptores, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, -1, -1));

        jPanel1.setBackground(new java.awt.Color(45, 118, 232));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/confirma-contrato.png"))); // NOI18N
        jLabel2.setText("Alterar");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, 58));

        PainelPrincipal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, 320, 80));

        lblEndCobranca.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblEndCobranca.setText("Endereço de Cobrança:");
        PainelPrincipal.add(lblEndCobranca, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jComboBoxPlanos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jComboBoxPlanos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxPlanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPlanosActionPerformed(evt);
            }
        });
        PainelPrincipal.add(jComboBoxPlanos, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 150, 50));

        jSpinnerReceptores.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        PainelPrincipal.add(jSpinnerReceptores, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 530, 120, 60));

        lblCategorias.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCategorias.setText("Selecione as categorias:");
        PainelPrincipal.add(lblCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, -1, -1));

        botao1.setBackground(new java.awt.Color(255, 255, 255));
        botao1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        botao1.setText("Infantil");
        botao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao1ActionPerformed(evt);
            }
        });
        PainelPrincipal.add(botao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, -1, -1));

        botao2.setBackground(new java.awt.Color(255, 255, 255));
        botao2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        botao2.setText("Filme");
        botao2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao2ActionPerformed(evt);
            }
        });
        PainelPrincipal.add(botao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, -1, -1));

        botao3.setBackground(new java.awt.Color(255, 255, 255));
        botao3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        botao3.setText("Notícia");
        botao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao3ActionPerformed(evt);
            }
        });
        PainelPrincipal.add(botao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, -1, -1));

        botao4.setBackground(new java.awt.Color(255, 255, 255));
        botao4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        botao4.setText("Esporte");
        botao4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao4ActionPerformed(evt);
            }
        });
        PainelPrincipal.add(botao4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, -1, 20));

        lblPlano.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblPlano.setText("Plano:");
        PainelPrincipal.add(lblPlano, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));

        btnVerificaPreco.setBackground(new java.awt.Color(45, 118, 232));
        btnVerificaPreco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerificaPrecoMouseClicked(evt);
            }
        });
        btnVerificaPreco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Verificar Preço");
        btnVerificaPreco.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        PainelPrincipal.add(btnVerificaPreco, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 170, 50));

        btnVerificaCanais.setBackground(new java.awt.Color(45, 118, 232));
        btnVerificaCanais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerificaCanaisMouseClicked(evt);
            }
        });
        btnVerificaCanais.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Verificar Canais");
        btnVerificaCanais.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        PainelPrincipal.add(btnVerificaCanais, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, 170, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        if(verificaCampos()){
           if(Contrato.removeContrato(idContrato)){
                String endCobranca = tEndCobranca.getText();
                int idPlano = jComboBoxPlanos.getSelectedIndex();
                int quantReceptores = (int) jSpinnerReceptores.getValue();
                int idCliente = Integer.parseInt(lblId.getText());


                Contrato contrato = new Contrato(idPlano, idCliente, quantReceptores, endCobranca);
                if(Contrato.adicionaContrato(contrato)){
                    contrato = Contrato.devolveContrato(contrato);
                    boolean habilitado = true;
                    if(idPlano == 1){
                        habilitado = CategoriaContrato.adicionaIlimitado(contrato);
                    } else {
                        habilitado = contaCategorias(contrato);
                    }
                    if(habilitado){
                        Perfil_clientes.preencheTabela();
                        new Operacao_sucesso().setVisible(true);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Falha ao realizar a operação!");
                        dispose();
                    }
                }
            }    
        }else{
            new Campos_preenchidos().setVisible(true);
        };
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        jPanel1.setBackground(new Color(22, 43, 229));
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
        jPanel1.setBackground(new Color(45,118,232));
    }//GEN-LAST:event_jPanel1MouseExited

    private void botao2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao2ActionPerformed
        planoIlimitado();
        administraBotoes();
    }//GEN-LAST:event_botao2ActionPerformed

    private void jComboBoxPlanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPlanosActionPerformed
       bloqueiaComponentes();
       verificaBotaoSelecionado();
       if(jComboBoxPlanos.getSelectedIndex()==1){
            botao1.setSelected(true);
            botao2.setSelected(true);
            botao3.setSelected(true);
            botao4.setSelected(true);
       }
       
        
    }//GEN-LAST:event_jComboBoxPlanosActionPerformed

    private void botao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao1ActionPerformed
        planoIlimitado();
        administraBotoes();
    }//GEN-LAST:event_botao1ActionPerformed

    private void botao4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao4ActionPerformed
        planoIlimitado();
        administraBotoes();
    }//GEN-LAST:event_botao4ActionPerformed

    private void botao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao3ActionPerformed
        planoIlimitado();
        administraBotoes();
    }//GEN-LAST:event_botao3ActionPerformed

    private void tEndCobrancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tEndCobrancaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tEndCobrancaActionPerformed

    private void btnVerificaPrecoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerificaPrecoMouseClicked
        float valor;
        if(jComboBoxPlanos.getSelectedIndex()==1){
            valor = PlanoIlimitado.calculaPlanoIlimitado();
            new Aviso_preco_contrato(valor).setVisible(true);
        }else{
            ArrayList<Integer> categorias = contaCategorias();
            if(jComboBoxPlanos.getSelectedIndex()==2){
                valor = PlanoTop.calculaPlanoTop(categorias);
                new Aviso_preco_contrato(valor).setVisible(true);
            }else{
                valor = PlanoRegular.calculaPlanoRegular(categorias);
                new Aviso_preco_contrato(valor).setVisible(true);
            }
        }
    }//GEN-LAST:event_btnVerificaPrecoMouseClicked

    private void btnVerificaCanaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerificaCanaisMouseClicked
        ArrayList<Integer> numeros = contaCategorias();
        ArrayList<Categoria> categorias = new ArrayList<>();
        for(int i: numeros){
            Categoria c = new Categoria();
            c.setId(i);
            categorias.add(c);
        }
        ArrayList<Canal> canais = new ArrayList<>();
        CanalDAO dao = new CanalDAO();
        for(Categoria categoria: categorias){
            ArrayList<Canal> provisorio = (ArrayList<Canal>) dao.buscarTodosCanaisComCategoria(categoria.getId());
            canais.addAll(provisorio);
        }
        new Tela_Canais_Contrato(canais).setVisible(true);
    }//GEN-LAST:event_btnVerificaCanaisMouseClicked
    
   public void planoIlimitado(){
       if(jComboBoxPlanos.getSelectedIndex()==1){
            if(!botao1.isSelected())
                botao1.setSelected(true);
             if(!botao2.isSelected())
                botao2.setSelected(true);
             if(!botao3.isSelected())
                botao3.setSelected(true);
             if(!botao4.isSelected())
                botao4.setSelected(true);
      }
   }    
  

    
    public void verificaBotaoSelecionado(){
        botao1.setSelected(false);
        botao2.setSelected(false);
        botao3.setSelected(false);
        botao4.setSelected(false);
    }
    
    
    public void administraBotoes(){
        int contador = 0;
        boolean habilitado = true;
        if(jComboBoxPlanos.getSelectedIndex()==3){
            if(botao1.isSelected()){
                contador++;
                if(contador==2){
                    habilitado = false;
                    bloqueiaComponentes(habilitado);
                }else{
                    habilitado = true;
                    bloqueiaComponentes(habilitado);
                }
            }
            if(botao2.isSelected()){
                contador++;
                if(contador==2){
                    habilitado = false;
                    bloqueiaComponentes(habilitado);
                }else{
                    habilitado = true;
                    bloqueiaComponentes(habilitado);
                }
            }
            if(botao3.isSelected()){
                contador++;
                if(contador==2){
                    habilitado = false;
                    bloqueiaComponentes(habilitado);
                }else{
                    habilitado = true;
                    bloqueiaComponentes(habilitado);
                }
            }
            if(botao4.isSelected()){
                contador++;
                if(contador==2){
                    habilitado = false;
                    bloqueiaComponentes(habilitado);
                }else{
                    habilitado = true;
                    bloqueiaComponentes(habilitado);
                }
            }
            
        } else if(jComboBoxPlanos.getSelectedIndex()==2){
            if(botao1.isSelected()){
                contador++;
                if(contador==3){
                    habilitado = false;
                    bloqueiaComponentes(habilitado);
                }else{
                    habilitado = true;
                    bloqueiaComponentes(habilitado);
                }
            }
            if(botao2.isSelected()){
                contador++;
                if(contador==3){
                    habilitado = false;
                    bloqueiaComponentes(habilitado);
                }else{
                    habilitado = true;
                    bloqueiaComponentes(habilitado);
                }
            }
            if(botao3.isSelected()){
                contador++;
                if(contador==3){
                    habilitado = false;
                    bloqueiaComponentes(habilitado);
                }else{
                    habilitado = true;
                    bloqueiaComponentes(habilitado);
                }
            }
            if(botao4.isSelected()){
                contador++;
                if(contador==3){
                    habilitado = false;
                    bloqueiaComponentes(habilitado);
                }else{
                    habilitado = true;
                    bloqueiaComponentes(habilitado);
                }
            }
            
        }
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Altera_contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Altera_contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Altera_contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Altera_contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Altera_contrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelPrincipal;
    private javax.swing.JRadioButton botao1;
    private javax.swing.JRadioButton botao2;
    private javax.swing.JRadioButton botao3;
    private javax.swing.JRadioButton botao4;
    private javax.swing.JPanel btnVerificaCanais;
    private javax.swing.JPanel btnVerificaPreco;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBoxPlanos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinnerReceptores;
    private javax.swing.JLabel lblCategorias;
    private javax.swing.JLabel lblEndCobranca;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblPlano;
    private javax.swing.JLabel lblQuantReceptores;
    private javax.swing.JPanel painel_header;
    private javax.swing.JTextField tEndCobranca;
    // End of variables declaration//GEN-END:variables
}
