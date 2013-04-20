package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Hagg
 */
public class ChatClient extends javax.swing.JFrame {

    private BufferedReader reader;
    private PrintWriter writer;

    public ChatClient() {
        initComponents();
    }

    private void neueVerbindung(String addresse, int port, String name) {
        try {
            Socket socket = new Socket(addresse, port);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            writer.append(name + "\n").flush();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s;
                    while ((s = reader.readLine()) != null) {
                        txa_text.append(s + "\n");
                    }
                    //sSystem.out.println("3");
                } catch (IOException ex) {
                    Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

        txf_senden.setEnabled(true);
        txa_text.setEnabled(true);
        btn_senden.setEnabled(true);

        btn_verbinden.setEnabled(false);
        txf_name.setEnabled(false);
        txf_server.setEnabled(false);
        txf_port.setEnabled(false);
        lbl_server.setEnabled(false);
        lbl_port.setEnabled(false);
        lbl_name.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txa_text = new javax.swing.JTextArea();
        btn_senden = new javax.swing.JButton();
        txf_senden = new javax.swing.JTextField();
        lbl_server = new javax.swing.JLabel();
        txf_server = new javax.swing.JTextField();
        lbl_port = new javax.swing.JLabel();
        txf_port = new javax.swing.JTextField();
        btn_verbinden = new javax.swing.JButton();
        lbl_name = new javax.swing.JLabel();
        txf_name = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txa_text.setEditable(false);
        txa_text.setColumns(20);
        txa_text.setRows(5);
        txa_text.setEnabled(false);
        jScrollPane1.setViewportView(txa_text);

        btn_senden.setText("senden");
        btn_senden.setEnabled(false);
        btn_senden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendenActionPerformed(evt);
            }
        });

        txf_senden.setEnabled(false);
        txf_senden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txf_sendenKeyReleased(evt);
            }
        });

        lbl_server.setText("Server:");

        txf_server.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txf_server.setText("127.0.0.1");

        lbl_port.setText("Port:");

        txf_port.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txf_port.setText("1234");

        btn_verbinden.setText("verbinden");
        btn_verbinden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verbindenActionPerformed(evt);
            }
        });

        lbl_name.setText("Name:");

        txf_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txf_name.setText("Daniel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txf_senden)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_senden))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_port, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_name, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txf_port)
                            .addComponent(txf_name)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_verbinden))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbl_server, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txf_server)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_server)
                    .addComponent(txf_server, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_port)
                    .addComponent(txf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_name)
                    .addComponent(txf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verbinden)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_senden)
                    .addComponent(txf_senden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sendenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendenActionPerformed
        writer.append(txf_senden.getText() + "\n").flush();
        txf_senden.setText("");
    }//GEN-LAST:event_btn_sendenActionPerformed

    private void btn_verbindenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verbindenActionPerformed
        neueVerbindung(txf_server.getText(), Integer.parseInt(txf_port.getText()), txf_name.getText());
    }//GEN-LAST:event_btn_verbindenActionPerformed

    private void txf_sendenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txf_sendenKeyReleased
        if (txf_senden.getText().equals("")) {
            btn_senden.setEnabled(false);
        } else {
            btn_senden.setEnabled(true);
        }
    }//GEN-LAST:event_txf_sendenKeyReleased

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
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatClient().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_senden;
    private javax.swing.JButton btn_verbinden;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JLabel lbl_port;
    private javax.swing.JLabel lbl_server;
    private javax.swing.JTextArea txa_text;
    private javax.swing.JTextField txf_name;
    private javax.swing.JTextField txf_port;
    private javax.swing.JTextField txf_senden;
    private javax.swing.JTextField txf_server;
    // End of variables declaration//GEN-END:variables
}
