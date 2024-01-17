package interfaz;

import sockets.ClienteUDP;

public class MainClienteInterfaz extends javax.swing.JFrame {

    /**
     * Creates new form ClienteInterfaz
     */
    public MainClienteInterfaz() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_container = new javax.swing.JPanel();
        panel_mensaje = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensaje_to_send = new javax.swing.JTextArea();
        btn_send = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        direccion_ip = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        puerto_local = new javax.swing.JSpinner();
        puerto_destino = new javax.swing.JSpinner();
        panel_result = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        output_txt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        main_container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_mensaje.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Mensaje a enviar al sevidor:");
        panel_mensaje.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, -1));

        mensaje_to_send.setColumns(20);
        mensaje_to_send.setRows(5);
        jScrollPane1.setViewportView(mensaje_to_send);

        panel_mensaje.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 350, 90));

        main_container.add(panel_mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 350, 110));

        btn_send.setText("Enviar");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });
        main_container.add(btn_send, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Puerto destino");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 80, -1));

        jLabel3.setText("Puerto local");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, -1));

        direccion_ip.setText("127.0.0.1");
        direccion_ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccion_ipActionPerformed(evt);
            }
        });
        jPanel1.add(direccion_ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 200, -1));

        jLabel4.setText("Dirección IP");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 120, -1));

        puerto_local.setValue(34567);
        jPanel1.add(puerto_local, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 90, -1));

        puerto_destino.setValue(12345);
        jPanel1.add(puerto_destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 90, -1));

        main_container.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 350, 110));

        panel_result.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        output_txt.setColumns(20);
        output_txt.setEditable(false);
        output_txt.setVisible(false);
        output_txt.setRows(5);
        jScrollPane2.setViewportView(output_txt);

        panel_result.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, -1));

        main_container.add(panel_result, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 350, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_container, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_container, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Envia datos al servidor a través de un socket UDP
     * @param evt action del usuario
     */
    
    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        // Se obtienen los datos desde los campos de información de la interfaz
        String mensaje = mensaje_to_send.getText();
        String direccion = direccion_ip.getText();
        Integer p_destino = (Integer) puerto_destino.getValue();
        Integer p_local = (Integer) puerto_local.getValue();
        
        // con la información obtenida se llama a la función enviarMensajeServidor
        String result = ClienteUDP.enviarMensajeServidor(p_local, p_destino, direccion, mensaje);
        
        //Se establece un mensaje de salida
        output_txt.setText(result);
        output_txt.setVisible(true);
    }//GEN-LAST:event_btn_sendActionPerformed

    private void direccion_ipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccion_ipActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_direccion_ipActionPerformed

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
            java.util.logging.Logger.getLogger(MainClienteInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainClienteInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainClienteInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClienteInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainClienteInterfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_send;
    private javax.swing.JTextField direccion_ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel main_container;
    private javax.swing.JTextArea mensaje_to_send;
    private javax.swing.JTextArea output_txt;
    private javax.swing.JPanel panel_mensaje;
    private javax.swing.JPanel panel_result;
    private javax.swing.JSpinner puerto_destino;
    private javax.swing.JSpinner puerto_local;
    // End of variables declaration//GEN-END:variables

}
