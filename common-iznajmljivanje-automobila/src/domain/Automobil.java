package domain;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Filip
 */
public class Automobil implements AbstractDomainObject {
    
    private String registracioniBroj;
    private String marka;
    private String model;
    private TipAutomobila tipAutomobila;

    public Automobil() {
    }

    public Automobil(String registracioniBroj, String marka, String model, TipAutomobila tipAutomobila) {
        this.registracioniBroj = registracioniBroj;
        this.marka = marka;
        this.model = model;
        this.tipAutomobila = tipAutomobila;
    }
    
    public String getRegistracioniBroj() {
        return registracioniBroj;
    }

    public void setRegistracioniBroj(String registracioniBroj) {
        this.registracioniBroj = registracioniBroj;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public TipAutomobila getTipAutomobila() {
        return tipAutomobila;
    }

    public void setTipAutomobila(TipAutomobila tipAutomobila) {
        this.tipAutomobila = tipAutomobila;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Automobil other = (Automobil) obj;
        return Objects.equals(this.registracioniBroj, other.registracioniBroj);
    }

    @Override
    public String toString() {
        return registracioniBroj + " " + marka + " " + model;
    }
    
    @Override
    public String getTableName() {
        return "automobil";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        while (resultSet.next()) {
            String registracioniBroj = resultSet.getString("registracioniBroj");
            String marka = resultSet.getString("marka");
            String model = resultSet.getString("model");
            Long tipId = resultSet.getLong("tipId");
            
            TipAutomobila ta = new TipAutomobila();
            ta.setTipId(tipId);

            Automobil a = new Automobil(registracioniBroj, marka, model, ta);
            list.add(a);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "registracioniBroj, marka, model, tipId";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
        Automobil a = (Automobil) ado;
        ps.setString(1, a.getRegistracioniBroj());
        ps.setString(2, a.getMarka());
        ps.setString(3, a.getModel());
        ps.setLong(4, a.getTipAutomobila().getTipId());
    }

    @Override
    public String getUpdateQuery() {
        return "registracioniBroj=?,marka=?, model=?, tipId=?";
    }

    @Override
    public String getID(AbstractDomainObject ado) {
        Automobil a = (Automobil) ado;
        return "registracioniBroj LIKE '%" + a.getRegistracioniBroj()+ "%'";
    }

    @Override
    public String getOrderCondition() {
        return "registracioniBroj";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        
        AbstractDomainObject ado = null;
        if (resultSet.next()) {
            String registracioniBroj = resultSet.getString("registracioniBroj");
            String marka = resultSet.getString("marka");
            String model = resultSet.getString("model");
            Long tipId = resultSet.getLong("tipId");

            TipAutomobila tp = new TipAutomobila();
            tp.setTipId(tipId);
            
            ado = new Automobil(registracioniBroj, marka, model, tipAutomobila);
        }
        return ado;
    }

    @Override
    public String getCondition(AbstractDomainObject ado) {
        Automobil a = (Automobil) ado;
        return "registracioniBroj LIKE '%" + a.getRegistracioniBroj()+ "%'";
    }
}
