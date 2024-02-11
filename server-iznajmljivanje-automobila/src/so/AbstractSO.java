/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import database.DBBroker;
import repository.database.DBConnectionFactory;

/**
 *
 * @author Filip
 */
public abstract class AbstractSO {

    protected DBBroker dbb;

    public AbstractSO() {
        dbb = new DBBroker();
    }

    public void execute(Object param) throws Exception {
        try {
            precondition(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
            System.out.println("Uspesno izvrsena operacija!");
        } catch (Exception e) {
            System.out.println("Neuspesno izvrsena operacija!");
            rollbackTransaction();
            throw e;
        } finally {
            closeConnection();
        }

    }

    protected abstract void precondition(Object param) throws Exception;

    protected abstract void executeOperation(Object param) throws Exception;

    protected void startTransaction() throws Exception {
    }

    protected void closeConnection() throws Exception {
        DBConnectionFactory.getInstance().getConnection().close();
    }

    protected void rollbackTransaction() throws Exception {
        DBConnectionFactory.getInstance().getConnection().rollback();
    }

    protected void commitTransaction() throws Exception {
        DBConnectionFactory.getInstance().getConnection().commit();
    }
}
