/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.controller;

import communication.Communication;
import domain.Klijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.coordinator.ClientCoordinator;
import ui.forms.KlijentForm;
import ui.forms.PretragaKlijentaForm;
import ui.forms.components.tableModels.KlijentTableModel;
import ui.forms.util.Constants;
import ui.forms.util.FormMode;

/**
 *
 * @author Filip
 */
public class KlijentController {
    
    private final KlijentForm frmKlijent;

    public KlijentController(KlijentForm frmKlijent) {
        this.frmKlijent = frmKlijent;
        addActionListeners();
    }

    private void addActionListeners() {
        frmKlijent.btnSacuvajAddActtionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
            }

            private void create() {
                try {
                    if (frmKlijent.getTxtIme().getText().isEmpty() || frmKlijent.getTxtEmail().getText().isEmpty()
                            || frmKlijent.getTxtPrezime().getText().isEmpty() || frmKlijent.getTxtAdresa().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frmKlijent, "Sistem ne može da zapamti klijenta.", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(frmKlijent, "Nisu uneti svi podaci", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
//                    validacija treba na serveru
//                    if (!validateForm()) {
//                        return;
//                    }
                    Klijent klijent = new Klijent();
                    klijent.setIme(frmKlijent.getTxtIme().getText().trim());
                    klijent.setEmail(frmKlijent.getTxtEmail().getText().trim());
                    klijent.setPrezime(frmKlijent.getTxtPrezime().getText().trim());
                    klijent.setAdresa(frmKlijent.getTxtAdresa().getText().trim());

                    Communication.getInstance().kreirajKlijenta(klijent);

                    JOptionPane.showMessageDialog(frmKlijent, "Sistem је zapamtio klijenta", "Čuvanje klijenta", JOptionPane.INFORMATION_MESSAGE);
                    frmKlijent.dispose();
                } catch (Exception e) {
                    Logger.getLogger(KlijentController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(frmKlijent, "Sistem ne može da zapamti klijenta", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        frmKlijent.btnOmoguciIzmenuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmKlijent.getBtnSacuvaj().setEnabled(false);
                frmKlijent.getBtnOmoguciIzmenu().setEnabled(false);
                frmKlijent.getBtnObrisi().setEnabled(false);
                frmKlijent.getBtnIzmeni().setEnabled(true);
                frmKlijent.getTxtIme().setEnabled(true);
                frmKlijent.getTxtEmail().setEnabled(true);
                frmKlijent.getTxtPrezime().setEnabled(true);
                frmKlijent.getTxtAdresa().setEnabled(true);
            }
        });

        frmKlijent.btnObrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                try {
                    Communication.getInstance().obrisiKlijenta((Klijent) ClientCoordinator.getInstance().getParam(Constants.KLIJENT));
                    JOptionPane.showMessageDialog(frmKlijent, "Sistem je obrisao klijenta", "Brisanje klijenta", JOptionPane.INFORMATION_MESSAGE);
                    frmKlijent.dispose();
                    PretragaKlijentaForm frmPretragaKlijenta = (PretragaKlijentaForm) frmKlijent.getParent();
                    KlijentTableModel ktm = (KlijentTableModel) frmPretragaKlijenta.getTblKlijenti().getModel();
                    ktm.obrisiKlijenta((Klijent) ClientCoordinator.getInstance().getParam(Constants.KLIJENT));
                } catch (Exception ex) {
                    Logger.getLogger(KlijentController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmKlijent, "Sistem ne može da obriše klijenta", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmKlijent.btnIzmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }

            private void update() {
                try {
//                    if (!validateForm()) {
//                        return;
//                    }
                    Klijent klijent = new Klijent();
                    klijent.setKlijentId(((Klijent) ClientCoordinator.getInstance().getParam(Constants.KLIJENT)).getKlijentId());
                    klijent.setIme(frmKlijent.getTxtIme().getText().trim());
                    klijent.setEmail(frmKlijent.getTxtEmail().getText().trim());
                    klijent.setPrezime(frmKlijent.getTxtPrezime().getText().trim());
                    klijent.setAdresa(frmKlijent.getTxtAdresa().getText().trim());

                    if (klijent.getIme().isEmpty() || klijent.getEmail().isEmpty() || klijent.getPrezime().isEmpty() || klijent.getAdresa().isEmpty()) {
                        JOptionPane.showMessageDialog(frmKlijent, "Sistem ne može da zapamti klijenta.", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(frmKlijent, "Nisu uneti svi podaci", "Klijent greška", JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    Communication.getInstance().izmeniKlijenta(klijent);
                    PretragaKlijentaForm frmPretragaKlijenta = (PretragaKlijentaForm) frmKlijent.getParent();
                    KlijentTableModel ktm = (KlijentTableModel) frmPretragaKlijenta.getTblKlijenti().getModel();
                    ktm.izmeniKlijenta(klijent, (int) ClientCoordinator.getInstance().getParam(Constants.KLIJENT_RED));
                    JOptionPane.showMessageDialog(frmKlijent, "Sistem је zapamtio klijenta", "Čuvanje klijenta", JOptionPane.INFORMATION_MESSAGE);
                    frmKlijent.dispose();
                } catch (Exception e) {
                    Logger.getLogger(KlijentController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(frmKlijent, "Sistem ne može da zapamti klijenta", "Klijent greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
        public void openForm(FormMode formMode) {
        if (formMode.equals(FormMode.EDIT)) {
            frmKlijent.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    try {
                        Klijent ucitanKlijent = Communication.getInstance().nadjiKlijenta((Klijent) ClientCoordinator.getInstance().getParam(Constants.KLIJENT));
                        if(ucitanKlijent==null){
                            throw new Exception();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frmKlijent, "Sistem ne može da učita klijenta");
                        frmKlijent.dispose();
                    }
                    JOptionPane.showMessageDialog(frmKlijent, "Sistem je učitao klijenta");
                }

            });
        }

        frmKlijent.setLocationRelativeTo(frmKlijent.getParent());
        setMode(formMode);
        frmKlijent.setTitle("Klijent");
        frmKlijent.setVisible(true);
    }
        
    private void setMode(FormMode formMode) {
        switch (formMode) {
            case ADD:
                frmKlijent.getBtnSacuvaj().setEnabled(true);
                frmKlijent.getBtnOmoguciIzmenu().setEnabled(false);
                frmKlijent.getBtnObrisi().setEnabled(false);
                frmKlijent.getBtnIzmeni().setEnabled(false);
                frmKlijent.getTxtIme().setEnabled(true);
                frmKlijent.getTxtEmail().setEnabled(true);
                frmKlijent.getTxtPrezime().setEnabled(true);
                frmKlijent.getTxtAdresa().setEnabled(true);
                break;
            case EDIT:
                frmKlijent.getBtnSacuvaj().setEnabled(false);
                frmKlijent.getBtnOmoguciIzmenu().setEnabled(true);
                frmKlijent.getBtnObrisi().setEnabled(true);
                frmKlijent.getBtnIzmeni().setEnabled(false);
                frmKlijent.getTxtIme().setEnabled(false);
                frmKlijent.getTxtEmail().setEnabled(false);
                frmKlijent.getTxtPrezime().setEnabled(false);
                frmKlijent.getTxtAdresa().setEnabled(false);

                Klijent izabraniKlijent = (Klijent) ClientCoordinator.getInstance().getParam(Constants.KLIJENT);
                frmKlijent.getTxtIme().setText(izabraniKlijent.getIme());
                frmKlijent.getTxtEmail().setText(izabraniKlijent.getEmail());
                frmKlijent.getTxtPrezime().setText(izabraniKlijent.getPrezime());
                frmKlijent.getTxtAdresa().setText(izabraniKlijent.getAdresa());
                break;
            default:
                break;
        }
    }
}
