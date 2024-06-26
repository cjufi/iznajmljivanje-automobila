/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.controller;

import forms.KonfiguracijaBazeForm;
import forms.coordinator.ServerCoordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import server.Settings;

/**
 *
 * @author Filip
 */
public class KonfiguracijaBazeController {

    private final KonfiguracijaBazeForm formKB;

    public KonfiguracijaBazeController(KonfiguracijaBazeForm formKB) {
        this.formKB = formKB;
        addActionListener();
    }

    public void openForm() {
        formKB.setLocationRelativeTo(ServerCoordinator.getInstance().getMainController().getFrmMain());
        formKB.setTitle("Podešavanja");
        formKB.setVisible(true);
    }

    private void addActionListener() {
        formKB.btnPoveziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = formKB.getTxtUrl().getText().trim();
                String username = formKB.getTxtUsername().getText().trim();
                String password = String.valueOf(formKB.getTxtPassword().getPassword()).trim();

                if (url.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(formKB, "Unesite URL i username.");
                    return;
                }
                
                if(!(url.contains("jdbc:mysql://localhost:"))){
                    JOptionPane.showMessageDialog(formKB, "URL nije lepo unet.");
                    return;
                }
                
                Settings.getInstance().setURL(url);
                Settings.getInstance().setUsername(username);
                Settings.getInstance().setPassword(password);
                Settings.getInstance().sacuvajPodesavanja();
                
                formKB.dispose();
            }

        });
    }
    
}
