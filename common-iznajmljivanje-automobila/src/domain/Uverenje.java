/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    private Korisnik korisnik;

    public Uverenje() {
    }

    public Uverenje(Long uverenjeId, double cena, Date datumOd, Date datumDo, Automobil automobil, Klijent klijent, Korisnik korisnik) {
        this.uverenjeId = uverenjeId;
        this.cena = cena;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.automobil = automobil;
        this.klijent = klijent;
        this.korisnik = korisnik;
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return "Uverenje{" + "uverenjeId=" + uverenjeId + ", cena=" + cena + ", datumOd=" + datumOd + ", datumDo=" + datumDo + ", automobil=" + automobil + ", klijent=" + klijent + ", korisnik=" + korisnik + '}';
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
            Long korisnikId = resultSet.getLong("korisnikId");

            Automobil a = new Automobil();
            a.setRegistracioniBroj(registracioniBroj);
            
            Klijent kl = new Klijent();
            kl.setKlijentId(klijentId);
            
            Korisnik ko = new Korisnik();
            ko.setKorisnikId(korisnikId);

            Uverenje u = new Uverenje(uverenjeId, cena, datumOd, datumDo, a, kl, ko);
            
            list.add(u);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "uverenjeId, datumOd, datumDo, cena, registracioniBroj, klijentId, korisnikId";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
        Uverenje u = (Uverenje) ado;
        ps.setLong(1, u.getUverenjeId());
        ps.setDate(2, (java.sql.Date) u.getDatumOd());
        ps.setDate(3, (java.sql.Date) u.getDatumDo());
        ps.setDouble(4, u.getCena());
        ps.setString(5, u.getAutomobil().getRegistracioniBroj());
        ps.setLong(6, u.getKlijent().getKlijentId());
        ps.setLong(7, u.getKorisnik().getKorisnikId());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public AbstractDomainObject getResult(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCondition(AbstractDomainObject ado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
