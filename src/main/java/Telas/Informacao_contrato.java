/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Negócio.Categoria;
import Negócio.Contrato;
import Negócio.Pagamento;
import Negócio.PlanoIlimitado;
import Negócio.PlanoRegular;
import Negócio.PlanoTop;
import Telas.PopUps.Acesso_verificado;
import Telas.PopUps.Boleto_sucesso;
import Telas.PopUps.Erro_linha_invalida;
import Telas.PopUps.Operacao_sucesso;
import Telas.PopUps.perguntaPagamentoFatura;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Alexf
 */
public class Informacao_contrato extends javax.swing.JFrame {

    /**
     * Creates new form Informacao_contrato
     */
    public Informacao_contrato(int contrato, String acesso, String endCobranca, int quantReceptores, String plano, int idPlano, int idCliente, String nome ) {
        initComponents();
        aparenciaTabela();
        setaCampos(contrato, acesso, endCobranca, quantReceptores, plano, idPlano, idCliente, nome);
        preencheTabela();
        preencheTabela2();
        verificaAcesso();
        lblIdCliente.setVisible(false);
        lblIdPlano.setVisible(false);
    }
    
    public void verificaAcesso(){
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
        int contrato = Integer.parseInt(lblContrato.getText());
        int idPlano = Integer.parseInt(lblIdPlano.getText());
        int idCliente = Integer.parseInt(lblIdCliente.getText());
        int count = 0;
        
        Pagamento pagamentoDados = new Pagamento(contrato, idPlano, idCliente);
        pagamentos = Pagamento.todasFaturas(pagamentoDados);
        
        for(Pagamento pagamento: pagamentos){
            if(pagamento.getPago()==0){
                count++;
            }
        }
        if(count>=3){
            lblAcesso.setText("Desabilitado");
        }else{
            lblAcesso.setText("Habilitado");
        }
    }
    
    public void setaCampos(int contrato, String acesso, String endCobranca, int quantReceptores, String plano, int idPlano, int idCliente, String nome){
        lblContrato.setText(Integer.toString(contrato));
        lblAcesso.setText(acesso);
        jTextAreaEndCobranca.setText(endCobranca);
        lblQuantReceptores.setText(Integer.toString(quantReceptores));
        lblPlano.setText(plano);
        lblIdCliente.setText(Integer.toString(idCliente));
        lblIdPlano.setText(Integer.toString(idPlano));
        lblNomeCliente.setText(nome);
    }
    
    
    public void preencheTabela2(){
        DefaultTableModel modelo = (DefaultTableModel) Tabela2.getModel();
        
        if(modelo.getRowCount() > 0){
             modelo.setNumRows(0);
        }
        int contrato = Integer.parseInt(lblContrato.getText());
        ArrayList<Categoria> categorias = Contrato.devolveCategoriasContrato(contrato);
        for(Categoria categoria: categorias){
            modelo.addRow(new Object[]{
                categoria.getNome()
            });
        }
        
    }
    
    public static void preencheTabela(){
        DefaultTableModel modelo = (DefaultTableModel) Tabela.getModel();
        
        if(modelo.getRowCount() > 0){
             modelo.setNumRows(0);
        }
        
        int contrato = Integer.parseInt(lblContrato.getText());
        int idPlano = Integer.parseInt(lblIdPlano.getText());
        int idCliente = Integer.parseInt(lblIdCliente.getText());
        Pagamento pagamentoDados = new Pagamento(contrato, idPlano, idCliente);
        
        ArrayList<Pagamento> pagamentos = Pagamento.todasFaturas(pagamentoDados);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        for(Pagamento pagamento: pagamentos){
            String faturaPaga;
            String dataFormatadaPagamento=null;
            String dataFormatada = pagamento.getData().format(formatador);
            
            if(pagamento.getDataPagamento()!=null)
                dataFormatadaPagamento = pagamento.getDataPagamento().format(formatador);
            
            if(pagamento.getPago()==1){
                faturaPaga = "Sim";
            }else{
                faturaPaga = "Não";
            }
           
            modelo.addRow(new Object[]{
                pagamento.getIdPagamento(),
                dataFormatada,
                pagamento.getValor(),
                faturaPaga,
                dataFormatadaPagamento
            });
        }
    }
    
