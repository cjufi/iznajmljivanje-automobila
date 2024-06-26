/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.controller;

import domain.Korisnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import ui.coordinator.ClientCoordinator;
import ui.forms.LoginForm;
import ui.forms.util.Constants;

/**
 *
 * @author Filip
 */
public class LoginController {
    
    private final LoginForm frmLogin;

    public LoginController(LoginForm frmLogin) {
        this.frmLogin = frmLogin;
        addActionListener();
    }

    public void openForm() {
        frmLogin.setVisible(true);
        frmLogin.setTitle("Prijava");
        frmLogin.setLocationRelativeTo(null);
    }

    private void addActionListener() {
        frmLogin.btnLoginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser(e);
            }

            private void loginUser(ActionEvent e) {
                resetForm();
                try {
                    String username = frmLogin.getTxtKorisnickoIme().getText().trim();
                    String password = String.valueOf(frmLogin.getTxtLozinka().getPassword());

                    validateForm(username, password);

                    Korisnik korisnik = communication.Communication.getInstance().login(username, password);

                    JOptionPane.showMessageDialog(frmLogin, "Uspešno ste se prijavili, " + korisnik.getKorisnickoIme(), "Login", JOptionPane.INFORMATION_MESSAGE);
                    ClientCoordinator.getInstance().addParam(Constants.KORISNIK, korisnik);
                    if (username.equals(korisnik.getKorisnickoIme()) && password.equals(korisnik.getSifra())) {
                        ClientCoordinator.getInstance().openMainForm();
                    }
                    frmLogin.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();

                    if (ex.getMessage().startsWith("Korisnik")) {
                        JOptionPane.showMessageDialog(frmLogin, ex.getMessage(), "Login greška", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frmLogin, "Neuspešna prijava", "Login greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            private void resetForm() {
                frmLogin.getLblKorisnickoImeError().setText(" ");
                frmLogin.getLblLozinkaError().setText(" ");
            }
            
// stavi ovu validaciju na server!
            private void validateForm(String username, String password) throws Exception {
                String errorMessage = "";
                if (username.isEmpty()) {
                    frmLogin.getLblKorisnickoImeError().setText("Polje korisničko ime ne sme biti prazno!");
                    errorMessage += "Polje korisničko ime ne sme biti prazno!\n";
                }
                if (password.isEmpty()) {
                    frmLogin.getLblLozinkaError().setText("Polje lozinka ne sme biti prazno!");
                    errorMessage += "Polje lozinka ne sme biti prazno!\n";
                }
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
            }
        });
    }
}
