package domain;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Filip
 */
public class Uverenje implements AbstractDomainObject {
    
    private Long uverenjeId;
    private double cena;
    private Date datumOd;
    private Date datumDo;
    private Automobil automobil;
    private Klijent klijent;

    public Uverenje() {
    }

    public Uverenje(Long uverenjeId, double cena, Date datumOd, Date datumDo, Automobil automobil, Klijent klijent) {
        this.uverenjeId = uverenjeId;
        this.cena = cena;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.automobil = automobil;
        this.klijent = klijent;
    }

    public Long getUverenjeId() {
        return uverenjeId;
    }

    public void setUverenjeId(Long uverenjeId) {
        this.uverenjeId = uverenjeId;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
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

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    @Override
    public String toString() {
        return "Uverenje{" + "uverenjeId=" + uverenjeId + ", cena=" + cena + ", datumOd=" + datumOd + ", datumDo=" + datumDo + ", automobil=" + automobil + ", klijent=" + klijent + '}';
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
        final Uverenje other = (Uverenje) obj;
        return Objects.equals(this.uverenjeId, other.uverenjeId);
    }

    @Override
    public String getTableName() {
        return "uverenje";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        while (resultSet.next()) {
            Long uverenjeId = resultSet.getLong("uverenjeId");
            Date datumOd = resultSet.getDate("datumOd");
            Date datumDo = resultSet.getDate("datumDo");
            Double cena = resultSet.getDouble("cena");
            String registracioniBroj = resultSet.getString("registracioniBroj");
            Long klijentId = resultSet.getLong("klijentId");

            Automobil a = new Automobil();
            a.setRegistracioniBroj(registracioniBroj);
            
            Klijent kl = new Klijent();
            kl.setKlijentId(klijentId);
            

            Uverenje u = new Uverenje(uverenjeId, cena, datumOd, datumDo, a, kl);
            
            list.add(u);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "datumOd, datumDo, cena, registracioniBroj, klijentId";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
        Uverenje u = (Uverenje) ado;
        ps.setDate(1, (java.sql.Date) u.getDatumOd());
        ps.setDate(2, (java.sql.Date) u.getDatumDo());
        ps.setDouble(3, u.getCena());
        ps.setString(4, u.getAutomobil().getRegistracioniBroj());
        ps.setLong(5, u.getKlijent().getKlijentId());
    }

    @Override
    public String getUpdateQuery() {
        return "datumOd=?, datumDo=?, cena=?, registracioniBroj=?, klijentId=?";
    }

    @Override
    public String getID(AbstractDomainObject ado) {
        Uverenje u = (Uverenje) ado;
        return "uverenjeId=" + u.getUverenjeId();
    }

    @Override
    public String getOrderCondition() {
        return "uverenjeId";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        AbstractDomainObject ado = null;
        if(resultSet.next()) {
            Long uverenjeId = resultSet.getLong("uverenjeId");
            Double cena = resultSet.getDouble("cena");
            Date datumOd = resultSet.getDate("datumOd");
            Date datumDo = resultSet.getDate("datumDo");
            String registracioniBroj = resultSet.getString("registracioniBroj");
            Long klijentId = resultSet.getLong("klijentId");
            
            Automobil a = new Automobil();
            a.setRegistracioniBroj(registracioniBroj);
            
            Klijent k = new Klijent();
            k.setKlijentId(klijentId);
            
            ado = new Uverenje(uverenjeId, cena, datumOd, datumDo, a, k);
        }
        
        return ado;
    }

    @Override
    public String getCondition(AbstractDomainObject ado) {
        Uverenje uverenje = (Uverenje) ado;
        return "registracioniBroj LIKE '%" + uverenje.getAutomobil().getRegistracioniBroj()+ "%'";
    }
}
