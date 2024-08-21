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
public class TipAutomobila implements AbstractDomainObject {
    
    private Long tipId;
    private String naziv;

    public TipAutomobila() {
    }

    public TipAutomobila(Long tipId, String naziv) {
        this.tipId = tipId;
        this.naziv = naziv;
    }

    public Long getTipId() {
        return tipId;
    }

    public void setTipId(Long tipId) {
        this.tipId = tipId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
        final TipAutomobila other = (TipAutomobila) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    @Override
    public String getTableName() {
        return "tipautomobila";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        
        while(rs.next()) {
            Long tipId = rs.getLong("tipId");
            String naziv = rs.getString("naziv");
        
            TipAutomobila ta = new TipAutomobila(tipId, naziv);
            list.add(ta);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "naziv";
    }

    @Override
    public String getUnknownValues() {
        return "?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
        TipAutomobila ta = (TipAutomobila) ado;
        ps.setString(1, ta.getNaziv());
    }

    @Override
    public String getUpdateQuery() {
        return "naziv=?";
    }

    @Override
    public String getID(AbstractDomainObject ado) {
        TipAutomobila ta = (TipAutomobila) ado;
        return "tipId=" + ta.getTipId();
    }

    @Override
    public String getOrderCondition() {
        return "naziv";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet rs) throws Exception {
        AbstractDomainObject ado = null;
        if (rs.next()) {
            Long tipId = rs.getLong("tipId");
            String naziv = rs.getString("naziv");

            ado = new TipAutomobila(tipId, naziv);
        }
        return ado;
    }

    @Override
    public String getCondition(AbstractDomainObject ado) {
        TipAutomobila tp = (TipAutomobila) ado;
        return "naziv LIKE '%" + tp.getNaziv() + "%'";
    }

    @Override
    public void setID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
