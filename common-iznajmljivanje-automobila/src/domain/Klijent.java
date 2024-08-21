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
public class Klijent implements AbstractDomainObject {

    private Long klijentId;
    private String ime;
    private String prezime;
    private String email;
    private String adresa;

    public Klijent() {
    }

    public Klijent(Long klijentId, String ime, String prezime, String email, String adresa) {
        this.klijentId = klijentId;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.adresa = adresa;
    }

    public Long getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Long klijentId) {
        this.klijentId = klijentId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
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
        final Klijent other = (Klijent) obj;
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " email=" + email + " adresa=" + adresa;
    }

    @Override
    public String getTableName() {
        return "klijent";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        while (resultSet.next()) {
            Long klijentId = resultSet.getLong("klijentId");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String email = resultSet.getString("email");
            String adresa = resultSet.getString("adresa");
            
            Klijent k = new Klijent(klijentId, ime, prezime, email, adresa);
            list.add(k);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "ime,prezime,email,adresa";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
        Klijent k = (Klijent) ado;
        ps.setString(1, k.getIme());
        ps.setString(2, k.getPrezime());
        ps.setString(3, k.getEmail());
        ps.setString(4, k.getAdresa());
    }

    @Override
    public String getUpdateQuery() {
        return "ime=?, prezime=?, email=? ,adresa=?";
    }

    @Override
    public String getID(AbstractDomainObject ado) {
       Klijent k = (Klijent) ado;
       return "klijentId=" + k.getKlijentId();
    }

    @Override
    public String getOrderCondition() {
        return "klijentId";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        AbstractDomainObject ado = null;
        if (resultSet.next()) {
            Long klijentId = resultSet.getLong("klijentId");
            String ime = resultSet.getString("ime");
            String email = resultSet.getString("email");
            String prezime = resultSet.getString("prezime");
            String adresa = resultSet.getString("adresa");

            ado = new Klijent(klijentId, ime, prezime, email, adresa);
        }
        return ado;
    }

    @Override
    public String getCondition(AbstractDomainObject ado) {
        Klijent k = (Klijent) ado;
        
        boolean hasIme = k.getIme() != null && !k.getIme().trim().isEmpty();
        boolean hasPrezime = k.getPrezime() != null && !k.getPrezime().trim().isEmpty();
        
        if (hasIme && !hasPrezime) {
            return "ime LIKE '%" + k.getIme() + "%'";
        } 
        if (!hasIme && hasPrezime) {
            return "prezime LIKE '%" + k.getPrezime() + "%'";
        }
        
        return "ime LIKE '%" + k.getIme() + "%' AND prezime LIKE '%" + k.getPrezime() + "%'";
    }

    @Override
    public void setID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
