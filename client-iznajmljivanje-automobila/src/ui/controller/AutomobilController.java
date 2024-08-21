package ui.controller;

import communication.Communication;
import domain.Automobil;
import domain.TipAutomobila;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import ui.coordinator.ClientCoordinator;
import ui.forms.AutomobilForm;
import ui.forms.PretragaAutomobilaForm;
import ui.forms.components.tableModels.AutomobilTableModel;
import ui.forms.util.Constants;
import ui.forms.util.FormMode;

/**
 *
 * @author Filip
 */
public class AutomobilController {
    
    private final AutomobilForm automobilForm;

    public AutomobilController(AutomobilForm automobilForm) {
        this.automobilForm = automobilForm;
        addActionListeners();
    }
    
    private void addActionListeners() {
        automobilForm.btnSacuvajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
            }

            private void create() {
                try {
                    if (automobilForm.getTxtRegistracioniBroj().getText().isEmpty() || automobilForm.getTxtMarka().getText().isEmpty() || automobilForm.getTxtModel().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(automobilForm, "Sistem ne može da zapamti automobil.", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(automobilForm, "Nisu uneti svi podaci", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!validateForm()) {
                        return;
                    }
                    Automobil automobil = new Automobil();
                    automobil.setRegistracioniBroj(automobilForm.getTxtRegistracioniBroj().getText().trim());
                    automobil.setMarka(automobilForm.getTxtMarka().getText().trim());
                    automobil.setModel(automobilForm.getTxtModel().getText().trim());
                    TipAutomobila tp = (TipAutomobila) automobilForm.getCbTipAutomobila().getSelectedItem();
                    automobil.setTipAutomobila(tp);
                    Communication.getInstance().kreirajAutomobil(automobil);
                    JOptionPane.showMessageDialog(automobilForm, "Sistem је zapamtio automobil", "Čuvanje automobila", JOptionPane.INFORMATION_MESSAGE);
                    automobilForm.dispose();
                } catch (Exception e) {
                    Logger.getLogger(AutomobilController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(automobilForm, "Sistem ne može da zapamti automobil", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        automobilForm.btnOmoguciIzmenuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                automobilForm.getBtnSacuvaj().setEnabled(false);
                automobilForm.getBtnOmoguciIzmenu().setEnabled(false);
                automobilForm.getBtnIzmeni().setEnabled(true);
                automobilForm.getBtnObrisi().setEnabled(false);
                automobilForm.getTxtRegistracioniBroj().setEnabled(false);
                automobilForm.getTxtMarka().setEnabled(true);
                automobilForm.getTxtModel().setEnabled(true);
                automobilForm.getCbTipAutomobila().setEnabled(true);
            }
        });

        automobilForm.btnIzmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }

            private void update() {
                try {
                    if (automobilForm.getTxtMarka().getText().isEmpty() || automobilForm.getTxtModel().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(automobilForm, "Sistem ne može da zapamti automobil.", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(automobilForm, "Nisu uneti svi podaci", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!validateForm()) {
                        return;
                    }
                    Automobil automobil = new Automobil();
                    automobil.setRegistracioniBroj(((Automobil) ClientCoordinator.getInstance().getParam(Constants.AUTOMOBIL)).getRegistracioniBroj());
                    automobil.setMarka(automobilForm.getTxtMarka().getText().trim());
                    automobil.setModel(automobilForm.getTxtModel().getText().trim());
                    TipAutomobila tipAutomobila = (TipAutomobila) automobilForm.getCbTipAutomobila().getSelectedItem();
                    automobil.setTipAutomobila(tipAutomobila);
                    Communication.getInstance().izmeniAutomobil(automobil);
                    PretragaAutomobilaForm frmPretragaAutomobil = (PretragaAutomobilaForm) automobilForm.getParent();
                    AutomobilTableModel atm = (AutomobilTableModel) frmPretragaAutomobil.getjTable1().getModel();
                    atm.izmeniAutomobil(automobil, (int) ClientCoordinator.getInstance().getParam(Constants.AUTOMOBIL_RED));
                    JOptionPane.showMessageDialog(automobilForm, "Sistem је zapamtio automobil", "Čuvanje automobil", JOptionPane.INFORMATION_MESSAGE);
                    automobilForm.dispose();
                } catch (Exception e) {
                    Logger.getLogger(AutomobilController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(automobilForm, "Sistem ne može da zapamti automobil", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        automobilForm.btnObrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
            
            private void delete() {
                try {
                    Communication.getInstance().obrisiAutomobil((Automobil) ClientCoordinator.getInstance().getParam(Constants.AUTOMOBIL));
                    JOptionPane.showMessageDialog(automobilForm, "Sistem je obrisao automobil", "Brisanje automobila", JOptionPane.INFORMATION_MESSAGE);
                    automobilForm.dispose();
                    PretragaAutomobilaForm frmPretragaAutomobila = (PretragaAutomobilaForm) automobilForm.getParent();
                    AutomobilTableModel atm = (AutomobilTableModel) frmPretragaAutomobila.getjTable1().getModel();
                    atm.obrisiAutomobil((Automobil) ClientCoordinator.getInstance().getParam(Constants.AUTOMOBIL));
                } catch (Exception ex) {
                    Logger.getLogger(AutomobilController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(automobilForm, "Sistem ne može da obriše automobil", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setMode(FormMode formMode) {
        
        switch (formMode) {
            case ADD:
                automobilForm.getBtnSacuvaj().setEnabled(true);
                automobilForm.getBtnOmoguciIzmenu().setEnabled(false);
                automobilForm.getBtnIzmeni().setEnabled(false);
                automobilForm.getTxtRegistracioniBroj().setEnabled(true);
                automobilForm.getTxtMarka().setEnabled(true);
                automobilForm.getTxtModel().setEnabled(true);
                automobilForm.getCbTipAutomobila().setEnabled(true);
                break;
            case EDIT:
                automobilForm.getBtnSacuvaj().setEnabled(false);
                automobilForm.getBtnOmoguciIzmenu().setEnabled(true);
                automobilForm.getBtnIzmeni().setEnabled(false);
                automobilForm.getTxtRegistracioniBroj().setEnabled(false);
                automobilForm.getTxtMarka().setEnabled(false);
                automobilForm.getTxtModel().setEnabled(false);
                automobilForm.getCbTipAutomobila().setEnabled(false);

                Automobil izabraniAutomobil = (Automobil) ClientCoordinator.getInstance().getParam(Constants.AUTOMOBIL);
                automobilForm.getTxtRegistracioniBroj().setText(izabraniAutomobil.getRegistracioniBroj());
                automobilForm.getTxtMarka().setText(izabraniAutomobil.getMarka());
                automobilForm.getTxtModel().setText(String.valueOf(izabraniAutomobil.getModel()));
                automobilForm.getCbTipAutomobila().setSelectedItem(izabraniAutomobil.getTipAutomobila());
                break;
            default:
                break;
        }
    }

    public void openForm(FormMode formMode) {

        if (formMode.equals(FormMode.EDIT)) {
            automobilForm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    try {
                        Automobil ucitaniAutomobil = Communication.getInstance().nadjiAutomobil((Automobil) ClientCoordinator.getInstance().getParam(Constants.AUTOMOBIL));
                        if(ucitaniAutomobil==null){
                            throw new Exception();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(automobilForm, "Sistem ne može da učita automobil", "Automobil greska",JOptionPane.ERROR_MESSAGE);
                        automobilForm.dispose();
                    }
                        JOptionPane.showMessageDialog(automobilForm, "Sistem je učitao automobil");
                }
            });
        }

        automobilForm.setTitle("Automobil");
        automobilForm.setLocationRelativeTo(automobilForm.getParent());
        prepareForm();
        setMode(formMode);
        automobilForm.setVisible(true);
    }

    private void prepareForm() {
        try {
            populateCbTipAutomobila();
        } catch (Exception ex) {
            Logger.getLogger(AutomobilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateCbTipAutomobila() throws Exception {
        automobilForm.getCbTipAutomobila().removeAllItems();
        List<TipAutomobila> automobili = Communication.getInstance().ucitajListuTipovaAutomobila();
        automobilForm.getCbTipAutomobila().setModel(new DefaultComboBoxModel<>(automobili.toArray()));
    }

    private boolean validateForm() {
        String registracioniBroj = automobilForm.getTxtRegistracioniBroj().getText().trim();
        if(registracioniBroj.length() != 7) {
            JOptionPane.showMessageDialog(automobilForm, "Registracioni broj mora imati tacno 7 karaktera", "Registracioni broj greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
