/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.forms.components.tableModels;

import domain.Automobil;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Filip
 */
public class AutomobilTableModel extends AbstractTableModel {
    
    private final String[] columnNames = {"Registracioni Broj", "Marka", "Model", "TipAutomobila"};
    private List<Automobil> automobili;

    public AutomobilTableModel(List<Automobil> automobili) {
        this.automobili = automobili;
    }

    public List<Automobil> getAutomobili() {
        return automobili;
    }

    public void setAutomobili(List<Automobil> automobili) {
        this.automobili = automobili;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if(automobili == null) {
            return 0;
        }
        return automobili.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Automobil a = automobili.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return a.getRegistracioniBroj();
            case 1:
                return a.getMarka();
            case 2:
                return a.getModel();
            case 3:
                return a.getTipAutomobila().getNaziv();         
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void obrisiAutomobil(Automobil a) {
        automobili.remove(a);
        fireTableDataChanged();
    }
    
    public void izmeniAutomobil(Automobil a, int row) {
        Automobil a1 = automobili.get(row);
        a1.setRegistracioniBroj(a.getRegistracioniBroj());
        a1.setMarka(a.getMarka());
        a1.setModel(a.getModel());
        a1.setTipAutomobila(a.getTipAutomobila());
        fireTableDataChanged();
    }
    
    public Automobil vratiAutomobil(int row) {
        return automobili.get(row);
    }
}
