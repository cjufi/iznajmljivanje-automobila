package ui.forms.components.tableModels;

import domain.Klijent;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Filip
 */
public class KlijentTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Ime", "Prezime", "Email", "Adresa"};
    private List<Klijent> klijenti;

    public KlijentTableModel(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<Klijent> klijenti) {
        this.klijenti = klijenti;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if(klijenti == null) {
            return 0;
        }
        return klijenti.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = klijenti.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return k.getIme();
            case 1:
                return k.getPrezime();
            case 2:
                return k.getEmail();
            case 3:
                return k.getAdresa();         
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void obrisiKlijenta(Klijent k) {
        klijenti.remove(k);
        fireTableDataChanged();
    }
    
    public void izmeniKlijenta(Klijent k, int row) {
        Klijent k1 = klijenti.get(row);
        k1.setIme(k.getIme());
        k1.setPrezime(k.getPrezime());
        k1.setEmail(k.getEmail());
        k1.setAdresa(k.getAdresa());
        fireTableDataChanged();
    }
    
    public Klijent vratiKlijenta(int row) {
        return klijenti.get(row);
    }
}
