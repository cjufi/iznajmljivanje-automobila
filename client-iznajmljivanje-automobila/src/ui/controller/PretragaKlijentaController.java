/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.controller;

import domain.Klijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.coordinator.ClientCoordinator;
import ui.forms.PretragaKlijentaForm;
import ui.forms.components.tableModels.KlijentTableModel;
import ui.forms.util.Constants;

/**
 *
 * @author Filip
 */
public class PretragaKlijentaController {
    
    private final PretragaKlijentaForm frmPretragaKlijenta;
    private KlijentTableModel ktm;

    public PretragaKlijentaController(PretragaKlijentaForm frmPretragaKlijentaForm) {
        this.frmPretragaKlijenta = frmPretragaKlijentaForm;
        addActionListeners();
    }

    public void openForm() {
        prepareForm();
        frmPretragaKlijenta.setTitle("Klijenti");
        frmPretragaKlijenta.setLocationRelativeTo(ClientCoordinator.getInstance().getMainController().getFrmMain());
        frmPretragaKlijenta.setVisible(true);
    }
    
    private void addActionListeners() {
        frmPretragaKlijenta.btnPretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ime = frmPretragaKlijenta.getIme().getText().trim();
                    String prezime = frmPretragaKlijenta.getPrezime().getText().trim();
                    if (ime.isEmpty() && prezime.isEmpty()) {
                        JOptionPane.showMessageDialog(frmPretragaKlijenta, "Unesite barem jednu vrednost za pretragu", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Klijent klijentNadji = new Klijent();
                    if(!ime.isEmpty()) klijentNadji.setIme(ime);
                    if(!prezime.isEmpty()) klijentNadji.setPrezime(prezime);
                    List<Klijent> klijenti = communication.Communication.getInstance().nadjiKlijente(klijentNadji);
                    if (klijenti.isEmpty()) {
                        prepareForm();
                        JOptionPane.showMessageDialog(frmPretragaKlijenta, "Sistem ne može da nađe klijente po zadatoj vrednosti", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                        frmPretragaKlijenta.getIme().setText("");
                        frmPretragaKlijenta.getPrezime().setText("");
                        return;
                    }
                    JOptionPane.showMessageDialog(frmPretragaKlijenta, "Sistem je našao klijenta po zadatoj vrednosti");
                    System.out.println(klijenti);
                    ktm = (KlijentTableModel) frmPretragaKlijenta.getTblKlijenti().getModel();
                    ktm.setKlijenti(klijenti);
                    frmPretragaKlijenta.getIme().setText("");
                    frmPretragaKlijenta.getPrezime().setText("");
                } catch (Exception ex) {
                    Logger.getLogger(PretragaKlijentaForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPretragaKlijenta, "Sistem ne može da nađe klijente po zadatoj vrednosti", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        frmPretragaKlijenta.btnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPretragaKlijenta.getTblKlijenti().getSelectedRow();
                if (row >= 0) {
                    Klijent klijent = ((KlijentTableModel) frmPretragaKlijenta.getTblKlijenti().getModel()).vratiKlijenta(row);
                    ClientCoordinator.getInstance().addParam(Constants.KLIJENT, klijent);
                    ClientCoordinator.getInstance().addParam(Constants.KLIJENT_RED, row);
                    try {
                        ClientCoordinator.getInstance().openEditKlijentForm(frmPretragaKlijenta);
                    } catch (Exception ex) {
                        Logger.getLogger(PretragaKlijentaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frmPretragaKlijenta, "Sistem ne može da učita klijenta.", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frmPretragaKlijenta, "Potrebno je da izaberete klijenta iz tabele", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });        
    }
    
    private void prepareForm() {
        List<Klijent> klijenti = new ArrayList<>();
        ktm = new KlijentTableModel(klijenti);
        frmPretragaKlijenta.getTblKlijenti().setModel(ktm);
    }
}
