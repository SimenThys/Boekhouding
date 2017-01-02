/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AppView;
import beans.SessionBeanRemoteInterface;
import java.io.IOException;
import javax.ejb.EJB;

/**
 *
 * @author student
 */
public class AppController implements ActionListener{

    /**
     * @param args the command line arguments
     */
    @EJB
    private static SessionBeanRemoteInterface remotebean;
    
    
    
    private AppView view;
    
    public AppController() throws IOException {
	this.view = new AppView(this);
    }

    public static void main(String[] args) throws IOException {
        AppController controller = new AppController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int knr = Integer.parseInt(view.getKredietvakje().getText());
        String s = remotebean.OpvragenKrediet(knr);
        System.out.println(s);
    }
}
