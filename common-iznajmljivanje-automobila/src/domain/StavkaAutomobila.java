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
public class StavkaAutomobila implements AbstractDomainObject {
    
    private Long stavkaId;
    private Automobil automobil;
    private String naziv;
    private Servis servis;

    public StavkaAutomobila() {
    }

    public StavkaAutomobila(Long stavkaId, Automobil automobil, String naziv, Servis servis) {
        this.stavkaId = stavkaId;
        this.automobil = automobil;
        this.naziv = naziv;
        this.servis = servis;
    }

    public Long getStavkaId() {
        return stavkaId;
    }

    public void setStavkaId(Long stavkaId) {
        this.stavkaId = stavkaId;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Servis getServis() {
        return servis;
    }

    public void setServis(Servis servis) {
        this.servis = servis;
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
        final StavkaAutomobila other = (StavkaAutomobila) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return "StavkaAutomobila{" + "stavkaId=" + stavkaId + ", automobil=" + automobil + ", naziv=" + naziv + ", servis=" + servis + '}';
    }

    @Override
    public String getTableName() {
        return "stavkaautomobila";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        while (resultSet.next()) {
            Long stavkaId = resultSet.getLong("stavkaId");
            String naziv = resultSet.getString("naziv");
            String registracioniBroj = resultSet.getString("registracioniBroj");
            Long servisId = resultSet.getLong("servisId");
            
            Automobil a = new Automobil();
            a.setRegistracioniBroj(registracioniBroj);
            
            Servis s = new Servis();
            s.setServisId(servisId);
            
            StavkaAutomobila sa = new StavkaAutomobila(stavkaId, a, naziv, s);
            list.add(sa);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "registracioniBroj,naziv,servisId";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception {
        StavkaAutomobila sa = (StavkaAutomobila) ado;
        ps.setString(1, sa.getAutomobil().getRegistracioniBroj());
        ps.setString(2, sa.getNaziv());
        ps.setLong(3, sa.getServis().getServisId());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID(AbstractDomainObject ado) {
        StavkaAutomobila sa = (StavkaAutomobila) ado;
        return "stavkaId=" + sa.getStavkaId();
    }

    @Override
    public String getOrderCondition() {
        return "stavkaId";
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
