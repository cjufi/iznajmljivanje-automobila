/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class InterakcijaKlijentAutomobil implements AbstractDomainObject {
    
    private Automobil automobil;
    private Klijent klijent;
    private int brojKoriscenja;

    public InterakcijaKlijentAutomobil() {
    }

    public InterakcijaKlijentAutomobil(Automobil automobil, Klijent klijent, int brojKoriscenja) {
        this.automobil = automobil;
        this.klijent = klijent;
        this.brojKoriscenja = brojKoriscenja;
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

    public int getBrojKoriscenja() {
        return brojKoriscenja;
    }

    public void setBrojKoriscenja(int brojKoriscenja) {
        this.brojKoriscenja = brojKoriscenja;
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
        final InterakcijaKlijentAutomobil other = (InterakcijaKlijentAutomobil) obj;
        if (!Objects.equals(this.automobil, other.automobil)) {
            return false;
        }
        return Objects.equals(this.klijent, other.klijent);
    }

    @Override
    public String getTableName() {
        return "interakcijaklijentautomobil";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        while (resultSet.next()) {
            String registracioniBroj = resultSet.getString("registracioniBroj");
            Long klijentId = resultSet.getLong("klijentId");
            int brojKoriscenja = resultSet.getInt("brojKoriscenja");
            
            Automobil a = new Automobil();
            a.setRegistracioniBroj(registracioniBroj);
            
            Klijent kl = new Klijent();
            kl.setKlijentId(klijentId);
            
            InterakcijaKlijentAutomobil i = new InterakcijaKlijentAutomobil(a, kl, brojKoriscenja);
            list.add(i);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "registracioniBroj, klijentId, brojKoriscenja";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
        InterakcijaKlijentAutomobil i = (InterakcijaKlijentAutomobil) ado;
        ps.setString(1, i.getAutomobil().getRegistracioniBroj());
        ps.setLong(2, i.getKlijent().getKlijentId());
        ps.setInt(3, i.getBrojKoriscenja());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID(AbstractDomainObject ado) {
        InterakcijaKlijentAutomobil i = (InterakcijaKlijentAutomobil) ado;
        return "registracioniBroj= "+i.getAutomobil().getRegistracioniBroj() + " AND klijentId= " + i.getKlijent().getKlijentId();
    }

    @Override
    public String getOrderCondition() {
        return "brojKoriscenja";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet rs) throws Exception {
        AbstractDomainObject ado = null;
        if(rs.next()) {
            int brojKoriscenja = rs.getInt("brojKoriscenja");
            String registracioniBroj = rs.getString("registracioniBroj");
            Long klijentId = rs.getLong("klijentId");
            
            Automobil a = new Automobil();
            a.setRegistracioniBroj(registracioniBroj);
            
            Klijent kl = new Klijent();
            kl.setKlijentId(klijentId);
            
            ado = new InterakcijaKlijentAutomobil(a, kl, brojKoriscenja);
        }
        return ado;
    }

    @Override
    public String getCondition(AbstractDomainObject ado) {
        InterakcijaKlijentAutomobil i = (InterakcijaKlijentAutomobil) ado;
        String cond = "";

        if (i.getAutomobil() != null) {
            cond += "registracioniBroj=" + i.getAutomobil().getRegistracioniBroj();
        }

        if (i.getAutomobil() != null && i.getKlijent() != null) {
            cond += " AND ";
        }

        if (i.getKlijent() != null) {
            cond += "klijentId=" + i.getKlijent().getKlijentId();
        }

        if (i.getAutomobil()== null && i.getKlijent()== null) {
            cond = "TRUE";
        }

        return cond;        
    }

    @Override
    public void setID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
