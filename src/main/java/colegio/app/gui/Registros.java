/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package colegio.app.gui;
import colegio.app.dbase.Conexion;
//import com.itextpdf.text.pdf.codec.Base64.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import java.lang.String;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

//---------------------LIBRERIAS IMPORTADAS


/**
 *
 * @author Andres Dominguez
 */
public class Registros extends javax.swing.JFrame {

    private PreparedStatement ps;
    private ResultSet rs;

    DefaultTableModel model;
    Connection conn;
    Statement sent;
    /*CONEXIONES*/
       
        //crear la conexion
       
    public boolean mostrar = true;
    public Registros() {
        initComponents();
        setTitle("Registro de Formatos");//ingresa el titulo
        conn = Conexion.getConection();
        this.setLocationRelativeTo(null);//centra el panel
        //txtmes.setText(FechaActualMes());
        //txtanio.setText(FechaActualanio());
        //jcboxMeses.setSelectedItem((FechaActualMes()));
        LlenarTabla();
        Desabilitar();
    }
    void Desabilitar() {
       txtbuscarAño.setEnabled(false);
       txtMes.setEnabled(false);
    }
    void Habilitar(){
   txtbuscarAño.setEnabled(true);
   txtMes.setEnabled(true);
  


}
         public static String FechaActualMes() {
        Date mes = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MMMM");
        return formatoFecha.format(mes);
        
    }
     
