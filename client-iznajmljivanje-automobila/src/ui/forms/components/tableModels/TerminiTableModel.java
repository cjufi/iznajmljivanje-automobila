package ui.forms.components.tableModels;

import domain.TerminVoznje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Filip
 */
public class TerminiTableModel extends AbstractTableModel {

    private final String[] columnNames = {"DatumOd", "DatumDo"};
    private List<TerminVoznje> termini;

    public List<TerminVoznje> getTermini() {
        return termini;
    }

    public void setTermini(List<TerminVoznje> termini) {
        this.termini = termini;
        fireTableDataChanged();
    }

    public TerminiTableModel(List<TerminVoznje> termini) {
        this.termini = termini;
    }
    
    @Override
    public int getRowCount() {
        if(termini == null) {
            return 0;
        }
        return termini.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TerminVoznje tv = termini.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return tv.getDatumOd();
            case 1:
                return tv.getDatumDo();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void dodajTermin(TerminVoznje tv) {
        termini.add(tv);
        fireTableDataChanged();
    }
    
    public void obrisiTermin(int row) {
        termini.remove(row);
        fireTableDataChanged();
    }
    
    public TerminVoznje getTerminVoznjeAt(int row) {
        return termini.get(row);
    }
}
