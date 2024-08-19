package ui.forms.components.tableModels;

import domain.Automobil;
import domain.Uverenje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Filip
 */
public class UverenjeTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Id", "Cena", "Datum Od", "Datum Do", "Registracioni Broj", "Klijent"};
    private List<Uverenje> uverenja;

    public UverenjeTableModel(List<Uverenje> uverenja) {
        this.uverenja = uverenja;
    }

    public List<Uverenje> getUverenja() {
        return uverenja;
    }

    public void setUverenja(List<Uverenje> uverenja) {
        this.uverenja = uverenja;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if(uverenja == null) {
            return 0;
        }
        return uverenja.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Uverenje uverenje = uverenja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return uverenje.getUverenjeId();
            case 1:
                return uverenje.getCena();
            case 2:
                return uverenje.getDatumOd();
            case 3:
                return uverenje.getDatumDo();
            case 4:
                return uverenje.getAutomobil().getRegistracioniBroj();
            case 5:
                return uverenje.getKlijent().getEmail();
            default:
                return "n/a";
        }
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void izmeniUverenje(Uverenje u, int row) {
        Uverenje u1 = uverenja.get(row);
        u1.setUverenjeId(u.getUverenjeId());
        u1.setCena(u.getCena());
        u1.setDatumOd(u.getDatumOd());
        u1.setDatumDo(u.getDatumDo());
        u1.setAutomobil(u.getAutomobil());
        u1.setKlijent(u.getKlijent());
        fireTableDataChanged();
    }
    
    public Uverenje vratiUverenje(int row) {
        return uverenja.get(row);
    }  
}
