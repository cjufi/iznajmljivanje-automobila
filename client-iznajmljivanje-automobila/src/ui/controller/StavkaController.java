package ui.controller;

import communication.Communication;
import domain.Automobil;
import domain.Servis;
import domain.StavkaAutomobila;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import ui.forms.StavkaAutaForm;
import ui.forms.util.FormMode;

/**
 *
 * @author Filip
 */
public class StavkaController {
    
    private final StavkaAutaForm frmStavka;

    public StavkaController(StavkaAutaForm frmStavka) {
        this.frmStavka = frmStavka;
        addActionListeners();
    }

    private void addActionListeners() {
        frmStavka.btnDodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
            }

            private void create() {
                try {
                    if (frmStavka.getjTextField1().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frmStavka, "Sistem ne može da zapamti stavku.", "Stavku greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(frmStavka, "Nisu uneti svi podaci", "Stavka greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if (!validateForm()) {
                        return;
                    }

                      StavkaAutomobila sa = new StavkaAutomobila();
                      sa.setNaziv(frmStavka.getjTextField1().getText().trim());
                      sa.setAutomobil((Automobil)frmStavka.getjComboBox1().getSelectedItem());
                      sa.setServis((Servis)frmStavka.getjComboBox2().getSelectedItem());
                    Communication.getInstance().kreirajStavku(sa);

                    JOptionPane.showMessageDialog(frmStavka, "Sistem је zapamtio stavku automobila", "Čuvanje stavke", JOptionPane.INFORMATION_MESSAGE);
                    frmStavka.dispose();
                } catch (Exception e) {
                    Logger.getLogger(KlijentController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(frmStavka, "Sistem ne može da zapamti stavku automobila", "Stavka greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
        public void openForm(FormMode formMode) {

        frmStavka.setLocationRelativeTo(frmStavka.getParent());
        prepareForm();
        setMode(formMode);
        frmStavka.setTitle("Stavka Automobila");
        frmStavka.setVisible(true);
    }
        
    private void setMode(FormMode formMode) {
        switch (formMode) {
            case ADD:
                frmStavka.getBtnDodaj().setEnabled(true);
                frmStavka.getjComboBox1().setEnabled(true);
                frmStavka.getjComboBox2().setEnabled(true);
                frmStavka.getjTextField1().setEnabled(true);
                break;
            default:
                break;
        }
    }
    
    private void prepareForm() {
        try {
            populateCbAutomobili();
            populateCbServisi();
        } catch (Exception ex) {
            Logger.getLogger(AutomobilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populateCbAutomobili() throws Exception {
        frmStavka.getjComboBox1().removeAllItems();
        List<Automobil> automobili = Communication.getInstance().ucitajListuAutomobila();
        frmStavka.getjComboBox1().setModel(new DefaultComboBoxModel<>(automobili.toArray()));
    }
    
    private void populateCbServisi() throws Exception {
        frmStavka.getjComboBox2().removeAllItems();
        List<Servis> servisi = Communication.getInstance().ucitajListuServisa();
        frmStavka.getjComboBox2().setModel(new DefaultComboBoxModel<>(servisi.toArray()));
    }
    
    private boolean validateForm() {
        String naziv = frmStavka.getjTextField1().getText().trim();
        if(naziv.length() > 20) {
            JOptionPane.showMessageDialog(frmStavka, "Stavka mora imati manje od 20 karaktera", "Registracioni broj greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