    public static void preencheTabela(ArrayList<Pagamento> pagamentos){
        DefaultTableModel modelo = (DefaultTableModel) Tabela.getModel();
        
        if(modelo.getRowCount() > 0){
             modelo.setNumRows(0);
        }
        
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        for(Pagamento pagamento: pagamentos){
            String faturaPaga;
            String dataFormatadaPagamento=null;
            String dataFormatada = pagamento.getData().format(formatador);
            
            if(pagamento.getDataPagamento()!=null)
                dataFormatadaPagamento = pagamento.getDataPagamento().format(formatador);
            
            if(pagamento.getPago()==1){
                faturaPaga = "Sim";
            }else{
                faturaPaga = "Não";
            }
           
            modelo.addRow(new Object[]{
                pagamento.getIdPagamento(),
                dataFormatada,
                pagamento.getValor(),
                faturaPaga,
                dataFormatadaPagamento
            });
        }
    }

    private Informacao_contrato() {
        initComponents();
    }
    
    public void aparenciaTabela(){
        DefaultTableModel modelo = (DefaultTableModel) Tabela.getModel();
        Tabela.setRowSorter(new TableRowSorter(modelo));
        Tabela.setSelectionBackground(new Color(45,118,232));
        JTableHeader header = Tabela.getTableHeader();
        JScrollPane scroll= jScrollPane1;
        scroll.getViewport().setBackground(Color.WHITE);
//        
        DefaultTableModel modelo2 = (DefaultTableModel) Tabela2.getModel();
        Tabela2.setRowSorter(new TableRowSorter(modelo2));
        Tabela2.setSelectionBackground(new Color(45,118,232));
        JTableHeader header2 = Tabela2.getTableHeader();
        JScrollPane scroll2= jScrollPane4;
        scroll2.getViewport().setBackground(Color.WHITE);
    }
    

    
    
    
        
    public void setColor(JPanel panel){
        panel.setBackground(new Color(22, 43, 229));
    }
    
