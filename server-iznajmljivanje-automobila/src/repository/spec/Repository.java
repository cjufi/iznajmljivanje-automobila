/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.spec;

import java.util.List;

/**
 *
 * @author Filip
 */
public interface Repository<T, K> {

    void add(T t) throws Exception;

    List<T> getAll() throws Exception;

    T getByID(K k) throws Exception;

    void edit(T t) throws Exception;

    void delete(T t) throws Exception;
}
