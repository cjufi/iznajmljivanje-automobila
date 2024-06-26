/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.implementation;

import domain.AbstractDomainObject;
import domain.Korisnik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author Filip
 */
public class DbBroker implements DbRepository<AbstractDomainObject>{

    @Override
    public void dodaj(AbstractDomainObject ado) throws Exception {
        try {
            String query = "INSERT INTO " + ado.getTableName() + " (" + ado.getAttributeNames() + ") VALUES(" + ado.getUnknownValues() + ")";
            System.out.println(query);
            PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
            ado.prepareStatement(ps, ado);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void promeni(AbstractDomainObject ado) throws Exception {
        try {
            String query = "UPDATE " + ado.getTableName() + " SET " + ado.getUpdateQuery() + " WHERE " + ado.getID(ado);
            System.out.println(query);
            PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
            ado.prepareStatement(ps, ado);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void obrisi(AbstractDomainObject ado) throws Exception {
        try {
            String query = "DELETE  FROM " + ado.getTableName() + " WHERE " + ado.getID(ado);
            Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }    
    }

    @Override
    public List<AbstractDomainObject> vratiSve(AbstractDomainObject ado) throws Exception {
        List<AbstractDomainObject> list = null;
        try {
            String query = "SELECT * FROM " + ado.getTableName() + " ORDER BY " + ado.getOrderCondition();
            System.out.println(query);
            Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = ado.getList(resultSet);
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return list;    }

    @Override
    public AbstractDomainObject uloguj(AbstractDomainObject ado) throws Exception {
        try {
            String query = "SELECT * FROM " + ado.getTableName() + " WHERE korisnickoIme='" + ((Korisnik) ado).getKorisnickoIme() + "' AND sifra='" + ((Korisnik) ado).getSifra()+ "'";
            System.out.println(query);
            Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ado = ado.getResult(resultSet);
            resultSet.close();
            statement.close();
            return ado;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }    
    }

    @Override
    public List<AbstractDomainObject> filter(AbstractDomainObject ado) throws Exception {
        List<AbstractDomainObject> list = null;
        try {
            String query = "SELECT * FROM " + ado.getTableName() + " WHERE " + ado.getCondition(ado) + " ORDER BY " + ado.getOrderCondition();
            System.out.println(query);
            Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = ado.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }   
    }

    @Override
    public AbstractDomainObject nadji(AbstractDomainObject ado) throws Exception {
        AbstractDomainObject ado1 = null;
        try {
            String query = "SELECT * FROM " + ado.getTableName() + " WHERE " + ado.getID(ado);
            System.out.println(query);
            Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ado1 = ado.getResult(resultSet);
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return ado1;
    }    
}
