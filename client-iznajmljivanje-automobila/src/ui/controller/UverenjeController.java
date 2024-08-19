package ui.controller;

import communication.Communication;
import domain.Automobil;
import domain.Klijent;
import domain.Uverenje;
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
import ui.forms.PretragaUverenjaForm;
import ui.forms.UverenjeForm;
import ui.forms.components.tableModels.UverenjeTableModel;
import ui.forms.util.Constants;
import ui.forms.util.FormMode;

/**
 *
 * @author Filip
 */
public class UverenjeController {
    
    private final UverenjeForm uverenjeForm;

    public UverenjeController(UverenjeForm uverenjeForm) {
        this.uverenjeForm = uverenjeForm;
        addActionListeners();
    }
    
        private void addActionListeners() {
        uverenjeForm.btnSacuvajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
            }

            private void create() {
                try {
                    if (uverenjeForm.getTxtCena().getText().isEmpty() || uverenjeForm.getTxtDatePoc().getText().isEmpty() || uverenjeForm.getTxtDateKraj().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(uverenjeForm, "Sistem ne može da zapamti uverenje.", "Uverenje greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(uverenjeForm, "Nisu uneti svi podaci", "UVerenje greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!validateForm()) {
                        return;
                    }
                    Uverenje uverenje = new Uverenje();
                    uverenje.setKlijent((Klijent) uverenjeForm.getCbKlijent().getSelectedItem());
                    uverenje.setAutomobil((Automobil) uverenjeForm.getCbAutomobil().getSelectedItem());
                    uverenje.setCena(Double.parseDouble(uverenjeForm.getTxtCena().getText().trim()));
                    
                    String datumOd = uverenjeForm.getTxtDatePoc().getText().trim();
                    java.sql.Date datumPocetka = java.sql.Date.valueOf(datumOd);
                    uverenje.setDatumOd(datumPocetka);
                    
                    String datumDo = uverenjeForm.getTxtDateKraj().getText().trim();
                    java.sql.Date datumZavrsetka = java.sql.Date.valueOf(datumDo);
                    uverenje.setDatumDo(datumZavrsetka);
                    
                    Communication.getInstance().kreirajUverenje(uverenje);
                    JOptionPane.showMessageDialog(uverenjeForm, "Sistem је zapamtio uverenje", "Čuvanje uverenja", JOptionPane.INFORMATION_MESSAGE);
                    uverenjeForm.dispose();
                } catch (Exception e) {
                    Logger.getLogger(AutomobilController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(uverenjeForm, "Sistem ne može da zapamti uverenje", "Uverenje greška", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        uverenjeForm.btnOmoguciIzmenuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uverenjeForm.getBtnSacuvaj().setEnabled(false);
                uverenjeForm.getBtnOmoguciIzmenu().setEnabled(false);
                uverenjeForm.getBtnIzmeni().setEnabled(true);
                uverenjeForm.getTxtCena().setEnabled(true);
                uverenjeForm.getTxtDatePoc().setEnabled(true);
                uverenjeForm.getTxtDateKraj().setEnabled(true);
                uverenjeForm.getCbAutomobil().setEnabled(true);
                uverenjeForm.getCbKlijent().setEnabled(true);
            }
        });

        uverenjeForm.btnIzmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }

            private void update() {
                try {
                    if (uverenjeForm.getTxtCena().getText().isEmpty() || uverenjeForm.getTxtDatePoc().getText().isEmpty() || uverenjeForm.getTxtDateKraj().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(uverenjeForm, "Sistem ne može da zapamti uverenje.", "Uverenje greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(uverenjeForm, "Nisu uneti svi podaci", "Uvernenje greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!validateForm()) {
                        return;
                    }
                    Uverenje uverenje = new Uverenje();
                    uverenje.setUverenjeId(((Uverenje)ClientCoordinator.getInstance().getParam(Constants.UVERENJE)).getUverenjeId());
                    uverenje.setAutomobil((Automobil) uverenjeForm.getCbAutomobil().getSelectedItem());
                    uverenje.setKlijent((Klijent) uverenjeForm.getCbKlijent().getSelectedItem());
                    uverenje.setCena(Double.parseDouble(uverenjeForm.getTxtCena().getText().trim()));
                    uverenje.setDatumOd(java.sql.Date.valueOf(uverenjeForm.getTxtDatePoc().getText().trim()));
                    uverenje.setDatumDo(java.sql.Date.valueOf(uverenjeForm.getTxtDateKraj().getText().trim()));

                    Communication.getInstance().izmeniUverenje(uverenje);
                    
                    PretragaUverenjaForm frmPretragaUverenja = (PretragaUverenjaForm) uverenjeForm.getParent();
                    UverenjeTableModel utm = (UverenjeTableModel) frmPretragaUverenja.getjTable1().getModel();
                    utm.izmeniUverenje(uverenje, (int) ClientCoordinator.getInstance().getParam(Constants.UVERENJE_RED));
                    JOptionPane.showMessageDialog(uverenjeForm, "Sistem је zapamtio uverenje", "Čuvanje uverenje", JOptionPane.INFORMATION_MESSAGE);
                    uverenjeForm.dispose();
                } catch (Exception e) {
                    Logger.getLogger(AutomobilController.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(uverenjeForm, "Sistem ne može da zapamti uverenje", "Automobil greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
        
        
    private void setMode(FormMode formMode) {
        
        switch (formMode) {
            case ADD:
                uverenjeForm.getBtnSacuvaj().setEnabled(true);
                uverenjeForm.getBtnOmoguciIzmenu().setEnabled(false);
                uverenjeForm.getBtnIzmeni().setEnabled(false);
                uverenjeForm.getCbKlijent().setEnabled(true);
                uverenjeForm.getCbAutomobil().setEnabled(true);
                uverenjeForm.getTxtCena().setEnabled(true);
                uverenjeForm.getTxtDatePoc().setEnabled(true);
                uverenjeForm.getTxtDateKraj().setEnabled(true);
                break;
            case EDIT:
                uverenjeForm.getBtnSacuvaj().setEnabled(false);
                uverenjeForm.getBtnOmoguciIzmenu().setEnabled(true);
                uverenjeForm.getBtnIzmeni().setEnabled(false);
                uverenjeForm.getTxtCena().setEnabled(false);
                uverenjeForm.getTxtDatePoc().setEnabled(false);
                uverenjeForm.getTxtDateKraj().setEnabled(false);
                uverenjeForm.getCbAutomobil().setEnabled(false);
                uverenjeForm.getCbKlijent().setEnabled(false);

                Uverenje izabranoUverenje = (Uverenje) ClientCoordinator.getInstance().getParam(Constants.UVERENJE);
                uverenjeForm.getTxtCena().setText(String.valueOf(izabranoUverenje.getCena()));
                uverenjeForm.getTxtDatePoc().setText(String.valueOf(izabranoUverenje.getDatumOd()));
                uverenjeForm.getTxtDateKraj().setText(String.valueOf(izabranoUverenje.getDatumDo()));
                uverenjeForm.getCbKlijent().setSelectedItem(izabranoUverenje.getKlijent());
                uverenjeForm.getCbAutomobil().setSelectedItem(izabranoUverenje.getAutomobil());
                break;
            default:
                break;
        }
    }
        
        public void openForm(FormMode formMode) {

        if (formMode.equals(FormMode.EDIT)) {
            uverenjeForm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    try {
                        Uverenje ucitanoUverenje = Communication.getInstance().nadjiUverenje((Uverenje) ClientCoordinator.getInstance().getParam(Constants.UVERENJE));
                        if(ucitanoUverenje==null){
                            throw new Exception();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(uverenjeForm, "Sistem ne može da učita uverenje", "Uverenje greška", JOptionPane.ERROR_MESSAGE);
                        uverenjeForm.dispose();
                    }
                    JOptionPane.showMessageDialog(uverenjeForm, "Sistem je učitao uverenje");
                }
            });
        }

        uverenjeForm.setTitle("Uverenje");
        uverenjeForm.setLocationRelativeTo(uverenjeForm.getParent());
        prepareForm();
        setMode(formMode);
        uverenjeForm.setVisible(true);
    }
        
    private void prepareForm() {
        try {
            populateCbKlijent();
            populateCbAutomobil();
        } catch (Exception ex) {
            Logger.getLogger(AutomobilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateCbKlijent() throws Exception {
        uverenjeForm.getCbKlijent().removeAllItems();
        List<Klijent> klijenti = Communication.getInstance().ucitajListuKlijenata();
        uverenjeForm.getCbKlijent().setModel(new DefaultComboBoxModel<>(klijenti.toArray()));
    }
    
    private void populateCbAutomobil() throws Exception {
        uverenjeForm.getCbAutomobil().removeAllItems();
        List<Automobil> automobili = Communication.getInstance().ucitajListuAutomobila();
        uverenjeForm.getCbAutomobil().setModel(new DefaultComboBoxModel<>(automobili.toArray()));
    }

private boolean validateForm() {
    try {
        Double.parseDouble(uverenjeForm.getTxtCena().getText().trim());
    }
    catch(NumberFormatException ex) {
        JOptionPane.showMessageDialog(uverenjeForm, "Cena mora biti broj", "Cena greška", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    try {
        var datumOd = java.sql.Date.valueOf(uverenjeForm.getTxtDatePoc().getText().trim());
        var datumDo = java.sql.Date.valueOf(uverenjeForm.getTxtDateKraj().getText().trim());
        
        
        if (datumOd.after(datumDo)) {
            JOptionPane.showMessageDialog(uverenjeForm, "Datum iznajmljivanja mora biti pre datuma vraćanja automobila", "Datum greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(uverenjeForm, "Pogrešan format datuma. Molimo unesite datum u formatu yyyy-MM-dd.", "Datum greška", JOptionPane.ERROR_MESSAGE);
        return false;
    } 
    return true;
    }
}
