/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import repository.Repository;

/**
 *
 * @author Filip
 * @param <T>
 */
public interface DbRepository<T> extends Repository<T> {
    
    default void connect() throws Exception {
        DbConnectionFactory.getInstance().getConnection();
    }

    default void disconnect() throws Exception {
        DbConnectionFactory.getInstance().getConnection().close();
    }

    default void commit() throws Exception {
        DbConnectionFactory.getInstance().getConnection().commit();
    }

    default void rollback() throws Exception {
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
