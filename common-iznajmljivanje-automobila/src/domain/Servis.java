package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Filip
 */
public class Servis implements AbstractDomainObject {

    private Long servisId;
    private String naziv;

    public Servis() {
    }

    public Servis(Long servisId, String naziv) {
        this.servisId = servisId;
        this.naziv = naziv;
    }

    public Long getServisId() {
        return servisId;
    }

    public void setServisId(Long servisId) {
        this.servisId = servisId;
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
        final Servis other = (Servis) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return "Servis: " + naziv;
    }
    
    @Override
    public String getTableName() {
        return "servis";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        while (resultSet.next()) {
            Long servisId = resultSet.getLong("servisId");
            String naziv = resultSet.getString("naziv");
            
            Servis s = new Servis(servisId, naziv);
            list.add(s);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "servisId, naziv";
    }

    @Override
    public String getUnknownValues() {
        return "?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
        Servis s = (Servis) ado;
        ps.setLong(1, s.getServisId());
        ps.setString(2, s.getNaziv());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID(AbstractDomainObject ado) {
        Servis s = (Servis) ado;
        return "servisId=" + s.getServisId();
    }

    @Override
    public String getOrderCondition() {
        return "servisId";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCondition(AbstractDomainObject ado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
