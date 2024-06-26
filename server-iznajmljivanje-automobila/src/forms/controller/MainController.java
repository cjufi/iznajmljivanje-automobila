/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.controller;

import controller.Controller;
import forms.MainForm;
import forms.coordinator.ServerCoordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Filip
 */
public class MainController {
    
    private final MainForm frmMain;

    public MainController(MainForm frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }

    public MainForm getFrmMain() {
        return frmMain;
    }

    public void openForm() {
        frmMain.setVisible(true);
        frmMain.setLocationRelativeTo(null);
        frmMain.getBtnZaustaviServer().setEnabled(false);
    }

    private void addActionListener() {
        frmMain.btnPokreniServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = new File("config/dbconfig.properties");
                if (!f.exists()) {
                    ServerCoordinator.getInstance().openSettingsForm();
                }
                if (f.exists()) {
                    Controller.getInstance().pokreniServer();
                    frmMain.getBtnPokreniServer().setEnabled(false);
                    frmMain.getBtnZaustaviServer().setEnabled(true);
                }
            }
        });

        frmMain.btnZaustaviServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().zaustaviServer();
                frmMain.getBtnPokreniServer().setEnabled(true);
                frmMain.getBtnZaustaviServer().setEnabled(false);
            }
        });

        frmMain.jmiKonfiguracijaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerCoordinator.getInstance().openSettingsForm();
            }
        });
    }
}