          public static String FechaActualanio() {
        Date anio = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy");
        return formatoFecha.format(anio);
        
    }
     public void BuscarReporte(String anio, String mes) {
        Connection con = null;
        try {
           String[] titulos = {"ID", "AÑO","MES","DIA", "RECIBE", "MATERIAL", "CANTIDAD"};
            
           String filtroaño = "" + anio + "";
           String filtromes = "" + mes + "";
           
           String SQL = "SELECT * FROM registros WHERE anio = " + '"' + filtroaño + '"' + " AND mes = "+ '"'  + filtromes + '"' +  "ORDER BY registros.dia ASC";
           
            System.out.println(SQL);
            model = new DefaultTableModel(null, titulos);
            sent = conn.prepareStatement(SQL);
            ResultSet rs = sent.executeQuery(SQL);
            String[] fila = new String[7];
            while (rs.next()) {
                fila[0] = rs.getString("ID");
                fila[1] = rs.getString("ANIO");
                fila[2] = rs.getString("MES");
                fila[3] = rs.getString("DIA");
                fila[4] = rs.getString("RECIBE");
                fila[5] = rs.getString("MATERIAL");
                fila[6] = rs.getString("CANTIDAD");
               
                model.addRow(fila);
            }
            jTable1.setModel(model);
            rs.close();
            sent.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
     public void BuscarAÑO(String anio) {
        Connection con = null;
        try {
           String[] titulos = {"ID", "AÑO","MES"};
            
           String filtroaño = "" + anio + "";
           
           
            //String SQL = "SELECT * FROM registros WHERE anio = " + '"' + filtroaño + '"' + " AND mes = "+ '"'  + filtromes + '"' +  "ORDER BY registros.dia ASC";
            String SQL = "SELECT ID,Anio,Mes FROM registros WHERE anio = " + '"' + filtroaño + '"' +" GROUP BY Mes,Anio ORDER BY `registros`.`Anio` ASC;";
            System.out.println(SQL);
            model = new DefaultTableModel(null, titulos);
            sent = conn.prepareStatement(SQL);
            ResultSet rs = sent.executeQuery(SQL);
            String[] fila = new String[3];
            while (rs.next()) {
                fila[0] = rs.getString("ID");
                fila[1] = rs.getString("ANIO");
                fila[2] = rs.getString("MES");
                
               
                model.addRow(fila);
            }
            TablaRegistrosExistentes.setModel(model);
            rs.close();
            sent.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
     public void BuscarMES(String mes) {
        Connection con = null;
        try {
           String[] titulos = {"ID", "AÑO","MES"};
            
           String filtromes = "" + mes + "";
           
           
            //String SQL = "SELECT * FROM registros WHERE anio = " + '"' + filtroaño + '"' + " AND mes = "+ '"'  + filtromes + '"' +  "ORDER BY registros.dia ASC";
            String SQL = "SELECT ID,Anio,Mes FROM registros WHERE mes = " + '"' + filtromes + '"' +" GROUP BY Mes,Anio ORDER BY `registros`.`Anio` ASC;";
            System.out.println(SQL);
            model = new DefaultTableModel(null, titulos);
            sent = conn.prepareStatement(SQL);
            ResultSet rs = sent.executeQuery(SQL);
            String[] fila = new String[3];
            while (rs.next()) {
                fila[0] = rs.getString("ID");
                fila[1] = rs.getString("ANIO");
                fila[2] = rs.getString("MES");
                
               
                model.addRow(fila);
            }
            TablaRegistrosExistentes.setModel(model);
            rs.close();
            sent.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }
     void LlenarTabla() {
        Connection con = null;
        try {
            String[] titulos = {"ID","AÑO", "MES"};
            String SQL = "SELECT ID,Anio,Mes FROM registros GROUP BY Mes,Anio ORDER BY `registros`.`Anio` ASC";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(SQL);
            String[] fila = new String[3];
            while (rs.next()) {
                fila[0] = rs.getString("ID");
                fila[1] = rs.getString("ANIO");
                fila[2] = rs.getString("MES");
           
                model.addRow(fila);
            }
            TablaRegistrosExistentes.setModel(model);
            con.close();

        } catch (Exception e) {

            //JOptionPane.showMessageDialog(null, "error");
        }
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaRegistrosExistentes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jcboxAnio = new javax.swing.JComboBox<>();
        jcboxMeses = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnReporteGenerar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtbuscarAño = new javax.swing.JTextField();
        jcboxBuscador = new javax.swing.JComboBox<>();
        txtMes = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuAltaFormato = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(102, 102, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1250, 660));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 660));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Año", "Mes", "Dia"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setIntercellSpacing(new java.awt.Dimension(30, 0));
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 830, 650));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TablaRegistrosExistentes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TablaRegistrosExistentes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TablaRegistrosExistentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaRegistrosExistentes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablaRegistrosExistentes.setIntercellSpacing(new java.awt.Dimension(50, 0));
        TablaRegistrosExistentes.setMinimumSize(new java.awt.Dimension(45, 120));
        TablaRegistrosExistentes.setRowHeight(45);
        TablaRegistrosExistentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaRegistrosExistentesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaRegistrosExistentes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 420, 490));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Reporte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jcboxAnio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023" }));
        jcboxAnio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcboxAnioKeyReleased(evt);
            }
        });

        jcboxMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre" }));
        jcboxMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboxMesesActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("AÑO");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("MES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jcboxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(34, 34, 34))
                    .addComponent(jcboxMeses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcboxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcboxMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addGap(7, 7, 7))
        );

        jPanel5.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 230, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Generar Reporte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        btnReporteGenerar.setText("REPORTE");
        btnReporteGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnReporteGenerar)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnReporteGenerar)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 230, 100));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtbuscarAño.setText("AÑO");
        txtbuscarAño.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        txtbuscarAño.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtbuscarAñoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtbuscarAñoFocusLost(evt);
            }
        });
        txtbuscarAño.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarAñoKeyReleased(evt);
            }
        });
        jPanel7.add(txtbuscarAño, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 70, -1));

        jcboxBuscador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccion", "Año", "Mes" }));
        jcboxBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboxBuscadorActionPerformed(evt);
            }
        });
        jPanel7.add(jcboxBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 163, 90, -1));

        txtMes.setText("MES");
        txtMes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMesFocusLost(evt);
            }
        });
        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });
        txtMes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMesKeyReleased(evt);
            }
        });
        jPanel7.add(txtMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 71, -1));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 230));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 230));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("VISUALIZACION DE REPORTE");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 220, 30));

        jMenu1.setText("Formatos");

        menuAltaFormato.setText("Altas");
        menuAltaFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAltaFormatoActionPerformed(evt);
            }
        });
        jMenu1.add(menuAltaFormato);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuAltaFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAltaFormatoActionPerformed
        Formato formato = new Formato();
        formato.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuAltaFormatoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //String anio = txtanio.getText();
        //  System.out.println("el anio es: " + anio);

        String mes = (jcboxMeses.getSelectedItem().toString());
        String anio = (jcboxAnio.getSelectedItem().toString());
        System.out.println("selecciono " + mes);
        System.out.println("selecciono " + anio);

        BuscarReporte(anio, mes);
            
            
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jcboxMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboxMesesActionPerformed

    }//GEN-LAST:event_jcboxMesesActionPerformed

    private void TablaRegistrosExistentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaRegistrosExistentesMouseClicked
        if (evt.getButton() == 1) {
            int fila = TablaRegistrosExistentes.getSelectedRow();
            System.out.println(fila);
            try {
                String SQL = " SELECT Anio,Mes FROM registros WHERE ID =" + TablaRegistrosExistentes.getValueAt(fila, 0);
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(SQL);
                System.out.println(SQL);
                rs.next();
               
                jcboxAnio.setSelectedItem(rs.getString("Anio"));
                jcboxMeses.setSelectedItem(rs.getString("Mes"));
                //txtanio.setText(rs.getString("anio"));
                //txtmes.setText(rs.getString("mes"));
               

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }

        }
    }//GEN-LAST:event_TablaRegistrosExistentesMouseClicked

    private void jcboxAnioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcboxAnioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboxAnioKeyReleased

    private void txtbuscarAñoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbuscarAñoFocusGained
        if (txtbuscarAño.getText().equals("AÑO")) {
            txtbuscarAño.setText("");
            txtbuscarAño.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txtbuscarAñoFocusGained

    private void txtbuscarAñoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbuscarAñoFocusLost
        if (txtbuscarAño.getText().equals("")) {
            txtbuscarAño.setText("AÑO");
            txtbuscarAño.setForeground(new Color(0, 0, 0));

        }
    }//GEN-LAST:event_txtbuscarAñoFocusLost

    private void jcboxBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboxBuscadorActionPerformed
       String opcion = (String) jcboxBuscador.getSelectedItem();
        if (opcion.equals("Año")) {
            txtbuscarAño.setEnabled(true);
            txtbuscarAño.setVisible(true);

           txtMes.setEnabled(false);
           txtMes.setVisible(false);
           LlenarTabla();

            
        }
        if (opcion.equals("Mes")) {

           txtbuscarAño.setEnabled(false);
            txtbuscarAño.setVisible(false);

           txtMes.setEnabled(true);
           txtMes.setVisible(true);
           LlenarTabla();
            
        }
        if (opcion.equals("Seleccion")) {
           /*txtbuscarAño.setEnabled(false);
           txtMes.setEnabled(false);*/
           Desabilitar();
           LlenarTabla();
            
        }
    }//GEN-LAST:event_jcboxBuscadorActionPerformed

    private void txtMesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMesFocusGained
        if (txtMes.getText().equals("MES")) {
            txtMes.setText("");
            txtMes.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txtMesFocusGained

    private void txtMesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMesFocusLost
        if (txtMes.getText().equals("")) {
            txtMes.setText("MES");
            txtMes.setForeground(new Color(0, 0, 0));

        }
    }//GEN-LAST:event_txtMesFocusLost

    private void txtbuscarAñoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarAñoKeyReleased
        String buscaranio = txtbuscarAño.getText();
        BuscarAÑO(buscaranio);
    }//GEN-LAST:event_txtbuscarAñoKeyReleased

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        
    }//GEN-LAST:event_txtMesActionPerformed

    private void txtMesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesKeyReleased
        String buscarmes = txtMes.getText();
        BuscarMES(buscarmes);
    }//GEN-LAST:event_txtMesKeyReleased

    private void btnReporteGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteGenerarActionPerformed
        //C:\Users\Andres Dominguez\Documents\NetBeansProjects\Formatos\src\formatos\registros.jasper
        //C:\Users\Andres Dominguez\Documents\NetBeansProjects\Formatos\src\formatos\registros.jrxml
        //C:\Users\Andres Dominguez\Documents\NetBeansProjects\Servicio_Registro\src\main\java\colegio\app\report\REPORTE1.jasper
      
        
          try {
           
           
//           JasperReport reporte = null;
//          //String Path = "..\\src\\formatos\\reporte1.jasper";
//           String Path = "src\\main\\java\\colegio\\app\\report\\REPORTE1.jasper";
//           reporte = (JasperReport) JRLoader.loadObjectFromFile(Path);
//           Map parametros = new HashMap(); 
//           parametros.put("ANIO", jcboxAnio.getSelectedItem().toString());
//           parametros.put("MES", jcboxMeses.getSelectedItem().toString());
//           JasperPrint jprint = JasperFillManager.fillReport(reporte,parametros,conn);
//           JasperViewer view = new JasperViewer(jprint,false);
//           view.setTitle("Registros");
//           view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//           
//           view.setVisible(true);
           
           
         
           InputStream  reporteProducto = new  FileInputStream(new File("C:\\Reporte\\REPORTE1.jasper"));
        
           Map<String, Object> parametros = new HashMap();
               
           parametros.put("ANIO", jcboxAnio.getSelectedItem().toString());
           parametros.put("MES", jcboxMeses.getSelectedItem().toString());
           JasperPrint jprint = JasperFillManager.fillReport(reporteProducto,parametros,conn);
           JasperViewer view = new JasperViewer(jprint,false);
           
           view.setTitle("Registros");
           view.setVisible(true);
                        
                     
          
        } catch (Exception ex) {
            //Logger.getLogger(Registros.class.getName()).log(Level.WARNING,null,ex);
            System.out.println(""+ex);
            JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
      
        
        
        
    }//GEN-LAST:event_btnReporteGenerarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaRegistrosExistentes;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnReporteGenerar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcboxAnio;
    private javax.swing.JComboBox<String> jcboxBuscador;
    private javax.swing.JComboBox<String> jcboxMeses;
    private javax.swing.JMenuItem menuAltaFormato;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtbuscarAño;
    // End of variables declaration//GEN-END:variables
}
