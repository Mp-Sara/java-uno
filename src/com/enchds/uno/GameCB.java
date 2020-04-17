/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enchds.uno;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class GameCB extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new form GameLS
     */
    public GameCB() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("The current card in play is a %.");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Current Scores:");

        jLabel3.setText("\"%\"");

        jLabel4.setText("What would you like to do?");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jScrollPane3.setViewportView(jTextArea1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Call uno", "Placeholder card name 1", "Placeholder card name 2", "Placeholder card name 3", "Placeholder card name 4", "Placeholder card name 5", "Placeholder card name 6", "Placeholder card name 7", "Draw from pile" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (UNO.os != null) {
            UNO.os.println(jComboBox1.getSelectedIndex());
            UNO.os.flush();
        } else UNO.out = Integer.toString(jComboBox1.getSelectedIndex());
    }//GEN-LAST:event_jButton1ActionPerformed
    
    class CBThread extends Thread {
        @Override
        public void run() {
            jLabel2.setText(UNO.lang.getOrDefault("Scores", "Scores"));
            while (isAlive()) {
                try {
                    if (UNO.is != null)
                        do UNO.line = UNO.is.readLine();
                        while (UNO.line == null);
                    else while (UNO.line == null) System.out.print("");
                    if (UNO.line.startsWith("MSG:DESC:"))
                        jLabel3.setText("\"" + UNO.line.replace("MSG:DESC:", "") + "\"");
                    else if (UNO.line.startsWith("MSG:NAME:")) UNO.names = UNO.line.replace("MSG:NAME:", "").split("-");
                    else if (UNO.line.startsWith("MSG:HAND")) {
                        UNO.lang.entrySet().forEach((Entry<String, String> entry) -> {
                            UNO.line = UNO.line.replace(entry.getKey(), entry.getValue());
                        });
                        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(UNO.line.replace("MSG:HAND:", "").split("-")));
                    } else if (UNO.line.startsWith("MSG:")) {
                        if (UNO.line.startsWith("MSG:NewPlayer")) {
                            UNO.line = UNO.lang.getOrDefault("NewPlayer", "NewPlayer")
                                    .replaceFirst("%", Integer.toString(
                                            Integer.parseInt(UNO.line.substring(UNO.line.length() - 4,
                                                    UNO.line.length() - 2)) + 1))
                                    .replaceFirst("%", Integer.toString(
                                            Integer.parseInt(UNO.line.substring(UNO.line.length() - 2))))
                                    .substring(0, UNO.line.length() - 4);
                            continue;
                        } else if (UNO.line.startsWith("MSG:NextTurn")) {
                            UNO.line = UNO.lang.getOrDefault("NextTurn", "NextTurn")
                                    .replaceFirst("%", UNO.names[Integer.parseInt(UNO.line.substring(UNO.line.length() - 2))])
                                    .substring(0, UNO.line.length() - 2);
                            continue;
                        }
                        UNO.lang.entrySet().forEach((Entry<String, String> entry) -> {
                            UNO.line = UNO.line.replace(entry.getKey(), entry.getValue());
                        });
                        if (UNO.line.contains("%")) {
                            try {
                                if (UNO.line.replaceFirst("%", "").contains("%")) {
                                    UNO.line = UNO.line.replace("%", Integer.toString(Integer.parseInt(
                                            UNO.line.substring(UNO.line.length() - 4, UNO.line.length() - 2))));
                                    UNO.line = UNO.line.substring(0, UNO.line.length() - 4)
                                            + UNO.line.substring(UNO.line.length() - 2);
                                }
                                UNO.line = UNO.line.replaceFirst("%",
                                        Integer.toString(Integer.parseInt(UNO.line.substring(UNO.line.length() - 2)) + 1));
                                UNO.line = UNO.line.substring(0, UNO.line.length() - 2);
                            } catch (NumberFormatException exc) {
                                System.err.println("Illegal % in language file! Keeping as is.");
                            }
                        }
                        jLabel4.setText(UNO.line.replace("MSG:", ""));
                        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>());
                    } else if (UNO.line.startsWith("CARD:")) {
                        UNO.lang.entrySet().forEach((Entry<String, String> entry) -> {
                            UNO.line = UNO.line.replace(entry.getKey(), entry.getValue());
                        });
                        jLabel1.setText(UNO.lang.getOrDefault("NewCard", "NewCard") + UNO.line.replace("CARD:", ""));
                    } else if (UNO.line.startsWith("SCORE:")) {
                        HashMap<String, Integer> scores = new HashMap<>(UNO.line.substring(6).split("-").length);
                        for (int i = 0; i < UNO.line.substring(6).split("-").length; i++)
                            scores.put(Integer.parseInt(UNO.line.substring(6).split("-")[i]) + ":" + i, i);
                        String scoreList = "";
                        for (int i = scores.size() - 1; i >= 0; i--) {
                            scoreList += UNO.lang.getOrDefault("ScoreEntry", "ScoreEntry")
                                .replaceFirst("%1", Integer.toString(scores.size() - i))
                                .replaceFirst("%2", UNO.names[scores.get(scores.keySet().toArray()[i].toString())])
                                .replaceFirst("%3", scores.keySet().toArray()[i].toString().split(":")[0]) + "\n";
                        }
                        jTextArea1.setText(scoreList);
                    } else {
                        UNO.lang.entrySet().forEach((Entry<String, String> entry) -> {
                            UNO.line = UNO.line.replace(entry.getKey(), entry.getValue());
                        });
                        jLabel4.setText(UNO.line.split("/")[0]);
                        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(UNO.line.split("/")[1].split("-")));
                    }
                    if (UNO.is == null) UNO.line = null;
                } catch (IOException exc) {
                    Logger.getLogger(GameCB.class.getName()).log(Level.SEVERE, null, exc);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
