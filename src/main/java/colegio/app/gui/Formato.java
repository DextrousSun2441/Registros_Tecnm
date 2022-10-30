package colegio.app.gui;

import colegio.app.dbase.Conexion;
//import static formatos.Conexion.getConection;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//---------------------LIBRERIAS IMPORTADAS

/**
 * @author Andres Dominguez
 */
public class Formato extends javax.swing.JFrame {

    private PreparedStatement ps;
    private ResultSet rs;

    DefaultTableModel model;
    Connection conn;
    Statement sent;
    /*CONEXIONES*/

    public boolean mostrar = true;

    public Formato() {
        initComponents();
        setTitle("Registro de Formatos");//ingresa el titulo
        conn = Conexion.getConection();
        this.setLocationRelativeTo(null);//centra el panel
        //campos que hago invisibles para hacer comparaciones despues
        txtAnio1.setVisible(false);
        txtMes1.setVisible(false);
        txtDia1.setVisible(false);
        txtRecibe1.setVisible(false);
        txtCantidad1.setVisible(false);
        txtMaterial1.setVisible(false);
        
        //campos que llaman a los constructores
        txtDia.setText(FechaDia());
        txtMes.setText(FechaActualMes());
        txtAnio.setText(FechaActualanio());
        
        //desabilito los txt que se usan para validar y comparar los datos que no se repitan
        Desabilitar();
        LlenarTabla();
     

    }

    //CLASE LIMPIAR 
    void limpiar() {
        txtCantidad.setText(null);
        txtRecibe.setText(null);
        txtMaterial.setText(null);
    }

    //desabilita los botones -errores contra usuario
    void Desabilitar() {
        btnguardar.setEnabled(false);
        btnLimpiar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtRecibe.setEnabled(false);
        txtAnio.setEnabled(false);
        txtMes.setEnabled(false);
        txtDia.setEnabled(false);
        txtMaterial.setEnabled(false);
    }

    void Habilitar() {
        btnguardar.setEnabled(true);
        btnLimpiar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnModificar.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtRecibe.setEnabled(true);
        txtAnio.setEnabled(true);
        txtMes.setEnabled(true);
        txtDia.setEnabled(true);
        txtMaterial.setEnabled(true);

    }
//CONSTRUTOR QUE SE ENCARGA DE LLENAR LOS DATOS DE LA TABLA

