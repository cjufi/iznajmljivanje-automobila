/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.database;

import java.sql.SQLException;
import repository.spec.Repository;

/**
 *
 * @author Filip
 * @param <T>
 * @param <K>
 */
public interface DBRepository<T, K> extends Repository<T, K> {

    default void connect() throws SQLException {
        DBConnectionFactory.getInstance().getConnection();
    }

    default void disconnect() throws SQLException {
        DBConnectionFactory.getInstance().getConnection().close();
    }

    default void commit() throws SQLException {
        DBConnectionFactory.getInstance().getConnection().commit();
    }

    default void rollback() throws SQLException {
        DBConnectionFactory.getInstance().getConnection().rollback();
    }
}
