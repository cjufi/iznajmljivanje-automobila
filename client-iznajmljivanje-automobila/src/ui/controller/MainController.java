/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.controller;

import domain.Korisnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.coordinator.ClientCoordinator;
import ui.forms.MainForm;
import ui.forms.util.Constants;

/**
 *
 * @author Filip
 */
public class MainController {
        private final MainForm frmMain;

    public MainController(MainForm frmMainForm) {
        this.frmMain = frmMainForm;
        addActionListeners();
    }

    public void openForm() {
        Korisnik korisnik = (Korisnik) ClientCoordinator.getInstance().getParam(Constants.KORISNIK);
        frmMain.getLblUlogovaniKorisnik().setText("Dobro došli, " + korisnik.getKorisnickoIme());
        frmMain.setTitle("Agencija za iznajmljivanje automobila");
        frmMain.setVisible(true);
        frmMain.setLocationRelativeTo(null);
    }

    private void addActionListeners() {
        frmMain.btnLogoutAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    communication.Communication.getInstance().logout();
                    Korisnik k = (Korisnik) ClientCoordinator.getInstance().getParam(Constants.KORISNIK);
                    JOptionPane.showMessageDialog(frmMain, "Uspešno ste se odjavili, " + k.getKorisnickoIme(), "Logout", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        frmMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    communication.Communication.getInstance().logout();
                    Korisnik k = (Korisnik) ClientCoordinator.getInstance().getParam(Constants.KORISNIK);
                    JOptionPane.showMessageDialog(frmMain, "Uspešno ste se odjavili, " + k.getKorisnickoIme(), "Logout", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        frmMain.jmiDodajKlijentaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientCoordinator.getInstance().openAddKlijentForm();
            }
        });
        
        frmMain.jmiPretraziKlijentaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientCoordinator.getInstance().openPretragaKlijentForm();
            }
        });
        
//        frmMain.jmiDodajAutomobilAddActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ClientCoordinator.getInstance().openAddAutomobilForm();
//            }
//        });
//        
//        frmMain.jmiPretraziAutomobilAddActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ClientCoordinator.getInstance().openPretragaAutomobilaForm();
//            }
//        });
    }
    
    public MainForm getFrmMain() {
        return frmMain;
    }
}
