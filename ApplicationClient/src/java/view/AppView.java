/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AppController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Simen
 */
public class AppView extends JFrame {
    private static final long serialVersionUID = 1L;
    private AppController controller;
    private JTextField kredietvakje;

    public JTextField getKredietvakje() {
        return kredietvakje;
    }
    
    public AppView(AppController c)
    {
        super("Boekhouding");
        this.controller = c;
        //paneel met opvraagstukje maken
        this.setLayout(new BorderLayout());
        this.kredietvakje = new JTextField();
        this.kredietvakje.setPreferredSize(new Dimension( 200, 24 ));
        JButton opvraagknop = new JButton("Vraag Op");
        opvraagknop.addActionListener(this.controller);
        this.add(kredietvakje, BorderLayout.WEST);
        this.add(opvraagknop, BorderLayout.EAST);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
