package ui.controller;

import communication.Communication;
import domain.Automobil;
import domain.Klijent;
import domain.Uverenje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.coordinator.ClientCoordinator;
import ui.forms.PretragaUverenjaForm;
import ui.forms.components.tableModels.UverenjeTableModel;
import ui.forms.util.Constants;

/**
 *
 * @author Filip
 */
public class PretragaUverenjaController {
    
    private final PretragaUverenjaForm frmPretragaUverenja;
    private UverenjeTableModel utm;

    public PretragaUverenjaController(PretragaUverenjaForm frmPretragaUverenja) {
        this.frmPretragaUverenja = frmPretragaUverenja;
        addActionListeners();
    }
    
    public void openForm() {
        prepareForm();
        frmPretragaUverenja.setTitle("Uverenja");
        frmPretragaUverenja.setLocationRelativeTo(ClientCoordinator.getInstance().getMainController().getFrmMain());
        frmPretragaUverenja.setVisible(true);
    }
    
    private void addActionListeners() {
        frmPretragaUverenja.btnPretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String registracioniBroj = frmPretragaUverenja.getTxtRegistracioniBroj().getText().trim();
                    if (registracioniBroj.isEmpty()) {
                        JOptionPane.showMessageDialog(frmPretragaUverenja, "Unesite barem jednu vrednost za pretragu", "Uverenje greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Uverenje uverenjeNadji = new Uverenje();
                    Automobil auto = new Automobil();
                    
                    if(!registracioniBroj.isEmpty()) { 
                        auto.setRegistracioniBroj(registracioniBroj);
                        uverenjeNadji.setAutomobil(auto);
                    }
                    
                    List<Uverenje> uverenja = communication.Communication.getInstance().nadjiUverenja(uverenjeNadji);
                    if(uverenja.isEmpty()) {
                        prepareForm();
                        JOptionPane.showMessageDialog(frmPretragaUverenja, "Sistem ne može da nađe uverenja po zadatoj vrednosti", "Uverenje greška", JOptionPane.ERROR_MESSAGE);
                        frmPretragaUverenja.getTxtRegistracioniBroj().setText("");
                        return;
                    }
                    
                    JOptionPane.showMessageDialog(frmPretragaUverenja, "Sistem je našao uverenja po zadatoj vrednosti");
                    utm = (UverenjeTableModel) frmPretragaUverenja.getjTable1().getModel();
                    List<Klijent> klijenti = Communication.getInstance().ucitajListuKlijenata();
                    for (Uverenje u : uverenja) {
                        for (Klijent kl : klijenti) {
                            if (u.getKlijent().getKlijentId().equals(kl.getKlijentId())) {
                                u.setKlijent(kl);
                            }
                        }
                    }                    
                    utm.setUverenja(uverenja);
                    frmPretragaUverenja.getTxtRegistracioniBroj().setText("");
                } catch (Exception ex) {
                    Logger.getLogger(PretragaUverenjaForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPretragaUverenja, "Sistem ne može da nađe automobile po zadatoj vrednosti", "Uverenje greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        frmPretragaUverenja.btnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPretragaUverenja.getjTable1().getSelectedRow();
                if (row >= 0) {
                    Uverenje uverenje = ((UverenjeTableModel) frmPretragaUverenja.getjTable1().getModel()).vratiUverenje(row);
                    ClientCoordinator.getInstance().addParam(Constants.UVERENJE, uverenje);
                    ClientCoordinator.getInstance().addParam(Constants.UVERENJE_RED, row);
                    try {
                        ClientCoordinator.getInstance().openEditUverenjeForm(frmPretragaUverenja);
                    } catch (Exception ex) {
                        Logger.getLogger(PretragaUverenjaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frmPretragaUverenja, "Sistem ne može da učita uverenje.", "Uverenje greška", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frmPretragaUverenja, "Potrebno je da izaberete uverenje iz tabele", "Uverenje greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });        
    }
    
    private void prepareForm() {
        List<Uverenje> uverenja = new ArrayList<>();
        utm = new UverenjeTableModel(uverenja);
        frmPretragaUverenja.getjTable1().setModel(utm);
    }
    
}