    public void resetColor(JPanel panel){
        panel.setBackground(new Color(45,118,232));
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        painel_header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblContrato = new javax.swing.JLabel();
        lblNomeCliente = new javax.swing.JLabel();
        lblContrato2 = new javax.swing.JLabel();
        lblQuantReceptores = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        btnVerificarAcesso = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnGerarBoleto = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnDarBaixa = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaEndCobranca = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblAcesso = new javax.swing.JLabel();
        btnRemover = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblPlano = new javax.swing.JLabel();
        lblIdCliente = new javax.swing.JLabel();
        lblIdPlano = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tabela2 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jDado = new javax.swing.JTextField();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        painel_header.setBackground(new java.awt.Color(45, 118, 232));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/contrato-pequeno.png"))); // NOI18N
        jLabel1.setText("Número do Contrato:");

        lblContrato.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblContrato.setForeground(new java.awt.Color(255, 255, 255));
        lblContrato.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblContrato.setText("nmr contrato");

        lblNomeCliente.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblNomeCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblNomeCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNomeCliente.setText("nomeCliente");

        lblContrato2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblContrato2.setForeground(new java.awt.Color(255, 255, 255));
        lblContrato2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblContrato2.setText("Cliente:");

        javax.swing.GroupLayout painel_headerLayout = new javax.swing.GroupLayout(painel_header);
        painel_header.setLayout(painel_headerLayout);
        painel_headerLayout.setHorizontalGroup(
            painel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel_headerLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContrato)
                .addGap(103, 103, 103)
                .addComponent(lblContrato2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeCliente)
                .addContainerGap(446, Short.MAX_VALUE))
        );
        painel_headerLayout.setVerticalGroup(
            painel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_headerLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(painel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrato2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(painel_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 100));

        lblQuantReceptores.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblQuantReceptores.setForeground(new java.awt.Color(153, 0, 204));
        lblQuantReceptores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblQuantReceptores.setText("Nmr");
        jPanel1.add(lblQuantReceptores, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 350, -1, 58));

        Tabela.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Data", "Valor", "Pago", "Data de Pagamento"
            }
        ));
        jScrollPane1.setViewportView(Tabela);
        if (Tabela.getColumnModel().getColumnCount() > 0) {
            Tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
            Tabela.getColumnModel().getColumn(2).setPreferredWidth(15);
            Tabela.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 550, 500));

        btnVerificarAcesso.setBackground(new java.awt.Color(45, 118, 232));
        btnVerificarAcesso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerificarAcessoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerificarAcessoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerificarAcessoMouseExited(evt);
            }
        });
        btnVerificarAcesso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/acesso-2.png"))); // NOI18N
        jLabel11.setText("Verificar Acesso");
        btnVerificarAcesso.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 50));

        jPanel1.add(btnVerificarAcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 250, 70));

        btnGerarBoleto.setBackground(new java.awt.Color(45, 118, 232));
        btnGerarBoleto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGerarBoletoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGerarBoletoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGerarBoletoMouseExited(evt);
            }
        });
        btnGerarBoleto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jLabel12.setText("Gerar Fatura");
        btnGerarBoleto.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 50));

        jPanel1.add(btnGerarBoleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 250, 70));

        btnDarBaixa.setBackground(new java.awt.Color(45, 118, 232));
        btnDarBaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDarBaixaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDarBaixaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDarBaixaMouseExited(evt);
            }
        });
        btnDarBaixa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pagar-fatura2.png"))); // NOI18N
        jLabel13.setText("Pagar Fatura");
        btnDarBaixa.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 50));

        jPanel1.add(btnDarBaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 250, 70));

        jTextAreaEndCobranca.setColumns(20);
        jTextAreaEndCobranca.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jTextAreaEndCobranca.setRows(5);
        jTextAreaEndCobranca.setBorder(new javax.swing.border.MatteBorder(null));
        jScrollPane2.setViewportView(jTextAreaEndCobranca);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 200, 380, 90));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(45, 118, 232));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Plano:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 100, -1, 58));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(45, 118, 232));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Quantidade de Receptores:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 350, 360, 58));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(45, 118, 232));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Categorias:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 400, -1, 58));

        lblAcesso.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblAcesso.setForeground(new java.awt.Color(153, 0, 204));
        lblAcesso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAcesso.setText("Acesso");
        jPanel1.add(lblAcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 300, -1, 58));

        btnRemover.setBackground(new java.awt.Color(45, 118, 232));
        btnRemover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRemoverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRemoverMouseExited(evt);
            }
        });
        btnRemover.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/remove.png"))); // NOI18N
        jLabel14.setText("Remover");
        btnRemover.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 50));

        jPanel1.add(btnRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 250, 70));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(45, 118, 232));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Endereço de Cobrança:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, -1, 58));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(45, 118, 232));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Pagamentos:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, 58));

        lblPlano.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lblPlano.setForeground(new java.awt.Color(153, 0, 204));
        lblPlano.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPlano.setText("plano");
        jPanel1.add(lblPlano, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, -1, 58));

        lblIdCliente.setText("idCliente");
        jPanel1.add(lblIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 650, 47, 23));

        lblIdPlano.setText("idPlano");
        jPanel1.add(lblIdPlano, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 650, 47, 23));

        Tabela2.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        Tabela2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome"
            }
        ));
        jScrollPane4.setViewportView(Tabela2);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 450, 380, 190));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(45, 118, 232));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Acesso:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, -1, 58));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setText("Número da Fatura:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        btnPesquisar.setBackground(new java.awt.Color(45, 118, 232));
        btnPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPesquisarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPesquisarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPesquisarMouseExited(evt);
            }
        });
        btnPesquisar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search.png"))); // NOI18N
        jLabel16.setText("Pesquisar");
        btnPesquisar.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 60));

        jPanel1.add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 239, 60));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 240, 20));

        jDado.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jDado.setBorder(null);
        jPanel1.add(jDado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 240, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1263, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerificarAcessoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerificarAcessoMouseClicked
        verificaAcesso();
        new Acesso_verificado().setVisible(true);
        
    }//GEN-LAST:event_btnVerificarAcessoMouseClicked

    private void btnVerificarAcessoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerificarAcessoMouseEntered
        setColor(btnVerificarAcesso);
    }//GEN-LAST:event_btnVerificarAcessoMouseEntered

    private void btnVerificarAcessoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerificarAcessoMouseExited
        resetColor(btnVerificarAcesso);
    }//GEN-LAST:event_btnVerificarAcessoMouseExited

    private void btnGerarBoletoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGerarBoletoMouseClicked
        int idCliente = Integer.parseInt(lblIdCliente.getText());
        int idPlano = Integer.parseInt(lblIdPlano.getText());
        int numeroContrato = Integer.parseInt(lblContrato.getText());
        Float valor=0f;
        
        if(idPlano==1){
            valor = PlanoIlimitado.calculaPlanoIlimitado(numeroContrato);
        }else if(idPlano==2){
            valor = PlanoTop.calculaPlanoTop(numeroContrato);
        }else if(idPlano==3){
            valor = PlanoRegular.calculaPlanoRegular(numeroContrato);
        }
        
        Pagamento pagamento = new Pagamento(valor, numeroContrato, idPlano, idCliente);
        if(Pagamento.geraFatura(pagamento)){
            new Boleto_sucesso().setVisible(true);
            preencheTabela();
        }else{
            JOptionPane.showMessageDialog(null, "Falha na criação da fatura!");
        }
    }//GEN-LAST:event_btnGerarBoletoMouseClicked

    private void btnGerarBoletoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGerarBoletoMouseEntered
        setColor(btnGerarBoleto);
    }//GEN-LAST:event_btnGerarBoletoMouseEntered

    private void btnGerarBoletoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGerarBoletoMouseExited
        resetColor(btnGerarBoleto);
    }//GEN-LAST:event_btnGerarBoletoMouseExited

    private void btnDarBaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDarBaixaMouseClicked
        if(Tabela.getSelectedRow()!=-1){
            new perguntaPagamentoFatura().setVisible(true);
        }else{
           new Erro_linha_invalida().setVisible(true);
        }
    }//GEN-LAST:event_btnDarBaixaMouseClicked

    private void btnDarBaixaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDarBaixaMouseEntered
        setColor(btnDarBaixa);
    }//GEN-LAST:event_btnDarBaixaMouseEntered

    private void btnDarBaixaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDarBaixaMouseExited
        resetColor(btnDarBaixa);
    }//GEN-LAST:event_btnDarBaixaMouseExited

    private void btnRemoverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseClicked
       if(Tabela.getSelectedRow()!=-1){
           int valor = (int) Tabela.getValueAt(Tabela.getSelectedRow(), 0);
           if(Pagamento.removeFatura(valor)){
               DefaultTableModel modelo = (DefaultTableModel) Tabela.getModel();
               new Operacao_sucesso().setVisible(true);
               modelo.removeRow(Tabela.getSelectedRow());
           }else{
               JOptionPane.showMessageDialog(null, "Erro ao excluir a fatura!");
           }
       }else{
           new Erro_linha_invalida().setVisible(true);
       }
      
    }//GEN-LAST:event_btnRemoverMouseClicked

    private void btnRemoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseEntered
        setColor(btnRemover);
    }//GEN-LAST:event_btnRemoverMouseEntered

    private void btnRemoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverMouseExited
        resetColor(btnRemover);
    }//GEN-LAST:event_btnRemoverMouseExited

    private void btnPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisarMouseClicked

        if(jDado.getText().length() != 0){
            String dado = jDado.getText();
            int idCliente = Integer.parseInt(lblIdCliente.getText());
            int idPlano = Integer.parseInt(lblIdPlano.getText());
            int numeroContrato = Integer.parseInt(lblContrato.getText());
            Pagamento p = new Pagamento(numeroContrato, idPlano, idCliente);
            p.setIdPagamento(Integer.parseInt(dado));
            ArrayList<Pagamento> pagamentos = Pagamento.buscaFaturaNumero(p);
            preencheTabela(pagamentos);
            jDado.setText("");
        }else{
            preencheTabela();
        };

    }//GEN-LAST:event_btnPesquisarMouseClicked

    private void btnPesquisarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisarMouseEntered
        this.setColor(btnPesquisar);
    }//GEN-LAST:event_btnPesquisarMouseEntered

    private void btnPesquisarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisarMouseExited
        this.resetColor(btnPesquisar);
    }//GEN-LAST:event_btnPesquisarMouseExited

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
            java.util.logging.Logger.getLogger(Informacao_contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacao_contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacao_contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacao_contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informacao_contrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable Tabela;
    private javax.swing.JTable Tabela2;
    private javax.swing.JPanel btnDarBaixa;
    private javax.swing.JPanel btnGerarBoleto;
    private javax.swing.JPanel btnPesquisar;
    private javax.swing.JPanel btnRemover;
    private javax.swing.JPanel btnVerificarAcesso;
    private javax.swing.JTextField jDado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaEndCobranca;
    private javax.swing.JLabel lblAcesso;
    private static javax.swing.JLabel lblContrato;
    private javax.swing.JLabel lblContrato2;
    private static javax.swing.JLabel lblIdCliente;
    private static javax.swing.JLabel lblIdPlano;
    private javax.swing.JLabel lblNomeCliente;
    private javax.swing.JLabel lblPlano;
    private javax.swing.JLabel lblQuantReceptores;
    private javax.swing.JPanel painel_header;
    // End of variables declaration//GEN-END:variables
}