    void LlenarTabla() {
        Connection con = null;
        try {
            String[] titulos = {"ID", "AÑO","MES","DIA", "RECIBE", "MATERIAL", "CANTIDAD"};
            String SQL = "SELECT * FROM `registros` ORDER BY `registros`.`Mes` ASC";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement();
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
            con.close();

        } catch (Exception e) {

            //JOptionPane.showMessageDialog(null, "error");
        }
    }/*fin de funcion llenartabla*/
//funcion que se encargar de buscar por el txtRecibe
    public void BuscarRecibe(String texto) {
        Connection con = null;
        try {
            String[] titulos = {"ID", "AÑO","MES","DIA", "RECIBE", "MATERIAL", "CANTIDAD"};
            String filtro = "" + texto + "%";
            String SQL = "SELECT * FROM REGISTROS WHERE RECIBE LIKE" + '"' + filtro + '"';
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
//funcion que se encargar de buscar por el txtBuscarMaterial

    public void BuscarMaterial(String texto) {
        Connection con = null;
        try {
            String[] titulos = {"ID", "AÑO","MES","DIA", "RECIBE", "MATERIAL", "CANTIDAD"};
            String filtro = "" + texto + "%";
            String SQL = "SELECT * FROM REGISTROS WHERE MATERIAL LIKE" + '"' + filtro + '"';
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
//funcion que se encargar de buscar por el BuscarCantidad

    public void BuscarCantidad(String texto) {
        Connection con = null;
        try {
            String[] titulos = {"ID", "AÑO","MES","DIA", "RECIBE", "MATERIAL", "CANTIDAD"};
            String filtro = "" + texto + "%";
            String SQL = "SELECT * FROM REGISTROS WHERE CANTIDAD LIKE" + '"' + filtro + '"';
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
//funcion que da la hora exacta

    public static String FechaDia() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd");
        return formatoFecha.format(fecha);
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
     
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        javax.swing.JButton btnNuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtAnio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRecibe = new javax.swing.JTextField();
        txtMaterial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtMaterial1 = new javax.swing.JTextField();
        txtAnio1 = new javax.swing.JTextField();
        txtCantidad1 = new javax.swing.JTextField();
        txtRecibe1 = new javax.swing.JTextField();
        txtMaterialBuscar = new javax.swing.JTextField();
        txtCantidadBuscar = new javax.swing.JTextField();
        cboxBuscar = new javax.swing.JComboBox<>();
        txtMes = new javax.swing.JTextField();
        txtDia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMes1 = new javax.swing.JTextField();
        txtDia1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuRegistro = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FORMATO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 14))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(102, 102, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo.setText("Nuevo");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 60, 90, -1));

        btnguardar.setText("Guardar");
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 90, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 90, -1));

        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 90, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 223, 90, -1));

        txtAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioActionPerformed(evt);
            }
        });
        jPanel1.add(txtAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 60, 31));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Cantidad");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, -1, 23));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Material");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, 23));
        jPanel1.add(txtRecibe, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 180, 30));
        jPanel1.add(txtMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 180, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Dia");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, 23));
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 180, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Buscar");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, -1, 23));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Recibe");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, 23));

        txtBuscar.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscar.setText("Recibe");
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 180, 30));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "FECHA", "RECIBE", "MATERIAL", "CANTIDAD"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setIntercellSpacing(new java.awt.Dimension(50, 0));
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 270, 940, 390));
        jPanel1.add(txtMaterial1, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 210, 40, -1));

        txtAnio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnio1ActionPerformed(evt);
            }
        });
        jPanel1.add(txtAnio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 50, -1));
        jPanel1.add(txtCantidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 40, -1));
        jPanel1.add(txtRecibe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 40, -1));

        txtMaterialBuscar.setForeground(new java.awt.Color(204, 204, 204));
        txtMaterialBuscar.setText("Material");
        txtMaterialBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaterialBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaterialBuscarFocusLost(evt);
            }
        });
        txtMaterialBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaterialBuscarActionPerformed(evt);
            }
        });
        txtMaterialBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaterialBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtMaterialBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 180, 30));

        txtCantidadBuscar.setForeground(new java.awt.Color(204, 204, 204));
        txtCantidadBuscar.setText("Cantidad");
        txtCantidadBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantidadBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadBuscarFocusLost(evt);
            }
        });
        txtCantidadBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtCantidadBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 180, 30));

        cboxBuscar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        cboxBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Recibe", "Material", "Cantidad" }));
        cboxBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(cboxBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 130, 100, 30));

        txtMes.setText(" ");
        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });
        jPanel1.add(txtMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 70, 30));

        txtDia.setText(" ");
        jPanel1.add(txtDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 40, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Año");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, 23));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Mes");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, 23));
        jPanel1.add(txtMes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 50, -1));
        jPanel1.add(txtDia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 50, -1));

        jMenu1.setText("Formatos");

        menuRegistro.setText("Registro");
        menuRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegistroActionPerformed(evt);
            }
        });
        jMenu1.add(menuRegistro);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioActionPerformed

    }//GEN-LAST:event_txtAnioActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
        Habilitar();
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        txtAnio.setText(FechaActualanio());
        txtMes.setText(FechaActualMes());
        txtDia.setText(FechaDia());

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtAnio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnio1ActionPerformed

    }//GEN-LAST:event_txtAnio1ActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        //Connection con = null;
        if ("".equals(txtAnio.getText()) || "".equals(txtMes.getText()) || "".equals(txtDia.getText()) ||"".equals(txtRecibe.getText()) || "".equals(txtMaterial.getText()) || "".equals(txtCantidad.getText())) {
            JOptionPane.showMessageDialog(null, "Llenar los campos vacíos", "mensaje", JOptionPane.WARNING_MESSAGE);
        } else {
            int Info = JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE GUARDAR ", "Confirmar Operacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (Info == JOptionPane.YES_OPTION) {
                try {
                   // conn = getConection();
                    ps = conn.prepareStatement("INSERT INTO REGISTROS (ANIO,MES,DIA,RECIBE,MATERIAL,CANTIDAD) VALUES (?,?,?,?,?,?)");
                    ps.setString(1, txtAnio.getText());
                    ps.setString(2, txtMes.getText());
                    ps.setString(3, txtDia.getText());
                    ps.setString(4, txtRecibe.getText());
                    ps.setString(5, txtMaterial.getText());
                    ps.setString(6, txtCantidad.getText());
                    int res = ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "Se Guardo El Registro Exitosamente ");
                        limpiar();
                        LlenarTabla();
                        Desabilitar();
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR al guadar el dato", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Desabilitar();
        limpiar();
        LlenarTabla();
        
        txtAnio.setText(FechaActualanio());
        txtMes.setText(FechaActualMes());
        txtDia.setText(FechaDia());


    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getButton() == 1) {
            int fila = jTable1.getSelectedRow();
            try {
                String SQL = " SELECT * FROM REGISTROS WHERE ID =" + jTable1.getValueAt(fila, 0);
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(SQL);
                System.out.println(SQL);
                rs.next();
                
                txtAnio.setText(rs.getString("ANIO"));
                txtMes.setText(rs.getString("MES"));
                txtDia.setText(rs.getString("DIA"));
                txtRecibe.setText(rs.getString("RECIBE"));
                txtMaterial.setText(rs.getString("MATERIAL"));
                txtCantidad.setText(rs.getString("CANTIDAD"));

                txtAnio1.setText(rs.getString("ANIO"));
                txtMes1.setText(rs.getString("MES"));
                txtDia1.setText(rs.getString("DIA"));
                txtRecibe1.setText(rs.getString("RECIBE"));
                txtMaterial1.setText(rs.getString("MATERIAL"));
                txtCantidad1.setText(rs.getString("CANTIDAD"));
                Habilitar();
                
                btnguardar.setEnabled(false);
                txtBuscar.setText(null);
                txtBuscar.setEnabled(false);
                
            } catch (Exception e) {

            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String nombre = txtBuscar.getText();
        BuscarRecibe(nombre);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        if (txtBuscar.getText().equals("Recibe")) {
            txtBuscar.setText("");
            txtBuscar.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        if (txtBuscar.getText().equals("")) {
            txtBuscar.setText("Recibe");
            txtBuscar.setForeground(new Color(0, 0, 0));

        }
    }//GEN-LAST:event_txtBuscarFocusLost

    private void txtMaterialBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaterialBuscarActionPerformed

    }//GEN-LAST:event_txtMaterialBuscarActionPerformed

    private void txtMaterialBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaterialBuscarKeyReleased
        String nombre = txtMaterialBuscar.getText();
        BuscarMaterial(nombre);
    }//GEN-LAST:event_txtMaterialBuscarKeyReleased

    private void txtMaterialBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaterialBuscarFocusGained
        if (txtMaterialBuscar.getText().equals("Material")) {
            txtMaterialBuscar.setText("");
            txtMaterialBuscar.setForeground(new Color(0, 0, 0));
        }

    }//GEN-LAST:event_txtMaterialBuscarFocusGained

    private void txtMaterialBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaterialBuscarFocusLost
        if (txtMaterialBuscar.getText().equals("")) {
            txtMaterialBuscar.setText("Material");
            txtMaterialBuscar.setForeground(new Color(0, 0, 0));

        }
    }//GEN-LAST:event_txtMaterialBuscarFocusLost

    private void cboxBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxBuscarActionPerformed
        String opcion = (String) cboxBuscar.getSelectedItem();
        if (opcion.equals("Recibe")) {
            txtBuscar.setEnabled(true);
            txtBuscar.setVisible(true);

            txtMaterialBuscar.setEnabled(false);
            txtMaterialBuscar.setVisible(false);

            txtCantidadBuscar.setEnabled(false);
            txtCantidadBuscar.setVisible(false);
        }
        if (opcion.equals("Material")) {

            txtBuscar.setEnabled(false);
            txtBuscar.setVisible(false);

            txtMaterialBuscar.setEnabled(true);
            txtMaterialBuscar.setVisible(true);
            txtCantidadBuscar.setEnabled(false);
            txtCantidadBuscar.setVisible(false);
        }
        if (opcion.equals("Cantidad")) {

            txtBuscar.setEnabled(false);
            txtBuscar.setVisible(false);

            txtMaterialBuscar.setEnabled(false);
            txtMaterialBuscar.setVisible(false);
            txtCantidadBuscar.setEnabled(true);
            txtCantidadBuscar.setVisible(true);
        }
    }//GEN-LAST:event_cboxBuscarActionPerformed

    private void txtCantidadBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadBuscarFocusGained
        if (txtCantidadBuscar.getText().equals("Cantidad")) {
            txtCantidadBuscar.setText("");
            txtCantidadBuscar.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txtCantidadBuscarFocusGained

    private void txtCantidadBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadBuscarFocusLost
        if (txtCantidadBuscar.getText().equals("")) {
            txtCantidadBuscar.setText("Cantidad");
            txtCantidadBuscar.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txtCantidadBuscarFocusLost

    private void txtCantidadBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadBuscarKeyReleased
        String nombre = txtCantidadBuscar.getText();
        BuscarCantidad(nombre);
    }//GEN-LAST:event_txtCantidadBuscarKeyReleased

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = jTable1.getSelectedRow();
        Connection con = null;
        if ("".equals(txtAnio.getText()) || "".equals(txtMes.getText()) || "".equals(txtDia.getText()) || "".equals(txtRecibe.getText()) || "".equals(txtMaterial.getText()) || "".equals(txtCantidad.getText())) {
            JOptionPane.showMessageDialog(null, "Llenar los campos vacíos", "mensaje", JOptionPane.WARNING_MESSAGE);
        } else {
            int Info = JOptionPane.showConfirmDialog(null, "ESTA SEGURO QUE DESEA ELIMINAR ", "confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (Info == JOptionPane.YES_OPTION) {

                try {
                    String SQL = "DELETE FROM REGISTROS WHERE ID= " + jTable1.getValueAt(fila, 0);
                    sent = conn.createStatement();
                    int n = sent.executeUpdate(SQL);
                    if (n > 0) {
                        LlenarTabla();
                        JOptionPane.showMessageDialog(null, "Datos Eliminados");
                        limpiar();
                        Desabilitar();
                        txtAnio.setText(FechaActualanio());
                        txtMes.setText(FechaActualMes());
                        txtDia.setText(FechaDia());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
                        LlenarTabla();
                        txtAnio.setText(FechaActualanio());
                        txtMes.setText(FechaActualMes());
                        txtDia.setText(FechaDia());
                        
                    }
                    con.close();
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Connection con = null;
        if ("".equals(txtAnio.getText()) || "".equals(txtMes.getText()) || "".equals(txtDia.getText()) ||  "".equals(txtRecibe.getText()) || "".equals(txtMaterial.getText()) || "".equals(txtCantidad.getText())) {
            JOptionPane.showMessageDialog(null, "Llenar los campos vacíos", "mensaje", JOptionPane.WARNING_MESSAGE);

        } else {
            try {

                int Info = JOptionPane.showConfirmDialog(null, "ESTA SEGURO QUE DESEA MODIFICAR ", "confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (Info == JOptionPane.YES_OPTION) {

                    if (txtAnio.getText().equals(txtAnio1.getText()) && txtMes.getText().equals(txtMes1.getText()) && txtDia.getText().equals(txtDia1.getText()) && txtRecibe.getText().equals(txtRecibe1.getText()) && txtMaterial.getText().equals(txtMaterial1.getText()) && txtCantidad.getText().equals(txtCantidad1.getText())) {
                        JOptionPane.showMessageDialog(null, "No a realizado modificaciones ", "MENSAJE", JOptionPane.WARNING_MESSAGE);
                    } else {

                        String SQL = "UPDATE REGISTROS SET ANIO=?,MES=?,DIA=?,RECIBE=?,MATERIAL=?,CANTIDAD=? WHERE ID=?";
                        int fila = jTable1.getSelectedRow();
                        String dato = (String) jTable1.getValueAt(fila, 0);
                        PreparedStatement ps = conn.prepareStatement(SQL);
                        ps.setString(1, txtAnio.getText());
                        ps.setString(2, txtMes.getText());
                        ps.setString(3, txtDia.getText());
                        ps.setString(4, txtRecibe.getText());
                        ps.setString(5, txtMaterial.getText());
                        

                        int Cantidad = Integer.parseInt(txtCantidad.getText());
                        ps.setInt(6, Cantidad);

                        ps.setString(7, dato);
                        int n = ps.executeUpdate();
                        if (n > 0) {

                            Desabilitar();
                            LlenarTabla();
                            limpiar();
                            txtDia.setText(FechaDia());
                            JOptionPane.showMessageDialog(null, "Datos Actualizados corectamente.");
                        }

                    }

                }
                con.close();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());

            }

        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void menuRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegistroActionPerformed
       Registros Registro = new Registros();
        Registro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuRegistroActionPerformed

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesActionPerformed

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
            java.util.logging.Logger.getLogger(Formato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JComboBox<String> cboxBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem menuRegistro;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtAnio1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidad1;
    private javax.swing.JTextField txtCantidadBuscar;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtDia1;
    private javax.swing.JTextField txtMaterial;
    private javax.swing.JTextField txtMaterial1;
    private javax.swing.JTextField txtMaterialBuscar;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtMes1;
    private javax.swing.JTextField txtRecibe;
    private javax.swing.JTextField txtRecibe1;
    // End of variables declaration//GEN-END:variables
}
