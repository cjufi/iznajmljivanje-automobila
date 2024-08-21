package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Filip
 */
public class TerminVoznje implements AbstractDomainObject {
    
    private Long terminId;
    private Uverenje uverenje;
    private Date datumOd;
    private Date datumDo;

    public TerminVoznje() {
    }

    public TerminVoznje(Long terminId, Uverenje uverenje, Date datumOd, Date datumDo) {
        this.terminId = terminId;
        this.uverenje = uverenje;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Long getTerminId() {
        return terminId;
    }

    public void setTerminId(Long terminId) {
        this.terminId = terminId;
    }

    public Uverenje getUverenje() {
        return uverenje;
    }

    public void setUverenje(Uverenje uverenje) {
        this.uverenje = uverenje;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    @Override
    public String toString() {
        return "TerminVoznje{" + "terminId=" + terminId + ", uverenje=" + uverenje + ", datumOd=" + datumOd + ", datumDo=" + datumDo + '}';
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
        final TerminVoznje other = (TerminVoznje) obj;
        if (!Objects.equals(this.terminId, other.terminId)) {
            return false;
        }
        return Objects.equals(this.uverenje, other.uverenje);
    }

    @Override
    public String getTableName() {
        return "terminvoznje";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        while(rs.next()) {
            Long terminId = rs.getLong("terminId");
            Long uverenjeId = rs.getLong("uverenjeId");
            Date datumOd = rs.getDate("datumOd");
            Date datumDo = rs.getDate("datumDo");
            
            Uverenje u = new Uverenje();
            u.setUverenjeId(uverenjeId);
            
            TerminVoznje tv = new TerminVoznje(terminId, u, datumOd, datumDo);
            list.add(tv);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "uverenjeId, datumOd, datumDo";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
        TerminVoznje tv = (TerminVoznje) ado;
        ps.setLong(1, tv.getUverenje().getUverenjeId());
        ps.setDate(2, (java.sql.Date) tv.getDatumOd());
        ps.setDate(3, (java.sql.Date) tv.getDatumDo());
    }

    @Override
    public String getUpdateQuery() {
        return "uverenjeId=?,datumOd=?,datumDo=?";
    }

    @Override
    public String getID(AbstractDomainObject ado) {
        TerminVoznje tv = (TerminVoznje) ado;
        return "terminId=" + tv.getTerminId();
    }

    @Override
    public String getOrderCondition() {
        return "terminId";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCondition(AbstractDomainObject ado) {
        TerminVoznje tv = (TerminVoznje) ado;
        return "uverenjeId = " + tv.getUverenje().getUverenjeId();
    }

    @Override
    public void setID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
