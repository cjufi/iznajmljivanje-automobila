/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class Korisnik implements AbstractDomainObject {

    private Long korisnikId;
    private String korisnickoIme;
    private String sifra;

    public Korisnik() {
    }

    public Korisnik(Long korisnikId, String korisnickoIme, String sifra) {
        this.korisnikId = korisnikId;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Korisnik(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
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
        final Korisnik other = (Korisnik) obj;
        return Objects.equals(this.korisnickoIme, other.korisnickoIme);
    }
    
    @Override
    public String getTableName() {
        return "korisnik";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        while (resultSet.next()) {
            Long korisnikId = resultSet.getLong("korisnikId");
            String korisnickoIme = resultSet.getString("korisnickoIme");
            String sifra = resultSet.getString("sifra");
            
            Korisnik k = new Korisnik(korisnikId, korisnickoIme, sifra);
            list.add(k);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "korisnikId,korisnickoIme,sifra";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
       Korisnik k = (Korisnik) ado;
       ps.setLong(1, k.getKorisnikId());
       ps.setString(2, k.getKorisnickoIme());
       ps.setString(3, k.getSifra());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID(AbstractDomainObject ado) {
        Korisnik k = (Korisnik) ado;
        return "korisnikId=" + k.getKorisnikId();
    }

    @Override
    public String getOrderCondition() {
        return "korisnikId";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        AbstractDomainObject ado = null;
        if (resultSet.next()) {
            Long korisnikId = resultSet.getLong("korisnikId");
            String korisnickoIme = resultSet.getString("korisnickoIme");
            String sifra = resultSet.getString("sifra");

            ado = new Korisnik(korisnikId, korisnickoIme, sifra);
        }
        
        return ado;        
    }

    @Override
    public String getCondition(AbstractDomainObject ado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
