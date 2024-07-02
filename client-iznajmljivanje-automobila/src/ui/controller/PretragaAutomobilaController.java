package ui.controller;

import domain.Automobil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.coordinator.ClientCoordinator;
import ui.forms.PretragaAutomobilaForm;
import ui.forms.components.tableModels.AutomobilTableModel;
import ui.forms.util.Constants;

/**
 *
 * @author Filip
 */
public class PretragaAutomobilaController {
    
    private final PretragaAutomobilaForm frmPretragaAutomobila;
    private AutomobilTableModel atm;

    public PretragaAutomobilaController(PretragaAutomobilaForm frmPretragaAutomobila) {
        this.frmPretragaAutomobila = frmPretragaAutomobila;
        addActionListeners();
    }
    
    public void openForm() {
        prepareForm();
        frmPretragaAutomobila.setTitle("Automobili");
        frmPretragaAutomobila.setLocationRelativeTo(ClientCoordinator.getInstance().getMainController().getFrmMain());
        frmPretragaAutomobila.setVisible(true);
    }
    
    private void addActionListeners() {
        frmPretragaAutomobila.btnPretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String registracioniBroj = frmPretragaAutomobila.getTxtRegistracioniBroj().getText().trim();
                    if (registracioniBroj.isEmpty()) {
                        JOptionPane.showMessageDialog(frmPretragaAutomobila, "Unesite barem jednu vrednost za pretragu", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Automobil automobilNadji = new Automobil();
                    if(!registracioniBroj.isEmpty()) automobilNadji.setRegistracioniBroj(registracioniBroj);
                    List<Automobil> automobili = communication.Communication.getInstance().nadjiAutomobile(automobilNadji);
                    if (automobili.isEmpty()) {
                        prepareForm();
                        JOptionPane.showMessageDialog(frmPretragaAutomobila, "Sistem ne može da nađe automobile po zadatoj vrednosti", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                        frmPretragaAutomobila.getTxtRegistracioniBroj().setText("");
                        return;
                    }
                    JOptionPane.showMessageDialog(frmPretragaAutomobila, "Sistem je našao automobila po zadatoj vrednosti");
                    System.out.println(automobili);
                    atm = (AutomobilTableModel) frmPretragaAutomobila.getjTable1().getModel();
                    atm.setAutomobili(automobili);
                    frmPretragaAutomobila.getTxtRegistracioniBroj().setText("");
                } catch (Exception ex) {
                    Logger.getLogger(PretragaAutomobilaForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPretragaAutomobila, "Sistem ne može da nađe automobile po zadatoj vrednosti", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        frmPretragaAutomobila.btnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPretragaAutomobila.getjTable1().getSelectedRow();
                if (row >= 0) {
                    Automobil automobil = ((AutomobilTableModel) frmPretragaAutomobila.getjTable1().getModel()).vratiAutomobil(row);
                    ClientCoordinator.getInstance().addParam(Constants.AUTOMOBIL, automobil);
                    ClientCoordinator.getInstance().addParam(Constants.AUTOMOBIL_RED, row);
                    try {
                        ClientCoordinator.getInstance().openEditAutomobilForm(frmPretragaAutomobila);
                    } catch (Exception ex) {
                        Logger.getLogger(PretragaAutomobilaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frmPretragaAutomobila, "Sistem ne može da učita automobil.", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frmPretragaAutomobila, "Potrebno je da izaberete automobil iz tabele", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });        
    }
    
    private void prepareForm() {
        List<Automobil> automobili = new ArrayList<>();
        atm = new AutomobilTableModel(automobili);
        frmPretragaAutomobila.getjTable1().setModel(atm);
    }
}
